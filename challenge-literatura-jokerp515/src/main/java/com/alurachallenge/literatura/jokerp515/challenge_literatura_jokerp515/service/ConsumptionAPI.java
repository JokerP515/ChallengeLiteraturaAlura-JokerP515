package com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsumptionAPI {
    private static final Logger LOGGER = Logger.getLogger(ConsumptionAPI.class.getName());

    public String getData(String url) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occurred while sending request", e);
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "InterruptedException occurred while sending request", e);
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        if (response != null) {
            //LOGGER.log(Level.INFO, "Response received: {0}", response.body());
            return response.body();
        } else {
            LOGGER.log(Level.WARNING, "No response received");
            return null;
        }
    }
}