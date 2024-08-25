package com.cardstore.controller.admin;

import java.io.IOException;

import com.cardstore.service.UserServices;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/user_ban")
public class BanUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BanUserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserServices userServices = new UserServices(request, response);

		userServices.banUser();
	}
}
