package com.cardstore.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/policy")
public class PolicyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PolicyServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String policyPage = "frontend/policy.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(policyPage);
        dispatcher.forward(request, response);
    }
}