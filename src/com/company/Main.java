package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int THREADCOUNT = 1;
    static long N;
    static List<Long> divisors;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an integer: ");
        N = scanner.nextLong();

        System.out.println("Enter a number of threads: ");
        THREADCOUNT = scanner.nextInt();

        divisors = new ArrayList<Long>();
        List<CalculatingThread> threads = new ArrayList<>();


        long sizeOfCalculatingPart = (long) ((Math.sqrt(N)) / THREADCOUNT);

        long start = System.currentTimeMillis();

        for (int i = 0; i < THREADCOUNT; i++) {
            CalculatingThread calculatingThread = new CalculatingThread((sizeOfCalculatingPart * i), (sizeOfCalculatingPart * (i + 1)) - 1, i);
            calculatingThread.start();
            threads.add(calculatingThread);
        }

        for (CalculatingThread thread :
                threads) {
            thread.join();
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Finished in " + (timeElapsed / 1) + " milliseconds");

        if (divisors.size() > 0) {
            for (long i : divisors) {
                //System.out.println("Is divisible by: " + i);
            }
        } else {
            System.out.println(N + " is a prime number!");
        }
    }
}
