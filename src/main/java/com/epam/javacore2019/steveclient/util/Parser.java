package com.epam.javacore2019.steveclient.util;

import java.util.ArrayList;
import java.util.List;


public class Parser {

    private List<Trigger> triggers;

    public Parser (List<Trigger> triggers) {
        this.triggers = triggers;
    }

    /**
     * Parses typed string
     * @param commandRequest string from console
     * @return List of arrays of strings with found command and parameters.
     */
     List<String[]> parseString(String commandRequest) {
        String input = commandRequest.toLowerCase();
        List<String[]> result = new ArrayList<>();

        for(Trigger trigger : triggers) {
            boolean found = false;
            String[] command = new String[]{null, null};

            if (input.contains(trigger.getKey())) {
                found = true;
                command[0] = trigger.getKey();
            } else {
                if (trigger.getWords() != null) {
                    for (String word : trigger.getWords()) {
                        if (input.contains(word)) {
                            found = true;
                            command[0] = trigger.getKey();
                            break;
                        }
                    }
                }
            }

            if (found && trigger.getParams() != null) {
                for (String param : trigger.getParams()) {
                    if (input.contains(param)) {
                        command[1] = param;
                        break;
                    }
                }
            }

            if (command[0] != null) {
                result.add(command);
            }
        }

        if (result.size() == 0) {
            return null;
        } else {
            return result;
        }
    }
}
