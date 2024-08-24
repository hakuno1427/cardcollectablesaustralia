package com.cardstore.controller.admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 24/08/2024
 */

@WebServlet("/admin/register")
public class ShowAdminRegisterFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowAdminRegisterFormServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String registerForm = "../admin/admin_register_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(registerForm);
		dispatcher.forward(request, response);
	}
}
