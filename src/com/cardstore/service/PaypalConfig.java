package com.cardstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PaypalConfig {

    private static String clientId;
    private static String clientSecret;
    private static String mode;

    static {
        try (InputStream input = PaypalConfig.class.getClassLoader().getResourceAsStream("paypal.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find paypal.properties");
            }

            Properties prop = new Properties();
            prop.load(input);

            clientId = prop.getProperty("paypal.client.id");
            clientSecret = prop.getProperty("paypal.client.secret");
            mode = prop.getProperty("paypal.mode");

            if (clientId == null || clientSecret == null || mode == null) {
                throw new RuntimeException("Missing PayPal configuration in properties file");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error loading PayPal properties", ex);
        }
    }

    public static String getClientId() {
        return clientId;
    }

    public static String getClientSecret() {
        return clientSecret;
    }

    public static String getMode() {
        return mode;
    }
}
