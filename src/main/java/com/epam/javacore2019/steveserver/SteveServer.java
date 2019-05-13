package com.epam.javacore2019.steveserver;

import com.epam.javacore2019.steveserver.handler.HandlerCommand;
import com.epam.javacore2019.steveserver.handler.HandlerGetTriggers;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Properties;

public class SteveServer {
    private InetSocketAddress port = null;

    private Properties properties = new Properties();

    {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {

            properties.load(input);
            String portFromProperties = properties.getProperty("steveServerPort");
            int portAsInt = Integer.parseInt(portFromProperties);
            port = new InetSocketAddress(portAsInt);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {

        HttpServer server = HttpServer.create(port, 0);
        server.createContext("/getTriggers", new HandlerGetTriggers());

        server.createContext("/command", new HandlerCommand());

        server.start();

    }

}
