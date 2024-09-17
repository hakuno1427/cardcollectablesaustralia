package com.cardstore.service;

import java.io.IOException;
import java.util.List;

import com.cardstore.dao.ListingDAO;
import com.cardstore.dao.ReviewDAO;
import com.cardstore.dao.UserDAO;
import com.cardstore.entity.Listing;
import com.cardstore.entity.Review;
import com.cardstore.entity.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SellerService {
	
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private ListingDAO listingDAO;
	private UserDAO userDAO;
	private ReviewDAO reviewDAO;

	public SellerService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.listingDAO = new ListingDAO();
		this.userDAO = new UserDAO();
		this.reviewDAO = new ReviewDAO();
		
	}
	
	public void viewSellerProfile() throws ServletException, IOException {
		Integer sellerId = Integer.parseInt(request.getParameter("sellerId"));
		User user = userDAO.get(sellerId);
		
		int page = 1;
        int pageSize = 8;
        
        if (request.getParameter("cardPage") != null) {
            page = Integer.parseInt(request.getParameter("cardPage"));
        }
		
        int start = (page - 1) * pageSize;
        long totalListing = listingDAO.listingCountBySeller(sellerId);
        int totalPages = (int) Math.ceil((double) totalListing / pageSize);
        
		if (user != null) {
			List<Listing> sellerListing = listingDAO.listSellerListing(sellerId, start, pageSize);
            request.setAttribute("listOfListings", sellerListing);
            request.setAttribute("seller", user);
            request.setAttribute("cardsCount", (int)totalListing);
            request.setAttribute("cardsCurrentPage", page);
            request.setAttribute("cardsTotalPages", totalPages);
            
            getSellerReviewsBySellerId(sellerId);
            
	        String viewSellerPage = "seller/view_seller.jsp";
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewSellerPage);
	        requestDispatcher.forward(request, response);
	    } else {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Seller not found");
	    }
		
	} 
	
	
	public void getSellerReviewsBySellerId(Integer sellerId) {
		int page = 1;
        int pageSize = 10;

        if (request.getParameter("reviewPage") != null) {
            page = Integer.parseInt(request.getParameter("reviewPage"));
        }
        int start = (page - 1) * pageSize;
        List<Review> reviewList = reviewDAO.findReviewsBySeller(sellerId, start, pageSize);
    
        long totalReviews = reviewDAO.countBySeller(sellerId);
        int totalPages = (int) Math.ceil((double) totalReviews / pageSize);

        request.setAttribute("reviews", reviewList);
        request.setAttribute("totalReviews", totalReviews);
        request.setAttribute("reviewCurrentPage", page);
        request.setAttribute("reviewTotalPages", totalPages);
       
	}
}
