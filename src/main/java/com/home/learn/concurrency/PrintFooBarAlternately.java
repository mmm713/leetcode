package com.home.learn.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintFooBarAlternately {
    private int n;
    private AtomicInteger integer;

    public PrintFooBarAlternately(int n) {
        this.n = n;
        this.integer = new AtomicInteger(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while(true) {
                if(integer.compareAndSet(0, 1)) {
                    printFoo.run();
                    break;
                }
                Thread.sleep(1);
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while(true) {
                if(integer.compareAndSet(1, 0)) {
                    printBar.run();
                    break;
                }
                Thread.sleep(1);
            }
        }
    }
}
