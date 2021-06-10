package com.home.learn.uber;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MemoryAllocateTest {
    @Test
    void test() {
        int[] mem = new int[8 * 8];
        MemoryAllocate allocate = new MemoryAllocate(mem);
        System.out.println(allocate.query(0, 5));
        System.out.println(allocate.query(0, 3));
        System.out.println(allocate.query(0, 2));
        System.out.println(allocate.query(0, 4));
        System.out.println(allocate.query(1, 2));
        System.out.println(allocate.query(1, 4));
        System.out.println(Arrays.toString(mem));
    }
}
