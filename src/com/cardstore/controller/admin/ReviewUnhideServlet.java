package com.cardstore.controller.admin;

import java.io.IOException;

import com.cardstore.service.AdminReviewServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 09/09/2024
 */

@WebServlet("/admin/review_unhide")
public class ReviewUnhideServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        AdminReviewServices adminReviewServices = new AdminReviewServices(request, response);

        String reviewId = request.getParameter("id");

        if (reviewId != null && !reviewId.trim().isEmpty()) {
            adminReviewServices.unhideReview(reviewId);
            response.sendRedirect(request.getContextPath() + "/admin/review_manage");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Review ID is required.");
        }
    }
}