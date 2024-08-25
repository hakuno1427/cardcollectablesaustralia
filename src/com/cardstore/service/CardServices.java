package com.cardstore.service;

import java.io.IOException;
import java.util.Arrays;
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
    }
    
    public void prepareCardAddPage() throws ServletException, IOException {
    	//add new game names to the list when they are available
        List<String> games = Arrays.asList("Magic The Gathering", "Pokemon");
        request.setAttribute("games", games);
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
    
    public void prepareCardUpdatePage() throws ServletException, IOException {
    	//add new game names to the list when they are available
        List<String> games = Arrays.asList("Magic The Gathering", "Pokemon");
        request.setAttribute("games", games);

        String serialNumber = request.getParameter("id");
        Card card = cardDAO.get(serialNumber);

        if (card == null) {
            String message = "Could not find card with Serial Number " + serialNumber;
            request.setAttribute("message", message);
            listCards();  // Optionally redirect to the list page if card is not found
            return;
        }

        request.setAttribute("card", card);
    }
    
    public void updateCard(String serialNumber, String cardName, String description, String game, double marketPrice, String imageUrl) {
        Card card = cardDAO.get(serialNumber);
        if (card != null) {
            card.setCardName(cardName);
            card.setDescription(description);
            card.setGame(game);
            card.setMarketprice(marketPrice);
            card.setImageUrl(imageUrl);
            cardDAO.update(card);
        }
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
}