package com.cardstore.controller.admin;

import java.io.IOException;
import com.cardstore.service.CardServices;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 21/08/2024
 */

@WebServlet("/admin/catalogue")
public class ShowCatalogueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowCatalogueServlet() {
		super();
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CardServices cardServices = new CardServices(request, response);

        cardServices.listCards();

        String cataloguePage = "/admin/catalogue.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(cataloguePage);
        dispatcher.forward(request, response);
    }
}