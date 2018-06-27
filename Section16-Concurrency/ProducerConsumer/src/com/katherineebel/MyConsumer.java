package com.katherineebel;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import static com.katherineebel.Main.EOF;

public class MyConsumer implements Runnable {
    private final ArrayBlockingQueue<String> buffer;
    private String color;

    MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    public void run() {
        while (true) {
            synchronized (buffer) {
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    if (EOF.equals(buffer.peek())) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.take());
                    }
                } catch (InterruptedException e) {
                    System.out.println("MyConsumer interrupted");
                }
            }
        }
    }
}
