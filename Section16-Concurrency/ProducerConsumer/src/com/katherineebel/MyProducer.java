package com.katherineebel;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class MyProducer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    MyProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};
        for (String num : nums) {
            try {
                System.out.println(color + "Adding..." + num);
                try {
                    buffer.put(num);
                } catch (InterruptedException e){
                    System.out.println("Producer was interrupted");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("MyProducer was interrupted");
            }
        }
        System.out.println(color + "Adding EOF and exiting...");
        try {
            buffer.put("EOF");
        } catch (InterruptedException e) {
            System.out.println("MyProducer was interrupted");
            e.printStackTrace();
        }
    }
}
