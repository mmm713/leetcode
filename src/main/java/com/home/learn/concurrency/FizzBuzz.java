package com.home.learn.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class FizzBuzz {
    private final int n;
    private final AtomicInteger a;

    public FizzBuzz(int n) {
        this.n = n;
        a = new AtomicInteger(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while(true) {
            int temp = a.get();
            if(temp > n) {
                return;
            }
            if(temp % 3 ==0 && temp % 5 != 0) {
                printFizz.run();
                a.incrementAndGet();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while(true) {
            int temp = a.get();
            if(temp > n) {
                return;
            }
            if(temp % 3 !=0 && temp % 5 == 0) {
                printBuzz.run();
                a.incrementAndGet();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(true) {
            int temp = a.get();
            if(temp > n) {
                return;
            }
            if(temp % 3 ==0 && temp % 5 == 0) {
                printFizzBuzz.run();
                a.incrementAndGet();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while(true) {
            int temp = a.get();
            if(temp > n) {
                return;
            }
            if(temp % 3 !=0 && temp % 5 != 0) {
                printNumber.accept(a.getAndIncrement());
            }
        }
    }
}
