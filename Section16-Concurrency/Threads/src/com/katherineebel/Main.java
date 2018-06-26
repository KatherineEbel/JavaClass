package com.katherineebel;

import static com.katherineebel.ThreadColor.ANSI_GREEN;
import static com.katherineebel.ThreadColor.ANSI_PURPLE;

public class Main {

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE + "hello from the main thread");
        Thread anotherThread = new AnotherThread();
        anotherThread.start(); // can only call start once on the thread
        System.out.println(ANSI_PURPLE + "Hello again from the main thread");

        // Anonymous Thread class (has to be started immediately
        new Thread() {
                public void run() {
                    System.out.println(ANSI_GREEN + "Hello from the anonymous class thread");
                }
        }.start();

        System.out.println(ANSI_PURPLE + "Another Hello from the main thread");
    }
}
