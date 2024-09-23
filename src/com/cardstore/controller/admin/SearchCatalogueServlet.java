package com.cardstore.controller.admin;

import java.io.IOException;

import com.cardstore.service.CardServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 18/09/2024
 */

@WebServlet("/admin/searchCatalogue")
public class SearchCatalogueServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchCatalogueServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CardServices cardServices = new CardServices(request, response);
        cardServices.searchCatalogue();
    }
}