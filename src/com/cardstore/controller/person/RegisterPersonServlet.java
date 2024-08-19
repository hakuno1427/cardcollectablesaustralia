package com.cardstore.controller.person;

import java.io.IOException;

import com.cardstore.service.PersonServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register_user")
public class RegisterPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterPersonServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersonServices personServices = new PersonServices(request, response);
		personServices.registerPerson();
	}
}
