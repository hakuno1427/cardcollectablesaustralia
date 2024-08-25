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
 * @author Sera Jeong 12211242 Created Date: 22/08/2024
 */

@WebServlet("/admin/card_update")
public class ShowCardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowCardUpdateServlet() {
		super();
	}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	CardServices cardServices = new CardServices(request, response);
        cardServices.prepareCardUpdatePage(); 

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/card_update.jsp");
        dispatcher.forward(request, response);
    }
}