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
    private String urlForGettingTriggers = null;
    private Parser parser;

    {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {

            properties.load(input);
            String port = properties.getProperty("steveServerPort");
            String host = properties.getProperty("host");
            urlForGettingTriggers = host + ":" + port + "/getTriggers";

        } catch (IOException e) {
            e.printStackTrace();
        }

        parser = new Parser(Utils.getTriggersFromServer(urlForGettingTriggers));
    }

    public String getCommandArrayAfterAnalysis(String commandRequest) {

        List<List<String>> commandList = parser.parseString(commandRequest);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String WRONG_INPUT_WARNING = "Wrong input";

        if (commandList == null) {
            canvas.printString("I don't understand");
        } else if (commandList.size() == 1) {

            List<String> command = commandList.get(0);
            StringBuilder outputString = new StringBuilder("Execute the command " + command.get(0));
            if (command.size() == 1) {
                outputString.append("?");
            } else {
                outputString.append(" with parameters: ").append(command.get(1));
                int size = command.size();
                for (int i = 2; i < size; i++) {
                    outputString.append(" and ").append(command.get(i));
                }
                outputString.append("?");
            }
            canvas.printString(outputString.toString());

            try {

                StringBuilder commandAsString = new StringBuilder();
                for (String string : command) {
                    commandAsString.append(string).append(" ");
                }
                while (true) {
                    String input = reader.readLine().toLowerCase();

                    switch (input) {
                        case "yes":
                        case "":
                            return commandAsString.toString().trim();
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
            canvas.printString("I have found several commands:");
            int count = 1;
            for (List command : commandList) {
                StringBuilder outputString = new StringBuilder(count++ + ") Command ");
                outputString.append(command.get(0));
                if (command.size() > 1) {
                    outputString.append(" with parameters: ").append(command.get(1));
                    int size = command.size();
                    for (int i = 2; i < size; i++) {
                        outputString.append(" and ").append(command.get(i));
                    }
                }
                canvas.printString(outputString.toString());
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
                        StringBuilder commandAsString = new StringBuilder();
                        for (String string : commandList.get(item - 1)) {
                            commandAsString.append(string).append(" ");
                        }
                        return commandAsString.toString().trim();
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
