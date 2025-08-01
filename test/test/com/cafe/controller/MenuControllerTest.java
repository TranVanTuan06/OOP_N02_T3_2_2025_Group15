package com.cafe.controller;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class MenuControllerTest {

    @Test
    public void testMenuPageResponse() throws Exception {
        new Thread(() -> {
            try {
                MenuController.startServer();
            } catch (Exception ignored) {}
        }).start();

        Thread.sleep(500);

        URL url = new URL("http://localhost:8080/menu");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        assertEquals(200, conn.getResponseCode());

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder html = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            html.append(line);
        }
        reader.close();

        String content = html.toString();
        assertTrue(content.contains("Espresso"));
        assertTrue(content.contains("Latte"));
        assertTrue(content.contains("Trà đào"));
    }
}

