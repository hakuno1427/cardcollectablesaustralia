package com.cardstore.controller.user;

import java.io.IOException;

import com.cardstore.service.UserServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/verify")
public class VerificationServlet extends HttpServlet {
    private UserServices userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String token = request.getParameter("token");
        
        userService = new UserServices(request, response);
        userService.verifyUser(token);
    }
}