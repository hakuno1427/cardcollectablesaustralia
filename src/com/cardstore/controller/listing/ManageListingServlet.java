package com.cardstore.controller.listing;

import com.cardstore.dao.CardDAO;
import com.cardstore.dao.ListingDAO;
import com.cardstore.entity.Card;
import com.cardstore.entity.Listing;
import com.cardstore.entity.User;
import com.cardstore.service.ListingService;
import com.cardstore.service.PermissionService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * @author Rutvi 
 */

@WebServlet("/listings")
public class ManageListingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ListingDAO listingDAO ;
    private CardDAO cardDAO;

    public static final String ADD_LISTING_PERMISSION = "MANAGE_MY_LISTING";

    private PermissionService permissionService;
    public ManageListingServlet() {
        super();
        this.listingDAO = new ListingDAO();
        this.cardDAO = new CardDAO();
        this.permissionService = new PermissionService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action == null){
            action = "";
        }

        switch (action){
            case "insert":
                insertListings(req, resp);
                break;
            case "update":
                updateListings(req, resp);
                break;
            default:
                RequestDispatcher dispatcher = req.getRequestDispatcher("seller/my_listing.jsp");
                dispatcher.forward(req, resp);
                break;
        }
    }

    private void updateListings(HttpServletRequest req, HttpServletResponse resp) {
        ListingService listingService = new ListingService(req, resp);
        listingService.updateListing();
    }

    private void insertListings(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListingService listingService = new ListingService(req, resp);
        listingService.addListing();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }

        switch (action){
            case "":
                showListings(req, resp);
                break;
            case "add":
                addListingForm(req, resp);
                break;
            case "view":
                viewListing(req, resp);
                break;
            case "edit":
                editListingForm(req, resp);
                break;
            case "del":
                deleteListing(req, resp);
                break;
            case "fetchCard":
                fetchCard(req, resp);
                break;
            default:
                RequestDispatcher dispatcher = req.getRequestDispatcher("frontend/my_listing.jsp");
                dispatcher.forward(req, resp);
                break;
        }


    }

    private void fetchCard(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
    	String serialNumber = (String) req.getParameter("serialNumber");
    	Card card = cardDAO.get(serialNumber);
    	
    	 String jsonResponse = String.format(
    	            "{\"cardName\": \"%s\", \"marketprice\": \"%s\", \"description\": \"%s\", \"imageUrl\":\"%s\", \"game\":\"%s\"}",
    	            escapeJson(card.getCardName()),
    	            escapeJson(String.valueOf(card.getMarketprice())),
    	            escapeJson(card.getDescription()),
    	            escapeJson(card.getImageUrl()),
    	            escapeJson(card.getGame())
    	        );
    	        
    	        resp.setContentType("application/json");
    	        resp.setCharacterEncoding("UTF-8");
    	        resp.getWriter().write(jsonResponse);
	}

 // Helper method to escape JSON special characters
    private String escapeJson(String value) {
        if (value == null) return "";
        return ((String) value).replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\b", "\\b")
                    .replace("\f", "\\f")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }

	private void deleteListing(HttpServletRequest req, HttpServletResponse resp) {
        ListingService listingService = new ListingService(req, resp);
        listingService.deleteListing();
    }

    private void editListingForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);

        if(session != null){
            User user = (User) session.getAttribute("user");
            if ((user == null) || (!permissionService.hasPermission(user.getRole(), ADD_LISTING_PERMISSION))) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
                return;
            }
            Integer listingId = Integer.valueOf(req.getParameter("listingId"));
            Listing listing = listingDAO.get(listingId);
            String registerForm = "seller/edit_listing.jsp";
            req.setAttribute("action", "Edit");
            req.setAttribute("listing", listing);
            RequestDispatcher dispatcher = req.getRequestDispatcher(registerForm);
            dispatcher.forward(req, resp);
        }
    }

    private void viewListing(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void addListingForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if(session != null){
            User user = (User) session.getAttribute("user");
            if ((user == null) || (!permissionService.hasPermission(user.getRole(), ADD_LISTING_PERMISSION))) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
                return;
            }
            String addListingForm = "seller/add_listing.jsp";
            req.setAttribute("action", "Add");
            RequestDispatcher dispatcher = req.getRequestDispatcher(addListingForm);
            dispatcher.forward(req, resp);
        }
    }

    private void showListings(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        
        if(session != null){
        	int page = 1;
            int pageSize = 10;
            
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
    		
            int start = (page - 1) * pageSize;
            User user = (User) session.getAttribute("user");
            long totalListing = listingDAO.listingCountBySeller(user.getUserId());
            int totalPages = (int) Math.ceil((double) totalListing / pageSize);
            List<Listing> sellerListing = listingDAO.listSellerListing(user.getUserId(), start, pageSize);
            
            req.setAttribute("listOfListings", sellerListing);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);
            RequestDispatcher dispatcher = req.getRequestDispatcher("seller/my_listing.jsp");
            dispatcher.forward(req, resp);
        }else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("/");
            dispatcher.forward(req, resp);
        }
    }
}
