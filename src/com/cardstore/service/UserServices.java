package com.cardstore.service;

import java.io.IOException;

import com.cardstore.dao.UserDAO;
import com.cardstore.entity.Role;
import com.cardstore.entity.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserServices {
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
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

		if (email != null && !email.equals("")) {
			user.setEmail(email);
		}

		user.setFirstName(firstname);
		user.setLastName(lastname);

		if (password != null && !password.equals("")) {
			user.setPassword(password);
		}
		user.setPhone(Integer.parseInt(phone));
	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userDAO.checkLogin(email, password);

		if (user == null) {
			String message = "Login failed. Please check your email and password.";
			request.setAttribute("message", message);
			showLogin();

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
				showCustomerProfile();
			}
		}
	}

	public void showLogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
		dispatcher.forward(request, response);
	}

	public void showCustomerProfile() throws ServletException, IOException {
		String profilePage = "frontend/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
		dispatcher.forward(request, response);
	}
}
