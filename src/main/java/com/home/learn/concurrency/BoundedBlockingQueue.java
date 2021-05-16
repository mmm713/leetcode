package com.home.learn.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue {
    int capacity;
    Queue<Integer> queue;
    Lock lock;
    Condition canRunEnqueue;
    Condition canRunDequeue;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.canRunEnqueue = lock.newCondition();
        this.canRunDequeue = lock.newCondition();
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            if (queue.size() == capacity) {
                canRunEnqueue.await();
            }
            queue.offer(element);
            canRunDequeue.signal();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try {
            if (queue.isEmpty()) {
                canRunDequeue.await();
            }
            int result = queue.poll();
            canRunEnqueue.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }

    public int size() throws InterruptedException {
        return queue.size();
    }
}
