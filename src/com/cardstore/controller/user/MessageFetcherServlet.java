package com.cardstore.controller.user;

import java.io.IOException;
import java.util.List;

import com.cardstore.entity.Message;
import com.cardstore.entity.User;
import com.cardstore.service.MessageService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/messageFetcher")
public class MessageFetcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageService messageService = new MessageService(request, response);
		
        String action = request.getParameter("action");
        User loggedInUser = (User) request.getSession().getAttribute("user");
        Integer userId = loggedInUser.getUserId();
        
        if ("getUsers".equals(action)) {
            // Fetch all users who have exchanged messages with logged-in user
            List<User> users = messageService.findUsersWithMessages(userId);
            response.setContentType("text/html");
            for (User user : users) {
                response.getWriter().println("<div class='list-group-item user-item' data-userid='" + user.getUserId() + "'>" + user.getFirstName() + " " + user.getLastName() + "</div>");
            }
        } else if ("getSubjects".equals(action)) {
            // Fetch subjects exchanged with the selected user
            Integer selectedUserId = Integer.parseInt(request.getParameter("userId"));
            List<String> subjects = messageService.getSubjectsWithUser(userId, selectedUserId);
            response.setContentType("text/html");
            for (String subject : subjects) {
                response.getWriter().println("<div class='list-group-item subject-item' data-userid='" + selectedUserId + "' data-subject='" + subject + "'>" + subject + "</div>");
            }
        } else if ("getMessages".equals(action)) {
            // Fetch messages exchanged on the selected subject
            Integer selectedUserId = Integer.parseInt(request.getParameter("userId"));
            String subject = request.getParameter("subject");
            List<Message> messages = messageService.getMessagesBySubject(userId, selectedUserId, subject);
            response.setContentType("text/html");
            for (Message message : messages) {
                String messageClass = message.getSender().getUserId().equals(userId) ? "sent-message" : "received-message";
                response.getWriter().println("<div class='message-wrapper'><div class='" + messageClass + "'><strong>" + message.getSender().getFirstName() + ":</strong> " + message.getContent() + "</div></div>");
            }
        }
    }
}
