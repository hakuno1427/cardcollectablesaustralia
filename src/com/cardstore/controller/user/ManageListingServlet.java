package com.cardstore.controller.user;

import com.cardstore.dao.ListingDAO;
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
 * @author Rutvi Created Date: 24/08/2024
 */

@WebServlet("/listings")
public class ManageListingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ListingDAO listingDAO = new ListingDAO();

    public static final String ADD_LISTING_PERMISSION = "ADD_LISTING";

    private PermissionService permissionService;
    public ManageListingServlet() {
        super();
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
                RequestDispatcher dispatcher = req.getRequestDispatcher("frontend/my_listing.jsp");
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
            default:
                RequestDispatcher dispatcher = req.getRequestDispatcher("frontend/my_listing.jsp");
                dispatcher.forward(req, resp);
                break;
        }


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
            String registerForm = "frontend/add_listing_form.jsp";
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
            String registerForm = "frontend/add_listing_form.jsp";
            req.setAttribute("action", "Add");
            RequestDispatcher dispatcher = req.getRequestDispatcher(registerForm);
            dispatcher.forward(req, resp);
        }
    }

    private void showListings(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if(session != null){
            User user = (User) session.getAttribute("user");
            List<Listing> sellerListing = listingDAO.listSellerListing(user.getUserId());
            req.setAttribute("listOfListings", sellerListing);
            RequestDispatcher dispatcher = req.getRequestDispatcher("frontend/my_listing.jsp");
            dispatcher.forward(req, resp);
        }else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("/");
            dispatcher.forward(req, resp);
        }
    }
}
