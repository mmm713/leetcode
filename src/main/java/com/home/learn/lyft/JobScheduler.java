package com.home.learn.lyft;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class JobScheduler {
    static class JobMachine {
        String machineId;
        int jobEndTime;

        public JobMachine(String machineId, int jobEndTime) {
            this.machineId = machineId;
            this.jobEndTime = jobEndTime;
        }

        public String getMachineId() {
            return machineId;
        }

        public int getJobEndTime() {
            return jobEndTime;
        }
    }

    PriorityQueue<JobMachine> runQueue;
    PriorityQueue<JobMachine> waitQueue;
    AtomicInteger autoId;

    public JobScheduler() {
        this.runQueue = new PriorityQueue<>(Comparator.comparingInt(JobMachine::getJobEndTime));
        this.waitQueue = new PriorityQueue<>(Comparator.comparing(JobMachine::getMachineId));
        autoId = new AtomicInteger(1);
    }

    public String[] scheduleJob(String[] input) {
        String[] result = new String[2];
        result[0] = input[0];
        int startTime = Integer.parseInt(input[1]);
        startTime = startTime / 100 * 60 + startTime % 100;
        int endTime = Integer.parseInt(input[2]) + startTime;
        while(!runQueue.isEmpty() && runQueue.peek().jobEndTime < startTime) {
            waitQueue.offer(runQueue.poll());
        }
        JobMachine machine;
        if(!waitQueue.isEmpty()) {
            machine = waitQueue.poll();
            machine.jobEndTime = endTime;
        } else {
            machine = new JobMachine("M" + autoId.getAndIncrement(), endTime);
        }
        runQueue.offer(machine);
        result[1] = machine.getMachineId();
        return result;
    }
}
