package com.epam.javacore2019.steveclient.common;

public class ConsoleCanvas implements Canvas {

    private int x;
    private int y;
    private char[][] canvas;

    public ConsoleCanvas(int x, int y) {
        this.x = x;
        this.y = y;
    }

    {
        canvas = new char[y][x];
    }

    @Override
    public void printString(String string) {
        System.out.println(string);
    }

    @Override
    public void draw() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(canvas[i][j]);
            }
            System.out.println();
        }
        clear();
    }

    @Override
    public void clear() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                canvas[i][j] = ' ';
            }
        }
    }
}
