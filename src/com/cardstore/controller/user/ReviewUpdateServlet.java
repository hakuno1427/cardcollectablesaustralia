package com.cardstore.controller.user;

import java.io.IOException;

import com.cardstore.service.ReviewServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 05/09/2024
 */

@WebServlet("/review_update_save")
public class ReviewUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReviewUpdateServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reviewId = request.getParameter("reviewId");
        String comment = request.getParameter("comment");
        double rating = Double.parseDouble(request.getParameter("rating"));

        ReviewServices reviewServices = new ReviewServices(request, response);
        reviewServices.updateReview(reviewId, comment, rating);
        response.sendRedirect(request.getContextPath() + "/review_buyer");
    }
}