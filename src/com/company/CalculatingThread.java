package com.company;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CalculatingThread extends Thread {
    ConcurrentLinkedQueue<SearchInterval> taskQueue;

    public CalculatingThread(ConcurrentLinkedQueue<SearchInterval> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void run() {
        while (true) {
            SearchInterval interval = taskQueue.poll();
            if (interval == null) {
                if(taskQueue.isEmpty()){
                    Main.threadPool.shutdown();
                }
                return;
            }
            for (long i = (interval.start == 0 ? 2 : interval.start) ; i <= interval.end; i++) {
                Main.calcs++;
                if (Main.N % i == 0 && Main.N != i && i > 2) {
                    System.out.println("Divisor found: " + i);
                    taskQueue.removeIf(searchInterval -> true);
                    break;
                }
            }
        }
    }
}

