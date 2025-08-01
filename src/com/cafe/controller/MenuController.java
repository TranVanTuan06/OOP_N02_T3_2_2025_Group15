package com.cafe.controller;

import com.cafe.menu.Drink;
import com.cafe.menu.MenuManager;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

public class MenuController {
    private static MenuManager manager = new MenuManager();

    public static void startServer() throws IOException {
        manager.addDrink(new Drink("Espresso", 30000));
        manager.addDrink(new Drink("Latte", 40000));
        manager.addDrink(new Drink("Trà đào", 35000));

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/menu", new MenuHandler());
        server.setExecutor(null);
        System.out.println("Server đang chạy tại http://localhost:8080/menu");
        server.start();
    }

    static class MenuHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            StringBuilder response = new StringBuilder();
            response.append("<html><head><title>Menu</title></head><body>");
            response.append("<h1>Menu Quán Cafe</h1><table border='1'>");
            response.append("<tr><th>Tên đồ uống</th><th>Giá (VND)</th></tr>");

            List<Drink> drinks = manager.getMenu();
            for (Drink d : drinks) {
                response.append("<tr><td>")
                        .append(d.getName())
                        .append("</td><td>")
                        .append(d.getPrice())
                        .append("</td></tr>");
            }

            response.append("</table></body></html>");

            byte[] bytes = response.toString().getBytes();
            exchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }
}
