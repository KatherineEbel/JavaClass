package com.katherineebel;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static com.katherineebel.Main.EOF;

public class MyConsumer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    public void run() {
        int count = 0;
        while (true) {
            if (bufferLock.tryLock()) {
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    System.out.println(color + "The counter = " + count);
                    count = 0;
                    if (buffer.get(0).equals(EOF)) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.remove(0));
                    }
                } finally {
                    bufferLock.unlock();
                }
            }
        }
    }
}
