package com.epam.javacore2019.steveclient.common;

import com.epam.javacore2019.steveclient.util.Utils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class CommandResultHandler extends Thread {

    private InputStream resultInputStream;

    public CommandResultHandler(InputStream resultInputStream) {
        this.resultInputStream = resultInputStream;
    }

    @Override
    public void run() {

        try {
            waitForData(resultInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        handleResult(resultInputStream);

    }

    private void waitForData(InputStream inputStream) throws IOException, InterruptedException {
        while (inputStream.available() == 0) {
            Thread.sleep(100);
        }
    }

    private void handleResult(InputStream inputStream) {

        try {

            JSONObject jsonObjectFromCommand = Utils.readJsonFromStream(inputStream);
            String type = jsonObjectFromCommand.names().getString(0);
            System.out.println(jsonObjectFromCommand.get(type));
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
