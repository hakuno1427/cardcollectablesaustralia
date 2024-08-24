package com.cardstore.controller.admin;

import java.io.IOException;
import java.util.List;

import com.cardstore.dao.CardDAO;
import com.cardstore.entity.Card;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242
 * Created Date: 21/08/2024
 */

@WebServlet("/catalogue")
public class ShowCatalogueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowCatalogueServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        CardDAO cardDAO = new CardDAO();
        int page = 1;
        int pageSize = 10;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int start = (page - 1) * pageSize;
        List<Card> listCards = cardDAO.listPaged(start, pageSize);
        long totalCards = cardDAO.count();
        int totalPages = (int) Math.ceil((double) totalCards / pageSize);
        int pageRange = 10;
        int startPage = Math.max(1, page - pageRange / 2);
        int endPage = Math.min(totalPages, startPage + pageRange - 1);

        if (endPage - startPage < pageRange) {
            startPage = Math.max(1, endPage - pageRange + 1);
        }

        request.setAttribute("listCards", listCards);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);

        String cataloguePage = "frontend/catalogue.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(cataloguePage);
        dispatcher.forward(request, response);
    }
}