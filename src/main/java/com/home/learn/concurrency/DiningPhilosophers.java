package com.home.learn.concurrency;

import java.util.concurrent.Semaphore;

public class DiningPhilosophers {

    public DiningPhilosophers() {
        for(int i = 0; i < 5; i++){
            semaphores[i] = new Semaphore(1);
        }
    }
    Semaphore[] semaphores = new Semaphore[5];
    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        Semaphore left = semaphores[philosopher];
        Semaphore right = semaphores[philosopher == 0 ? 4 : philosopher - 1];
        if(philosopher % 2 == 0) {
            left.acquire();
            pickLeftFork.run();
            right.acquire();
            pickRightFork.run();
            eat.run();
            putRightFork.run();
            right.release();
            putLeftFork.run();
            left.release();
        } else {
            right.acquire();
            pickRightFork.run();
            left.acquire();
            pickLeftFork.run();
            eat.run();
            putLeftFork.run();
            left.release();
            putRightFork.run();
            right.release();
        }
    }
}
