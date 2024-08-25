package com.cardstore.service;

import java.io.IOException;
import java.util.List;

import com.cardstore.dao.CardDAO;
import com.cardstore.entity.Card;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 25/08/2024
 */

public class CardServices {
    private CardDAO cardDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public CardServices(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.cardDAO = new CardDAO();
    }

    public void listCards() throws ServletException, IOException {
        List<Card> listCards = cardDAO.listAll();
        request.setAttribute("listCards", listCards);
        
        String cardAddPage = "/admin/catalogue.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(cardAddPage);
        dispatcher.forward(request, response);
    }

    public void createCard() throws ServletException, IOException {
        String serialNumber = request.getParameter("serialNumber");
        String cardName = request.getParameter("cardName");
        String description = request.getParameter("description");
        String game = request.getParameter("game");
        String marketprice = request.getParameter("marketprice");
        String imageUrl = request.getParameter("imageUrl");

        Card card = new Card(cardName, game, serialNumber, description, Double.parseDouble(marketprice), imageUrl);
        cardDAO.create(card);

        response.sendRedirect(request.getContextPath() + "/admin/catalogue");
    }

    public void updateCard() throws ServletException, IOException {
        String serialNumber = request.getParameter("serialNumber");
        String cardName = request.getParameter("cardName");
        String description = request.getParameter("description");
        String game = request.getParameter("game");
        String marketprice = request.getParameter("marketprice");
        String imageUrl = request.getParameter("imageUrl");

        Card card = new Card(cardName, game, serialNumber, description, Double.parseDouble(marketprice), imageUrl);
        cardDAO.update(card);

        response.sendRedirect(request.getContextPath() + "/admin/catalogue");
    }

    public void deleteCard() throws ServletException, IOException {
        String serialNumber = request.getParameter("id"); 
        if (serialNumber != null && !serialNumber.trim().isEmpty()) {
            cardDAO.delete(serialNumber);
            response.sendRedirect(request.getContextPath() + "/admin/catalogue");
        } else {
            String message = "Card ID is required for deletion.";
            request.setAttribute("message", message);
            listCards(); 
        }
    }

    public void getCardBySerialNumber() throws ServletException, IOException {
        String serialNumber = request.getParameter("serialNumber");
        Card card = cardDAO.get(serialNumber);

        if (card == null) {
            String message = "Could not find card with Serial Number " + serialNumber;
            request.setAttribute("message", message);
            listCards();
            return;
        }

        request.setAttribute("card", card);
        String editPage = "/admin/card_form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
        dispatcher.forward(request, response);
    }
}