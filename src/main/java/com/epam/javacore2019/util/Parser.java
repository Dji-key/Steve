package com.epam.javacore2019.util;

import java.util.Map;


public class Parser {

    private static Map<Trigger, String[]> names = NameRegister.INSTANCE.REGISTER;

    /**
     * Parses typed string
     * @param input string from console
     * @return array of 2 words: found key of command if exist [0] and found parameter if exist [1] otherwise - null.
     */
    public static String[] parseString(String input) {
        String[] result = new String[] {null, null};
        for (Map.Entry<Trigger, String[]> entry : names.entrySet()) {
            Trigger trigger = entry.getKey();
            if (input.contains(trigger.getKey())) {
                result[0] = trigger.getKey();
            } else {
                String[] words = trigger.getWords();
                for (String word : words) {
                    if (input.contains(word)) {
                        result[0] = trigger.getKey();
                        break;
                    }
                }
            }
            if (result[0] != null && entry.getValue() != null) {
                String[] params = entry.getValue();
                for (String param : params) {
                    if (input.contains(param)) {
                        result[1] = param;
                        return result;
                    }
                }
                return result;
            }
        }
        return result;
    }
}
