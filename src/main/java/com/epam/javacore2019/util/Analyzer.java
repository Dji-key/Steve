package com.epam.javacore2019.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Analyzer {
    private Parser parser = new Parser();

    public String[] analyze(String commandRequest) {
        List<String[]> commandList = parser.parseString(commandRequest);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Add canvas EVERYWHERE
        if (commandList == null) {
            System.out.println("I don't understand");
        } else if (commandList.size() == 1) {
            System.out.print("Execute the command " + commandList.get(0)[0]);
            if (commandList.get(0)[1] == null) {
                System.out.println(" ?");
            } else {
                System.out.println(" with parameter " + commandList.get(0)[1] + " ?");
            }

            try {

                while (true) {
                    String input = reader.readLine().toLowerCase();
                    if (input.equals("yes") || input.equals("")) {
                        return commandList.get(0);
                    } else if (input.equals("no")) {
                        return null;
                    } else {
                        System.out.println("incorrect input");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("I found several commands:");
            int size = commandList.size();
            for (int i = 0; i < size; i++) {
                System.out.print((i + 1) + ") ");
                System.out.print("Command " + commandList.get(i)[0]);
                if (commandList.get(i)[1] == null) {
                    System.out.println();
                } else {
                    System.out.println(" with parameter " + commandList.get(i)[1]);
                }
            }
            System.out.println("0) Do nothing");
            System.out.println("Enter which U want:");

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
                        return commandList.get(item);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("incorrect input");
                        continue;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
