package com.katherineebel;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static final String EOF = "EOF";
    public static void main(String[] args) {
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(6);
        ReentrantLock bufferLock = new ReentrantLock();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_CYAN);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_RED);

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_GREEN + "I'm being printed for the Callable class");
                return "This is the callable result";
            }
        });

        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Thread running the task was interrupted: " + e.getMessage());
        }
        executorService.shutdown();
    }
}
