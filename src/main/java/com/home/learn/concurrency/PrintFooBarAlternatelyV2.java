package com.home.learn.concurrency;

import java.util.concurrent.Semaphore;

public class PrintFooBarAlternatelyV2 {
    private final int n;

    private final Semaphore semF = new Semaphore(1);
    private final Semaphore semB = new Semaphore(0);

    public PrintFooBarAlternatelyV2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semF.acquire();
            printFoo.run();
            semB.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semB.acquire();
            printBar.run();
            semF.release();
        }
    }
}
