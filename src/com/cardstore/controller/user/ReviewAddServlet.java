package com.cardstore.controller.user;

import java.io.IOException;

import com.cardstore.service.ReviewServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 03/09/2024
 */

@WebServlet("/review_add_save")
public class ReviewAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReviewAddServlet() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	
	    ReviewServices reviewServices = new ReviewServices(request, response);
	    reviewServices.createReview(); 
	}
}