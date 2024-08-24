package com.cardstore.controller.user;

import java.io.IOException;

import com.cardstore.entity.User;
import com.cardstore.service.PermissionService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add_listing")
public class ShowAddListingFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String ADD_LISTING_PERMISSION = "ADD_LISTING";

	private PermissionService permissionService;

	public ShowAddListingFormServlet() {
		super();
		this.permissionService = new PermissionService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getAttribute("urser");
		if ((user == null) || (permissionService.hasPermission(user.getRole(), ADD_LISTING_PERMISSION))) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
			return;
		}

		String registerForm = "frontend/add_listing_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(registerForm);
		dispatcher.forward(request, response);
	}
}
