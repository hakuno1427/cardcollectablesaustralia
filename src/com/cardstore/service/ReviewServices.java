package com.cardstore.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.cardstore.dao.ReviewDAO;
import com.cardstore.dao.UserDAO;
import com.cardstore.entity.Review;
import com.cardstore.entity.Role;
import com.cardstore.entity.User;
import com.cardstore.entity.Permission;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 03/09/2024
 */

public class ReviewServices {
	public static final String REVIEW_BUYER = "REVIEW_BUYER";

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ReviewDAO reviewDAO;
    private UserDAO userDAO;

    public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.reviewDAO = new ReviewDAO();
        this.userDAO = new UserDAO();
    }
    
    public List<User> getSellers() {
        UserDAO userDao = new UserDAO();
        List<User> sellers = userDao.findSellers();
        if (sellers.isEmpty()) {
            System.out.println("No sellers found");
        }
        return sellers;
    }

    public void createReview() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null || !this.hasPermission(user, REVIEW_BUYER)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to create a review.");
            return;
        }

        String sellerIdStr = request.getParameter("sellerId");
        String ratingStr = request.getParameter("rating");
        String comment = request.getParameter("comment");

        if (sellerIdStr == null || ratingStr == null || comment == null ||
            sellerIdStr.isEmpty() || ratingStr.isEmpty() || comment.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required form fields.");
            return;
        }

        try {
            int sellerId = Integer.parseInt(sellerIdStr);
            double rating = Double.parseDouble(ratingStr);

            Review review = new Review();
            review.setBuyerId(user.getUserId()); // Assuming you have a method to get the buyer ID from the user
            review.setSellerId(sellerId);
            review.setRating(rating);
            review.setComment(comment);
            review.setReviewDate(LocalDate.now());

            reviewDAO.create(review);

            response.sendRedirect(request.getContextPath() + "/review_buyer");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid format for numeric fields.");
        }
    }

    public void listReviews() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You must be logged in to view reviews.");
            return;
        }

        int page = 1;
        int pageSize = 10;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int start = (page - 1) * pageSize;
        List<Review> reviews = reviewDAO.searchByBuyer(user.getUserId(), start, pageSize);
        long totalReviews = reviewDAO.countByBuyer(user.getUserId());
        int totalPages = (int) Math.ceil((double) totalReviews / pageSize);
        int pageRange = 10;
        int startPage = Math.max(1, page - pageRange / 2);
        int endPage = Math.min(totalPages, startPage + pageRange - 1);

        if (endPage - startPage < pageRange) {
            startPage = Math.max(1, endPage - pageRange + 1);
        }

        request.setAttribute("reviews", reviews);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("buyerId", user.getUserId());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/review_buyer.jsp");
        dispatcher.forward(request, response);
    }
       
    
    public void listSellerReviews() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You must be logged in to view reviews.");
            return;
        }

        int page = 1;
        int pageSize = 10;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int start = (page - 1) * pageSize;
        List<Review> reviewList = reviewDAO.findReviewsBySeller(user.getUserId(), start, pageSize);
    
        long totalReviews = reviewDAO.countBySeller(user.getUserId());
        int totalPages = (int) Math.ceil((double) totalReviews / pageSize);
        int pageRange = 10;
        int startPage = Math.max(1, page - pageRange / 2);
        int endPage = Math.min(totalPages, startPage + pageRange - 1);

        if (endPage - startPage < pageRange) {
            startPage = Math.max(1, endPage - pageRange + 1);
        }

        request.setAttribute("reviews", reviewList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("sellerId", user.getUserId());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/review_seller.jsp");
        dispatcher.forward(request, response);
 
    }

    public void prepareReviewUpdatePage(String reviewId) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (!this.hasPermission(user, REVIEW_BUYER)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
            return;
        }

        Review review = reviewDAO.get(reviewId);
        if (review == null) {
            String message = "Could not find review with ID " + reviewId;
            request.setAttribute("message", message);
            // Optionally redirect to the list page or show an error
            return;
        }

        List<User> sellers = this.getSellers(); // Retrieve sellers
        request.setAttribute("review", review);
        request.setAttribute("sellers", sellers); // Set sellers attribute

        RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/review_update.jsp");
        dispatcher.forward(request, response);
    }

    public void updateReview(String reviewId, String comment, double rating) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (!this.hasPermission(user, REVIEW_BUYER)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
            return;
        }

        Review review = reviewDAO.get(reviewId);
        if (review != null) {
            review.setComment(comment);
            review.setRating(rating);
            reviewDAO.update(review);
        } 
    }

    public void deleteReview() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (!this.hasPermission(user, REVIEW_BUYER)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
            return;
        }

        String reviewId = request.getParameter("id");
        if (reviewId != null && !reviewId.trim().isEmpty()) {
            reviewDAO.delete(reviewId);
            response.sendRedirect(request.getContextPath() + "/review_buyer");
        } else {
            String message = "Review ID is required for deletion.";
            request.setAttribute("message", message);
            listReviews();
        }
    }
    
	private boolean hasPermission(User user, String permissionName) {
		if (user == null) {
			return false;
		}

		Role role = user.getRole();

		Set<Permission> permissions = role.getPermissions();
		for (Permission permission : permissions) {
			if (permission.getName().equals(permissionName)) {
				return true;
			}
		}

		return false;
	}
}