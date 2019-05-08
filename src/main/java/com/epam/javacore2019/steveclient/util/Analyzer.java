package com.epam.javacore2019.steveclient.util;

import com.epam.javacore2019.steveclient.common.Canvas;
import com.epam.javacore2019.steveclient.common.ConsoleCanvas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

public class Analyzer {
    private Canvas canvas = new ConsoleCanvas(5, 5);

    private Properties properties = new Properties();
    private String urlForAccessingTriggers = null;
    private Parser parser;

    {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {

            properties.load(input);
            String port = properties.getProperty("steveServerPort");
            String host = properties.getProperty("host");
            urlForAccessingTriggers = host + ":" + port + "/getTriggers";

        } catch (IOException e) {
            e.printStackTrace();
        }

        parser = new Parser(Utils.getTriggersFromServer(urlForAccessingTriggers));
    }

    public String[] getCommandArrayAfterAnalysis(String commandRequest) {

        List<String[]> commandList = parser.parseString(commandRequest);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String WRONG_INPUT_WARNING = "Wrong input";

        if (commandList == null) {
            canvas.printString("I don't understand");
        } else if (commandList.size() == 1) {
            String output;
            if (commandList.get(0)[1] == null) {
                output = "Execute the command " + commandList.get(0)[0] + "?";
            } else {
                output = "Execute the command " + commandList.get(0)[0] + " with parameter " + commandList.get(0)[1] + "?";
            }
            canvas.printString(output);

            try {

                while (true) {
                    String input = reader.readLine().toLowerCase();

                    switch (input) {
                        case "yes":
                        case "":
                            return commandList.get(0);
                        case "no":
                            return null;
                        default:
                            canvas.printString(WRONG_INPUT_WARNING);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            canvas.printString("I found several commands:");
            int size = commandList.size();
            for (int i = 0; i < size; i++) {
                String output;
                if (commandList.get(i)[1] == null) {
                    output = (i + 1) + ") " + "Command " + commandList.get(i)[0];
                } else {
                    output = (i + 1) + ") " + "Command " + commandList.get(i)[0] + " with parameter " + commandList.get(i)[1];
                }
                canvas.printString(output);
            }
            canvas.printString("0) Do nothing");
            canvas.printString("Enter which U want:");

            try {

                while (true) {
                    String input = reader.readLine().toLowerCase();

                    int item;
                    try {
                        item = Integer.valueOf(input);
                    } catch (NumberFormatException e) {
                        canvas.printString(WRONG_INPUT_WARNING);
                        continue;
                    }

                    try {
                        return commandList.get(item - 1);
                    } catch (IndexOutOfBoundsException e) {
                        if (item == 0) {
                            return null;
                        } else {
                            canvas.printString(WRONG_INPUT_WARNING);
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
