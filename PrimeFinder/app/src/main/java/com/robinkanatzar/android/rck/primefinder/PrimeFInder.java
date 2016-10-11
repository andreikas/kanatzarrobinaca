package com.robinkanatzar.android.rck.primefinder;

public class PrimeFInder implements Runnable {

    public long target;
    public long prime;
    public boolean finished = false;
    private Thread runner;
    String outputString;
    public long outputText1 = -1;
    public long outputText2 = -1;
    public long outputText3 = -1;
    long longArray[] = new long[3];
    String longArrayString;

    PrimeFInder(long inTarget) {
        target = inTarget;
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    @Override
    public void run() {
        long numPrimes = 0;
        long candidate = 2;

        while (numPrimes < target) {
            if (isPrime(candidate)) {
                numPrimes++;
                prime = candidate;

                System.out.println("Prime Candidate: " + prime);
            }
            candidate++;
        }

        System.out.println("Number of Primes: " + numPrimes);
        finished = true;
    }

    boolean isPrime(long checkNumber) {
        double root = Math.sqrt(checkNumber);

        for (int i = 2; i <= root; i++) {
            if (checkNumber % i == 0) {
                return false;
            }
        }
        return true;
    }
}

