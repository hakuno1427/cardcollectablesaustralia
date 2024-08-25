package com.cardstore.service;

import java.io.IOException;
import java.util.List;

import com.cardstore.dao.ReviewDAO;
import com.cardstore.entity.Review;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 25/08/2024
 */

public class AdminReviewServices {
    private ReviewDAO reviewDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public AdminReviewServices(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.reviewDAO = new ReviewDAO();
    }

    public void listReviews() throws IOException {
        int page = 1;
        int pageSize = 10;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int start = (page - 1) * pageSize;
        List<Review> listReviews = reviewDAO.listPaged(start, pageSize);
        long totalReviews = reviewDAO.count();
        int totalPages = (int) Math.ceil((double) totalReviews / pageSize);
        int pageRange = 10;
        int startPage = Math.max(1, page - pageRange / 2);
        int endPage = Math.min(totalPages, startPage + pageRange - 1);

        if (endPage - startPage < pageRange) {
            startPage = Math.max(1, endPage - pageRange + 1);
        }

        request.setAttribute("listReviews", listReviews);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
    }
}