package com.cardstore.controller.admin;

import java.io.IOException;

import com.cardstore.dao.CardDAO;
import com.cardstore.entity.Card;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 23/08/2024
 */

@WebServlet("/admin/card_update_save")
public class CardUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CardDAO cardDAO = new CardDAO();

    public CardUpdateServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String serialNumber = request.getParameter("serialNumber");
        String cardName = request.getParameter("cardName");
        String description = request.getParameter("description");
        String game = request.getParameter("game");
        String imageUrl = request.getParameter("imageUrl");
        String marketPriceString = request.getParameter("marketprice");

        //parse marketprice
        double marketPrice = -1; //default for no value
        if (marketPriceString != null && !marketPriceString.isEmpty()) {
            try {
                marketPrice = Double.parseDouble(marketPriceString);
            } catch (NumberFormatException e) {
            }
        }

        //update card object
        Card card = cardDAO.get(serialNumber);
        if (card != null) {
            card.setCardName(cardName);
            card.setDescription(description);
            card.setGame(game);
            card.setImageUrl(imageUrl);
            card.setMarketprice(marketPrice);

            cardDAO.update(card);
            response.sendRedirect("catalogue");
        } else {
            request.setAttribute("error", "Card not found.");
            request.getRequestDispatcher("/admin/card_update.jsp").forward(request, response);
        }
    }
}