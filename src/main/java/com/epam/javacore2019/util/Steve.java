package com.epam.javacore2019.util;

import com.epam.javacore2019.state.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Steve implements Runnable {
    private final Context context = new Context();
    @Override
    public void run() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        synchronized (context) {
            try {
                String input;
                while (!(input = reader.readLine()).equals("exit")) {
                    new Thread(new Executor(input, context)).start();

                    try {
                        context.wait();
                    } catch (InterruptedException e) {
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
