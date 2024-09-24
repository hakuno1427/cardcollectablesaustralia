package com.cardstore.controller.user;

import java.io.IOException;

import com.cardstore.dao.RoleDAO;
import com.cardstore.entity.Role;
import com.cardstore.service.UserServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register_buyer")
public class RegisterBuyerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RoleDAO roleDAO;

	public RegisterBuyerServlet() {
		super();
		this.roleDAO = new RoleDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserServices userServices = new UserServices(request, response);
		userServices.register(roleDAO.findByName(Role.BUYER_ROLE));
	}
}
