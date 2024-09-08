package com.cardstore.controller.user;

import java.io.IOException;

import com.cardstore.entity.Message;
import com.cardstore.service.MessageService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/send_message")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageService messageService = new MessageService(request, response);
		
		Integer receiverId = Integer.parseInt(request.getParameter("userId"));
        String subject = request.getParameter("subject");
        String content = request.getParameter("messageContent");
        
		Message newMessage = messageService.sendMessage(receiverId, subject, content);
		
		// Send a response to update the message box
        response.setContentType("text/html");
        request.setAttribute("message", newMessage);
        response.getWriter().println("<div>" + newMessage.getSender().getFirstName() + ": " + newMessage.getContent() + "</div>");
    }
}
