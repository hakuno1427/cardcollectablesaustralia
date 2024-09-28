package com.cardstore.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailService {
    private Properties emailProperties;

    public EmailService() {
        loadEmailProperties();
    }

    private void loadEmailProperties() {
        emailProperties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("mail.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find mail.properties");
            }
            emailProperties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Session createSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", emailProperties.getProperty("mail.smtp.host"));
        properties.put("mail.smtp.port", emailProperties.getProperty("mail.smtp.port"));

        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        emailProperties.getProperty("mail.smtp.username"),
                        emailProperties.getProperty("mail.smtp.password"));
            }
        });
    }

    public void sendVerificationEmail(String recipientEmail, String verificationToken) {
        try {
        	String verificationLink = "http://cardcollectiblesaustralia.store/verify?token=" + verificationToken;
            Message message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(emailProperties.getProperty("mail.from")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Email Verification");
            message.setText("Please verify your email by clicking the link: " + verificationLink);

            Transport.send(message);
            System.out.println("Verification email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
