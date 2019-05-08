package com.epam.javacore2019.steveclient;

import com.epam.javacore2019.steveclient.util.Analyzer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class SteveClient implements Runnable {

    private Analyzer analyzer = new Analyzer();
    private Properties properties = new Properties();
    private String commandUrl;

    {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {

            properties.load(input);
            String port = properties.getProperty("steveServerPort");
            String host = properties.getProperty("host");
            commandUrl = host + ":" + port + "/command";

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){

            String input;
            while (!(input = reader.readLine()).equals("exit")) {

                String[] command = analyzer.getCommandArrayAfterAnalysis(input);

                if (command != null) {

                    String output;
                    if (command[1] != null) {
                        output = command[0] + " " + command[1];
                    } else {
                        output = command[0];
                    }
                    URL commandRequest = new URL(commandUrl);
                    HttpURLConnection connection = (HttpURLConnection)commandRequest.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    outputStream.writeBytes(output);
                    outputStream.close();
                    connection.getResponseCode();
                    connection.disconnect();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
