package com.epam.javacore2019.webservice;

import com.epam.javacore2019.webservice.handler.HandlerCSS;
import com.epam.javacore2019.webservice.handler.HandlerHTML;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Properties;

public class WebClientApp {

    private InetSocketAddress port = null;

    private Properties properties = new Properties();

    {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {

            properties.load(input);
            String portFromProperties = properties.getProperty("steveWebClientPort");
            int portAsInt = Integer.parseInt(portFromProperties);
            port = new InetSocketAddress(portAsInt);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {

        HttpServer server = HttpServer.create(port, 0);

        server.createContext("/pages/", new HandlerHTML());

        server.createContext("/css/", new HandlerCSS());

        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                System.out.println(httpExchange.getRequestURI());
            }
        });

        server.start();
    }

}
