package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    static int THREADCOUNT = 1;
    static long N;
    static long calcs = 0;
    static ExecutorService threadPool;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an integer: ");
        N = scanner.nextLong();
        System.out.println("Enter a number of threads: ");
        THREADCOUNT = scanner.nextInt();

        ConcurrentLinkedQueue<SearchInterval> taskQueue = new ConcurrentLinkedQueue<>();
        long start = System.currentTimeMillis();

        double sqrtN = Math.sqrt(N);
        long taskSize = (long) (sqrtN / THREADCOUNT) / 10;

        for (int i = 0; (i + 1) * taskSize < sqrtN; i++) {
            taskQueue.add(new SearchInterval((i * taskSize), ((i + 1) * taskSize)));
        }

        threadPool = Executors.newFixedThreadPool(THREADCOUNT);

        for (int i = 0; i < THREADCOUNT; i++) {
            threadPool.submit(new CalculatingThread(taskQueue));
        }

        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Finished in " + (timeElapsed / 1) + " milliseconds");
    }
}
