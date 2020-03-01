package com.home.learn.interview;

import com.home.learn.lyft.JobScheduler;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class JobSchedulerTest {

    @Test
    public void testHappyCase() {
        JobScheduler scheduler = new JobScheduler();
        String[] input0 = {"J1", "0000", "45"};
        String[] input1 = {"J2", "0005", "10"};
        String[] input2 = {"J3", "0010", "30"};
        String[] input3 = {"J4", "0050", "20"};
        String[] input4 = {"J5", "0100", "20"};
        String[] input5 = {"J6", "0130", "30"};
        System.out.println("reuslt is : " + Arrays.toString(scheduler.scheduleJob(input0)));
        System.out.println("reuslt is : " + Arrays.toString(scheduler.scheduleJob(input1)));
        System.out.println("reuslt is : " + Arrays.toString(scheduler.scheduleJob(input2)));
        System.out.println("reuslt is : " + Arrays.toString(scheduler.scheduleJob(input3)));
        System.out.println("reuslt is : " + Arrays.toString(scheduler.scheduleJob(input4)));
        System.out.println("reuslt is : " + Arrays.toString(scheduler.scheduleJob(input5)));
    }
}
