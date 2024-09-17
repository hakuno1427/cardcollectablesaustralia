package com.cardstore.controller.user;

import java.io.IOException;
import java.util.List;

import com.cardstore.entity.User;
import com.cardstore.service.MessageService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/my_messages")
public class MyMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageService messageService = new MessageService(request, response);

        // Assuming current user is stored in session
        User currentUser = (User) request.getSession().getAttribute("user");

        // Get the list of users who have exchanged messages with the current user
        List<User> userList = messageService.findUsersWithMessages(currentUser.getUserId());
        
        // Set the user list as a request attribute to forward to the JSP
        request.setAttribute("userList", userList);

        // Forward to JSP
        request.getRequestDispatcher("/frontend/my_messages.jsp").forward(request, response);
    }

	
}
