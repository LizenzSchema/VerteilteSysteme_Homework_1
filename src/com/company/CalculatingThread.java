package com.company;

public class CalculatingThread extends Thread {
    long lowerBound, upperBound;
    int threadNumber;

    public CalculatingThread(long lowerBound, long upperBound, int threadNumber) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.threadNumber = threadNumber;
    }

    public void run() {
        for (long i = upperBound; i >= lowerBound && i > 1; i--) {
            if (Main.N % i == 0) {
                Main.divisors.add(i);
            }
        }
    }
}

