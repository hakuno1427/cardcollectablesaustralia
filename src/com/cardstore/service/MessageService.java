package com.cardstore.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.cardstore.dao.MessageDAO;
import com.cardstore.dao.UserDAO;
import com.cardstore.entity.Message;
import com.cardstore.entity.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MessageService {

	private MessageDAO messageDAO;
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public MessageService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.messageDAO = new MessageDAO();
		this.userDAO = new UserDAO();
	}

	// Fetch users who have exchanged messages with the logged-in user
	public List<User> findUsersWithMessages(Integer userId) {
		return messageDAO.findUsersWithMessages(userId);
	}

	// Fetch subjects exchanged between the logged-in user and the selected user
	public List<String> getSubjectsWithUser(Integer userId, Integer selectedUserId) {
		return messageDAO.findSubjectsByUserPair(userId, selectedUserId);
	}

	// Fetch messages by subject between two users
	public List<Message> getMessagesBySubject(Integer userId, Integer selectedUserId, String subject) {
		return messageDAO.findMessagesByUserAndSubject(userId, selectedUserId, subject);
	}

	public Message sendMessage(Integer receiverId, String subject, String content) {
		User sender = (User) request.getSession().getAttribute("user");
		User receiver = userDAO.get(receiverId);

		Message newMessage = new Message();
		newMessage.setSender(sender);
		newMessage.setReceiver(receiver);
		newMessage.setSubject(subject);
		newMessage.setContent(content);
		newMessage.setSentDate(new java.util.Date()); 
		
		// Save the message to the database
		messageDAO.create(newMessage);

		return newMessage;
	}
	
	public Message sendAdminMessage(Integer receiverId, String subject, String content) {
		User sender = userDAO.findByEmail("admin@cca.com");
		User receiver = userDAO.get(receiverId);

		Message newMessage = new Message();
		newMessage.setSender(sender);
		newMessage.setReceiver(receiver); // Assuming userId is the ID of the recipient
		newMessage.setSubject(subject);
		newMessage.setContent(content);
		newMessage.setSentDate(new java.util.Date()); // Set the current date

		// Save the message to the database
		messageDAO.create(newMessage);

		return newMessage;
	}
}
