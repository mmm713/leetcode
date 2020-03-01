package com.home.learn.interview;

import com.google.common.collect.Lists;

import com.home.learn.lyft.CommonElement;
import org.junit.jupiter.api.Test;

import java.util.List;

class CommonElementTest {

    @Test
    public void testHappyCases() {
        List<Integer> test0 = Lists.newArrayList(1,2,3,4,4,5,5);
        List<Integer> test1 = Lists.newArrayList(1,1,2,2,3);
        test(test0, test1);
        List<Integer> test2 = Lists.newArrayList(1,2,4,8);
        List<Integer> test3 = Lists.newArrayList(2,8,9);
        test(test2, test3);
        List<Integer> test4 = Lists.newArrayList(3,4,5,6,7,9,12,17);
        List<Integer> test5 = Lists.newArrayList(2,8,9,22,67);
        test(test4, test5);
    }

    private static void test(List<Integer> l0, List<Integer> l1) {
        CommonElement<Integer> res = new CommonElement<>(l0.iterator(), l1.iterator());
        System.out.println("===========================");
        while (res.hasNext()) {
            System.out.print(res.next() + " -> ");
        }
        System.out.print("\n");
    }
}
