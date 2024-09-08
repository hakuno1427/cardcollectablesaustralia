package com.cardstore.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Index;

@Entity
@Table(name = "message",
indexes = {
        @Index(name = "idx_sender", columnList = "sender_id"),
        @Index(name = "idx_receiver", columnList = "receiver_id"),
        @Index(name = "idx_subject", columnList = "subject")
    })
@NamedQueries({
        @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m ORDER BY m.sentDate DESC"),
        @NamedQuery(name = "Message.findBySubject", query = "SELECT m FROM Message m WHERE m.subject = :subject ORDER BY m.sentDate DESC"),
        @NamedQuery(name = "Message.findBySenderAndReceiver", query = "SELECT m FROM Message m WHERE m.sender.userId = :senderId AND m.receiver.userId = :receiverId ORDER BY m.sentDate DESC"),
        @NamedQuery(name = "Message.findWhoExchangedWithUser", query = "SELECT DISTINCT u FROM Message m JOIN m.sender u WHERE m.receiver.userId = :userId UNION SELECT DISTINCT u FROM Message m JOIN m.receiver u WHERE m.sender.userId = :userId"),
        @NamedQuery(name = "Message.findSubjectsByUserPair", query = "SELECT DISTINCT m.subject FROM Message m WHERE (m.sender.userId = :userId1 AND m.receiver.userId = :userId2) OR (m.sender.userId = :userId2 AND m.receiver.userId = :userId1)"),
        @NamedQuery(name = "Message.findMessagesByUserAndSubject", query = "SELECT m FROM Message m WHERE ((m.sender.userId = :userId1 AND m.receiver.userId = :userId2) OR (m.sender.userId = :userId2 AND m.receiver.userId = :userId1)) AND m.subject = :subject ORDER BY m.sentDate ASC")
        
         
})
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer messageId;
    private User sender;
    private User receiver;
    private String content;
    private String subject;
    private Date sentDate;
    private boolean isRead;

    public Message() {
    }

    public Message(User sender, User receiver, String content, String subject, Date sentDate, boolean isRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.subject = subject;
        this.sentDate = sentDate;
        this.isRead = isRead;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId", unique = true, nullable = false)
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Column(name = "content", nullable = false, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "subject", nullable = true, length = 100)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "sentDate", nullable = false)
    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    @Column(name = "isRead", nullable = false)
    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, sender, receiver, content, subject, sentDate, isRead);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        return Objects.equals(messageId, other.messageId) && Objects.equals(sender, other.sender)
                && Objects.equals(receiver, other.receiver) 
                && Objects.equals(content, other.content) && Objects.equals(subject, other.subject)
                && Objects.equals(sentDate, other.sentDate) && isRead == other.isRead;
    }

    @Override
    public String toString() {
        return "Message [messageId=" + messageId + ", sender=" + sender + ", receiver=" + receiver
                + ", content=" + content + ", subject=" + subject + ", sentDate=" + sentDate + ", isRead=" + isRead + "]";
    }
}
