package com.home.learn.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzzV2 {
    private int n;

    Semaphore fizzSem = new Semaphore(0);
    Semaphore buzzSem = new Semaphore(0);
    Semaphore fizzbuzzSem = new Semaphore(0);
    Semaphore runSem = new Semaphore(0);
    int k = 1;
    public FizzBuzzV2(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while(k < n) {
            fizzSem.acquire();
            if(k %3 == 0 && k % 5 != 0 && k <= n)
                printFizz.run();
            runSem.release();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while(k < n) {
            buzzSem.acquire();
            if(k % 3 != 0 && k % 5 == 0 && k <= n)
                printBuzz.run();
            runSem.release();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(k < n) {
            fizzbuzzSem.acquire();
            if(k % 15 == 0 && k <= n)
                printFizzBuzz.run();
            runSem.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {

        while(k <= n) {
            if(k % 15 == 0){
                fizzbuzzSem.release();
                runSem.acquire();
            } else if(k % 5 == 0){
                buzzSem.release();
                runSem.acquire();
            } else if(k % 3 == 0){
                fizzSem.release();
                runSem.acquire();
            } else {
                printNumber.accept(k);
            }
            k++;
        }
        // free up any locks which are held
        fizzSem.release();
        buzzSem.release();
        fizzbuzzSem.release();
    }
}
