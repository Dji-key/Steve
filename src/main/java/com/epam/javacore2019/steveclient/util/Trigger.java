package com.epam.javacore2019.steveclient.util;

import java.util.Objects;

/**
 * Contains words for invocation command and general key for them all. Is used like storage in command classes.
 */
public class Trigger {

    private String key;
    private String[] params;
    private String[] strictParams;
    private String[] words;

    public Trigger(String key, String[] params, String[] strictParams, String... words) {
        this.key = key;
        this.params = params;
        this.strictParams = strictParams;
        this.words = words;
    }

    String getKey() {
        return key;
    }

    String[] getWords() {
        if (words == null) {
            return null;
        } else {
            return words.clone();
        }
    }

    String[] getParams() {
        if (params == null) {
            return null;
        } else {
            return params.clone();
        }
    }

    String[] getStrictParams() {
        if (strictParams == null) {
            return null;
        } else {
            return strictParams.clone();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trigger trigger = (Trigger) o;
        return Objects.equals(key, trigger.key);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key);
    }
}
