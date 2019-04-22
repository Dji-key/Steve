package com.epam.javacore2019.util;

import com.epam.javacore2019.common.Canvas;
import com.epam.javacore2019.common.ConsoleCanvas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Analyzer {
    private Parser parser = new Parser();
    private Canvas canvas = new ConsoleCanvas();

    private String wrongInputWarning = "Wrong input";

    public String[] analyze(String commandRequest) {
        List<String[]> commandList = parser.parseString(commandRequest);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Add canvas EVERYWHERE
        if (commandList == null) {
            canvas.draw("I don't understand");
        } else if (commandList.size() == 1) {
            String output;
            if (commandList.get(0)[1] == null) {
                output = "Execute the command " + commandList.get(0)[0] + " ?";
            } else {
                output = "Execute the command " + commandList.get(0)[0] + " with parameter " + commandList.get(0)[1] + " ?";
            }
            canvas.draw(output);

            try {

                while (true) {
                    String input = reader.readLine().toLowerCase();
                    if (input.equals("yes") || input.equals("")) {
                        return commandList.get(0);
                    } else if (input.equals("no")) {
                        return null;
                    } else {
                        canvas.draw(wrongInputWarning);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            canvas.draw("I found several commands:");
            int size = commandList.size();
            for (int i = 0; i < size; i++) {
                String output;
                if (commandList.get(i)[1] == null) {
                    output = (i + 1) + ") " + "Command " + commandList.get(i)[0];
                } else {
                    output = (i + 1) + ") " + "Command " + commandList.get(i)[0] + " with parameter " + commandList.get(i)[1];
                }
                canvas.draw(output);
            }
            canvas.draw("0) Do nothing");
            canvas.draw("Enter which U want:");

            try {

                while (true) {
                    String input = reader.readLine().toLowerCase();

                    int item;
                    try {
                        item = Integer.valueOf(input);
                    } catch (NumberFormatException e) {
                        System.out.println("incorrect input");
                        continue;
                    }

                    try {
                        return commandList.get(item - 1);
                    } catch (IndexOutOfBoundsException e) {
                        if (item == 0) {
                            return null;
                        } else {
                            System.out.println("incorrect input");
                            continue;
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
