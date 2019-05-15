package com.epam.javacore2019.steveclient.common;

import com.epam.javacore2019.steveclient.util.Utils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommandResultHandler extends Thread {

    private HttpURLConnection connection;

    public CommandResultHandler(HttpURLConnection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {

        try {
            waitForData(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        handleResult(connection);

    }

    private void waitForData(HttpURLConnection connection) throws IOException, InterruptedException {
        InputStream inputStream = connection.getInputStream();
        while (inputStream.available() == 0) {
            Thread.sleep(100);
        }
    }

    private void handleResult(HttpURLConnection connection) {

        try {

            if (connection.getHeaderField("Content").equals("text")) {
                String output = Utils.readStringFromStream(connection.getInputStream());
                System.out.println(output);
                System.out.println();
            } else {
                JSONObject jsonObjectFromCommand = Utils.readJsonFromStream(connection.getInputStream());
                String type = jsonObjectFromCommand.names().getString(0);
                System.out.println(jsonObjectFromCommand.get(type));
                System.out.println();
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
