package com.epam.javacore2019.steveclient;

import com.epam.javacore2019.steveclient.util.Analyzer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class SteveClient implements Runnable {

    private Analyzer analyzer = new Analyzer();
    private Properties properties = new Properties();
    private String urlForSendingCommand;

    {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {

            properties.load(input);
            String port = properties.getProperty("steveServerPort");
            String host = properties.getProperty("host");
            urlForSendingCommand = host + ":" + port + "/command";

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){

            String inputFromConsole;
            while (!(inputFromConsole = reader.readLine()).equals("exit")) {

                String commandAfterAnalysis = analyzer.getCommandArrayAfterAnalysis(inputFromConsole);

                if (commandAfterAnalysis != null) {

                    HttpURLConnection connection = getConnectionForCommand(commandAfterAnalysis, urlForSendingCommand);
                    connection.getResponseCode();
                    BufferedReader reader1 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    System.out.println(reader1.readLine());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private HttpURLConnection getConnectionForCommand(String commandString, String urlForSendingCommand) {

        HttpURLConnection connection = null;

        try {

            URL commandRequest = new URL(urlForSendingCommand);
            connection = (HttpURLConnection) commandRequest.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(commandString);
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
