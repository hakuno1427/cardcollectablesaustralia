package com.cardstore.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.cardstore.dao.UserDAO;
import com.cardstore.entity.Card;
import com.cardstore.entity.Permission;
import com.cardstore.entity.Role;
import com.cardstore.entity.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserServices {
	public static final String VIEW_PROFILE_PERMISSION = "VIEW_MY_PROFILE";
	public static final String EDIT_PROFILE_PERMISSION = "EDIT_MY_PROFILE";
	public static final String MANAGE_USER_PERMISSION = "MANAGE_USER";

	private UserDAO userDAO;
	private EmailService emailService;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.emailService = new EmailService();
		this.userDAO = new UserDAO();
	}

	public void register(Role role) throws ServletException, IOException {
		String email = request.getParameter("email");
		User existUser = userDAO.findByEmail(email);
		String message = "";

		if (existUser != null) {
			message = "Could not register. The email " + email + " is already registered by another user.";
		} else {

			User newUser = new User();
			newUser.setRole(role);
			updateUserFieldsFromForm(newUser);
			
			String verificationToken = UUID.randomUUID().toString();
			newUser.setVerificationToken(verificationToken);
			
			emailService.sendVerificationEmail(email, verificationToken);
			userDAO.create(newUser);

			message = "You have registered successfully! Thank you.<br/>" + "<a href='login'>Click here</a> to login";
		}

		String messagePage = "frontend/message.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
	}

	private void updateUserFieldsFromForm(User user) {
		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String description = request.getParameter("description");
		

		if (email != null && !email.equals("")) {
			user.setEmail(email);
		}

		user.setFirstName(firstname);
		user.setLastName(lastname);

		if (password != null && !password.equals("")) {
			user.setPassword(password);
		}
		user.setPhone(Integer.parseInt(phone));
		if(description!=null && !description.equals("")) {
			user.setDescription(description);
		}
		else 
		{
			user.setDescription("");		
		}
	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userDAO.checkLogin(email, password);

		if (user == null) {
			String message = "Login failed. Please check your email and password.";
			request.setAttribute("message", message);
			showLogin();
			return;
		}

		if (user.getEnabled() == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("role", user.getRole());

			Object objRedirectURL = session.getAttribute("redirectURL");

			if (objRedirectURL != null) {
				String redirectURL = (String) objRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			} else {
				showMyProfile();
			}

			return;
		}

		String message = "Your account has been banned permanently. If you think this is a mistake, you can send an appeal to us";
		request.setAttribute("message", message);
		showLogin();
	}

	public void showLogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
		dispatcher.forward(request, response);
	}

	public void showMyProfile() throws ServletException, IOException {
		String profilePage = "frontend/profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
		dispatcher.forward(request, response);
	}

	public void doAdminLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userDAO.checkLogin(email, password);

		if (user == null) {
			String message = "Login failed. Please check your email and password.";
			request.setAttribute("message", message);
			showAdminLogin(); 
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("role", user.getRole());

			Object objRedirectURL = session.getAttribute("redirectURL");

			if (objRedirectURL != null) {
				String redirectURL = (String) objRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			} else {
				showAdminProfile();
			}
		}
	}

	public void showUserProfile() throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		if (!this.hasPermission(user, VIEW_PROFILE_PERMISSION)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
			return;
		}

		String profilePage = "frontend/profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
		dispatcher.forward(request, response);
	}

	public void showEditProfileForm() throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		if (!this.hasPermission(user, EDIT_PROFILE_PERMISSION)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
			return;
		}

		String editProfilePage = "frontend/edit_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editProfilePage);
		dispatcher.forward(request, response);
	}

	public void updateProfile() throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		if (!this.hasPermission(user, EDIT_PROFILE_PERMISSION)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
			return;
		}

		updateUserFieldsFromForm(user);
		userDAO.update(user);

		showUserProfile();
	}

	private boolean hasPermission(User user, String permissionName) {
		if (user == null) {
			return false;
		}

		Role role = user.getRole();

		Set<Permission> permissions = role.getPermissions();
		for (Permission permission : permissions) {
			if (permission.getName().equals(permissionName)) {
				return true;
			}
		}

		return false;
	}

	public void showAdminLogin() throws ServletException, IOException {
		String loginPage = "/admin/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
		dispatcher.forward(request, response);
	}

	public void showAdminProfile() throws ServletException, IOException {
		Role role = (Role) request.getSession().getAttribute("role");
		if (role.getName().compareTo(Role.ADMIN_ROLE) == 0) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
			return;
		}

		String profilePage = "/admin/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
		dispatcher.forward(request, response);
	}

	public void adminRegister(Role role) throws ServletException, IOException {
		String email = request.getParameter("email");
		User existUser = userDAO.findByEmail(email);
		String message = "";

		if (existUser != null) {
			message = "Could not register. The email " + email + " is already registered by another user.";
		} else {

			User newUser = new User();
			newUser.setRole(role);

			updateUserFieldsFromForm(newUser);
			userDAO.create(newUser);

			message = "You have registered successfully! Thank you.<br/>" + "<a href='login'>Click here</a> to login";
		}

		String messagePage = "/admin/message.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
	}
	
	public void listUsers() throws ServletException, IOException {
	    User user = (User) request.getSession().getAttribute("user");

	    if (!this.hasPermission(user, MANAGE_USER_PERMISSION)) {
	        response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
	        return;
	    }

	    int page = 1;
	    int pageSize = 25;

	    if (request.getParameter("page") != null) {
	        page = Integer.parseInt(request.getParameter("page"));
	    }
	    
	    String roleFilter = request.getParameter("roleFilter");
	    int start = (page - 1) * pageSize;
	    
	    List<User> listUsers = (roleFilter != null) ? userDAO.findByRole(roleFilter) : userDAO.listPaged(start, pageSize);

	    
	    long totalUsers = listUsers.size();
	    
	    int totalPages = (int) Math.ceil((double) totalUsers / pageSize);
	    int pageRange = 10;
	    int startPage = Math.max(1, page - pageRange / 2);
	    int endPage = Math.min(totalPages, startPage + pageRange - 1);

	    if (endPage - startPage < pageRange) {
	        startPage = Math.max(1, endPage - pageRange + 1);
	    }

	    request.setAttribute("listUsers", listUsers);
	    request.setAttribute("currentPage", page);
	    request.setAttribute("totalPages", totalPages);
	    request.setAttribute("startPage", startPage);
	    request.setAttribute("endPage", endPage);

	    request.setAttribute("roleFilter", roleFilter);


	    String listPage = "users.jsp";
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
	    requestDispatcher.forward(request, response);
	}
	
	public void showAdminUpdateUserForm() throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		if (!this.hasPermission(user, MANAGE_USER_PERMISSION)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
			return;
		}

		int userId = Integer.parseInt(request.getParameter("id"));
		User selectedUser = userDAO.get(userId);

		if (selectedUser == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "We cannot find user with that email");
			return;
		}

		request.setAttribute("selectedUser", selectedUser);
	}

	public void updateUser() throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		if (!this.hasPermission(user, MANAGE_USER_PERMISSION)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
			return;
		}

		int userId = Integer.parseInt(request.getParameter("id"));
		User selectedUser = userDAO.get(userId);

		updateUserFieldsFromForm(selectedUser);
		userDAO.update(selectedUser);

		listUsers();
	}

	public void banUser() throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		if (!this.hasPermission(user, MANAGE_USER_PERMISSION)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
			return;
		}

		int userId = Integer.parseInt(request.getParameter("id"));
		User selectedUser = userDAO.get(userId);

		if (selectedUser == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "We cannot find user with that email");
			return;
		}

		selectedUser.setEnabled(User.NO_VALUE);
		userDAO.update(selectedUser);

		String message = "You have successfully banned this user";
		request.setAttribute("message", message);
		listUsers();
	}
	
	public void verifyUser(String token) throws ServletException, IOException {
		User user = userDAO.getUserByVerificationToken(token);
		
		if (user == null) {
			String message = "Verification token is not valid";
			request.setAttribute("message", message);
			showLogin();
			return;
		}
		
		user.setVerified(User.YES_VALUE);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		session.setAttribute("role", user.getRole());

		showMyProfile();
	}
}
