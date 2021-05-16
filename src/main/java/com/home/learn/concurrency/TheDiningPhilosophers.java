package com.home.learn.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class TheDiningPhilosophers {
    private Integer count = 0;
    public TheDiningPhilosophers() {

    }
    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        synchronized(count){
            if(count == 0) {
                count++;
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
                count--;
                count.notifyAll();
            }
        }
    }
}
