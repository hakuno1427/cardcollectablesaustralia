package com.cardstore.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class EmailService {

    private static Properties mailProperties = new Properties();

    // Load mail properties from an external file or classpath
    static {
        try (InputStream input = EmailService.class.getClassLoader().getResourceAsStream("mail.properties")) {
            mailProperties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Create a session with email credentials
    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", mailProperties.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", mailProperties.getProperty("mail.smtp.port"));
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Use getDefaultInstance instead of getInstance
        return Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailProperties.getProperty("mail.username"),
                        mailProperties.getProperty("mail.password"));
            }
        });
    }

    public void notifyMessage(String to, String senderName, String messageSubject, String messageBody) {
        try {
            // Load email template
            String template = loadEmailTemplate("email_template.html");

            // Replace placeholders with actual values
            String emailContent = template
                .replace("{senderName}", senderName)
                .replace("{messageSubject}", messageSubject)
                .replace("{messageBody}", messageBody)
                .replace("{currentYear}", String.valueOf(java.time.Year.now().getValue()));

            // Create a MimeMessage object
            MimeMessage message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(mailProperties.getProperty("mail.smtp.user")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(senderName + "via Card Collectables Australia");
            message.setContent(emailContent, "text/html");

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String loadEmailTemplate(String filename) {
        StringBuilder contentBuilder = new StringBuilder();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
