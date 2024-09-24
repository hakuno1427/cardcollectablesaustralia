package com.cardstore.controller.seller;

import java.io.IOException;
import java.util.List;

import com.cardstore.dao.CardDAO;
import com.cardstore.entity.Card;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Author: Rutvi Patel
 */

@WebServlet("/loadCards")
public class LoadCardsServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	
	private final CardDAO cardDAO;

	public LoadCardsServlet() {
		super();
		this.cardDAO = new CardDAO();
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String offsetParam = req.getParameter("offset");
        String limitParam = req.getParameter("limit");
        int offset = Integer.parseInt(offsetParam);
        int limit = Integer.parseInt(limitParam);
        
        try {
            List<Card> cards = cardDAO.fetchCardsWithLazyLoading(offset, limit);
            
         // Return JSON response manually
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            StringBuilder json = new StringBuilder();
            json.append("[");
            for (int i = 0; i < cards.size(); i++) {
                Card card = cards.get(i);
                json.append("{")
                .append("\"serialNumber\":\"").append(card.getSerialNumber()).append("\", ")
                .append("\"cardName\":\"").append(card.getCardName()).append("\"")
                .append("}");
                if (i < cards.size() - 1) {
                    json.append(", ");
                }
            }
            json.append("]");

            resp.getWriter().write(json.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
	}

}
