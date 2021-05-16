package com.home.learn.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BuildingH2O {
    private final ReentrantLock lock;
    private final Condition condition;
    private int hCount;

    public BuildingH2O() {
        this.lock = new ReentrantLock(true);
        this.condition = lock.newCondition();
        this.hCount = 0;
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        lock.lock();
        while(hCount == 2) {
            condition.await();
        }
        releaseHydrogen.run();
        this.hCount++;
        condition.signalAll();
        lock.unlock();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        lock.lock();
        while(this.hCount != 2) {
            condition.await();
        }
        releaseOxygen.run();
        this.hCount = 0;
        condition.signalAll();
        lock.unlock();
    }
}
