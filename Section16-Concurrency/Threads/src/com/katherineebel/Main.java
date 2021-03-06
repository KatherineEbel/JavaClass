package com.katherineebel;

import static com.katherineebel.ThreadColor.ANSI_GREEN;
import static com.katherineebel.ThreadColor.ANSI_PURPLE;
import static com.katherineebel.ThreadColor.ANSI_RED;

public class Main {

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE + "hello from the main thread");
        final Thread anotherThread = new AnotherThread();
        anotherThread.setName("== Another Thread ==");
        anotherThread.start(); // can only call start once on the thread
        System.out.println(ANSI_PURPLE + "Hello again from the main thread");

        // Anonymous Thread class (has to be started immediately
        new Thread() {
                public void run() {
                    System.out.println(ANSI_GREEN + "Hello from the anonymous class thread");
                }
        }.start();

        // pass subclass implementing Runnable to thread constructor
        Thread myRunnableThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println(ANSI_RED + "Hello from the anonymous class's implementation of run()");
                try {
                    anotherThread.join(2000);
                    System.out.println(ANSI_RED + "AnotherThread terminated, or timed out, so I'm running again");
                } catch (InterruptedException e) {
                    System.out.println(ANSI_RED + "I couldn't wait after all I was interrupted");
                }
            }
        });
        myRunnableThread.start();
//        anotherThread.interrupt();
        System.out.println(ANSI_PURPLE + "Another Hello from the main thread");
    }
}
