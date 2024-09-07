package com.cardstore.controller.user;

import java.io.IOException;

import com.cardstore.service.ReviewServices;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 05/09/2024
 */

@WebServlet("/review_update")
public class ShowReviewUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowReviewUpdateServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reviewId = request.getParameter("id");

        ReviewServices reviewServices = new ReviewServices(request, response);
        reviewServices.prepareReviewUpdatePage(reviewId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/review_update.jsp");
        dispatcher.forward(request, response);
    }
}