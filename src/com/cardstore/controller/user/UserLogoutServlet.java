package com.cardstore.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class UserLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserLogoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("role");

		response.sendRedirect("/");
	}
}
