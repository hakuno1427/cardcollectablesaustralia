package com.cardstore.controller.admin;

import java.io.IOException;

import com.cardstore.dao.RoleDAO;
import com.cardstore.entity.Role;
import com.cardstore.service.UserServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 24/08/2024
 */

@WebServlet("/admin/register_save")
public class RegisterAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RoleDAO roleDAO;

	public RegisterAdminServlet() {
		super();
		this.roleDAO = new RoleDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserServices userServices = new UserServices(request, response);
		userServices.register(roleDAO.findByName(Role.ADMIN_ROLE));
	}
}

