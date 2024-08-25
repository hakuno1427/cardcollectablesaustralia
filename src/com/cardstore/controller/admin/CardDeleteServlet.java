package com.cardstore.controller.admin;

import java.io.IOException;

import com.cardstore.dao.CardDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 23/08/2024
 */

@WebServlet("/admin/card_delete")
public class CardDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CardDAO cardDAO = new CardDAO();

    public CardDeleteServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String serialNumber = request.getParameter("id");

        if (serialNumber != null && !serialNumber.trim().isEmpty()) {
            cardDAO.delete(serialNumber);
        }

      //redirect to catalogue
        response.sendRedirect("/admin/catalogue");
    }
}