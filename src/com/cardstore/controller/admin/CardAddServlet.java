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

@WebServlet("/admin/card_add_save")
public class CardAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CardAddServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String serialNumber = request.getParameter("serialNumber");
        String cardName = request.getParameter("cardName");
        String description = request.getParameter("description");
        String game = request.getParameter("game");
        String marketprice = request.getParameter("marketprice");
        String imageUrl = request.getParameter("imageUrl");

        //create new card object and set attributes
        Card card = new Card();
        card.setSerialNumber(serialNumber);
        card.setCardName(cardName);
        card.setDescription(description);
        card.setGame(game);
        card.setMarketprice(Double.parseDouble(marketprice));
        card.setImageUrl(imageUrl);

        //save new card using CardDAO
        CardDAO cardDAO = new CardDAO();
        cardDAO.create(card);

        //redirect to catalogue
        response.sendRedirect("/admin/catalogue");
    }
}