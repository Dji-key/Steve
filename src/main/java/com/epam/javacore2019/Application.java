package com.epam.javacore2019;

import com.epam.javacore2019.util.Steve;

public class Application {

    public static void main(String[] args) {
        Thread thread = new Thread(new Steve());
        thread.start();
    }
}