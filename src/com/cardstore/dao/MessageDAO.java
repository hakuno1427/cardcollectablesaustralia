package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.Message;
import com.cardstore.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


public class MessageDAO extends JpaDAO<Message>  implements GenericDAO<Message>  {

	@Override
	public Message create(Message message) {
		return super.create(message);
	}

	@Override
	public Message update(Message message) {
		return super.update(message);
	}

	@Override
	public Message get(Object messageId) {
		return super.find(Message.class, messageId);
	}

	@Override
	public void delete(Object messageId) {
		super.delete(Message.class, messageId);		
	}

	@Override
	public List<Message> listAll() {
		return super.findWithNamedQuery("Message.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Message.countAll");
	}
	
	
	public List<User> findUsersWithMessages(Integer userId) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<User> query = entityManager.createNamedQuery("Message.findWhoExchangedWithUser", User.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }
	
	// Find all subjects exchanged between two users
    public List<String> findSubjectsByUserPair(Integer userId, Integer selectedUserId) {
		EntityManager entityManager = getEntityManager();
        TypedQuery<String> query = entityManager.createNamedQuery("Message.findSubjectsByUserPair", String.class);
        query.setParameter("userId1", userId);
        query.setParameter("userId2", selectedUserId);
        return query.getResultList();
    }

    // Find all messages between two users on a specific subject
    public List<Message> findMessagesByUserAndSubject(Integer userId, Integer selectedUserId, String subject) {
		EntityManager entityManager = getEntityManager();
        TypedQuery<Message> query = entityManager.createNamedQuery("Message.findMessagesByUserAndSubject", Message.class);
        query.setParameter("userId1", userId);
        query.setParameter("userId2", selectedUserId);
        query.setParameter("subject", subject);
        return query.getResultList();
    }
}
