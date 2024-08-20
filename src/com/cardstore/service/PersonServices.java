package com.cardstore.service;

import java.io.IOException;

import com.cardstore.dao.BuyerDAO;
import com.cardstore.dao.PersonDAO;
import com.cardstore.dao.SellerDAO;
import com.cardstore.entity.Buyer;
import com.cardstore.entity.Person;
import com.cardstore.entity.Seller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PersonServices {
	private PersonDAO personDAO;
	private BuyerDAO buyerDAO;
	private SellerDAO sellerDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public PersonServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.personDAO = new PersonDAO();
		this.buyerDAO = new BuyerDAO();
		this.sellerDAO = new SellerDAO();
	}
	
	public void registerBuyer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Person existPerson = personDAO.findByEmail(email);
		String message = "";

		if (existPerson != null) {
			message = "Could not register. The email " + email + " is already registered by another person.";
		} else {

			Buyer newBuyer = new Buyer();
			updatePersonFieldsFromForm(newBuyer);
			buyerDAO.create(newBuyer);

			message = "You have registered as a buyer successfully! Thank you.<br/>" + "<a href='login'>Click here</a> to login";
		}

		String messagePage = "frontend/message.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
	}
	
	public void registerSeller() throws ServletException, IOException {
		String email = request.getParameter("email");
		Person existPerson = personDAO.findByEmail(email);
		String message = "";

		if (existPerson != null) {
			message = "Could not register. The email " + email + " is already registered by another person.";
		} else {

			Seller newSeller = new Seller();
			updatePersonFieldsFromForm(newSeller);
			sellerDAO.create(newSeller);

			message = "You have registered as a seller successfully! Thank you.<br/>" + "<a href='login'>Click here</a> to login";
		}

		String messagePage = "frontend/message.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
	}

	private void updatePersonFieldsFromForm(Person person) {
		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");

		if (email != null && !email.equals("")) {
			person.setEmail(email);
		}

		person.setFirstName(firstname);
		person.setLastName(lastname);

		if (password != null && !password.equals("")) {
			person.setPassword(password);
		}
		person.setPhone(Integer.parseInt(phone));
	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Person person = personDAO.checkLogin(email, password);

		if (person == null) {
			String message = "Login failed. Please check your email and password.";
			request.setAttribute("message", message);
			showLogin();

		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loggedInPerson", person);
			session.setAttribute("personRole", person instanceof Buyer ? "BUYER" : "SELLER");

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
