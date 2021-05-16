package com.home.learn.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class PrintZeroEvenOdd {
    private int n;
    private int value;
    private Semaphore semZ;
    private Semaphore semE;
    private Semaphore semO;

    public PrintZeroEvenOdd(int n) {
        this.n = n;
        this.semZ = new Semaphore(1);
        this.semE = new Semaphore(0);
        this.semO = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semZ.acquire();
            printNumber.accept(0);
            if(++value % 2 == 1) {
                semO.release();
            } else {
                semE.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n / 2; i++) {
            semE.acquire();
            printNumber.accept(value);
            semZ.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < (n + 1) / 2; i++) {
            semO.acquire();
            printNumber.accept(value);
            semZ.release();
        }
    }
}
