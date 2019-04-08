package com.epam.javacore2019.util;

import java.util.Objects;

/**
 * Contains words for invocation command and general key for them all. Is used like storage in command classes.
 */
public class Trigger {

    private String key;
    private String[] words;

    public Trigger(String key, String... words) {
        this.key = key;
        this.words = words;
    }

    public String getKey() {
        return key;
    }

    public String[] getWords() {
        return words.clone();
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
