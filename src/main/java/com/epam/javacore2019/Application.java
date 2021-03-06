package com.epam.javacore2019;

import com.epam.javacore2019.dbserver.data.Table;
import com.epam.javacore2019.steveclient.SteveClient;
import com.epam.javacore2019.steveserver.SteveServer;
import com.epam.javacore2019.webservice.WebClientApp;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        try {
            new SteveServer().start();
            new WebClientApp().start();
            new SteveClient().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}