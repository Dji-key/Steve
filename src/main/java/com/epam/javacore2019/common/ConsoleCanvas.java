package com.epam.javacore2019.common;

public class ConsoleCanvas implements Canvas {
    @Override
    public void draw(String text) {
        System.out.println(text);
    }
}
