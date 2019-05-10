package com.epam.javacore2019.steveclient.util;

import java.util.ArrayList;
import java.util.LinkedList;
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
     List<List<String>> parseString(String commandRequest) {
        commandRequest = commandRequest.toLowerCase();
        String[] separatedCommandRequest = commandRequest.split(" ");
        List<List<String>> commandList = new ArrayList<>();

        for(Trigger trigger : triggers) {
            boolean found = false;
            List<String> command = new ArrayList<>();

            if (commandRequest.contains(trigger.getKey())) {
                found = true;
                command.add(trigger.getKey());
            } else {
                if (trigger.getWords() != null) {
                    for (String word : trigger.getWords()) {
                        if (commandRequest.contains(word)) {
                            found = true;
                            command.add(trigger.getKey());
                            break;
                        }
                    }
                }
            }

            if (!found) {
                continue;
            }

            if (trigger.getParams() != null) {
                for (String param : trigger.getParams()) {
                    if (commandRequest.contains(param)) {
                        command.add(param);
                        break;
                    }
                }
            } else if (trigger.getStrictParams() != null) {
                for (String strictParam : trigger.getStrictParams()) {
                    int length = separatedCommandRequest.length;
                    for (int i = 0; i < length; i++) {
                        if (strictParam.equals(separatedCommandRequest[i]) && i + 1 < length) {
                            command.add(strictParam + " " + separatedCommandRequest[i + 1]);
                        }
                    }
                }
            }

            commandList.add(command);
        }

        if (commandList.size() == 0) {
            return null;
        } else {
            return commandList;
        }
    }
}
