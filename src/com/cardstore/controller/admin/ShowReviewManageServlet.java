package com.cardstore.controller.admin;

import java.io.IOException;
import java.util.List;

import com.cardstore.dao.ReviewDAO;
import com.cardstore.entity.Review;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 24/08/2024
 */

@WebServlet("/admin/review_manage")
public class ShowReviewManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowReviewManageServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

        ReviewDAO reviewDAO = new ReviewDAO();
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

        String reviewManagePage = "/admin/review_manage.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(reviewManagePage);
        dispatcher.forward(request, response);
    }
}