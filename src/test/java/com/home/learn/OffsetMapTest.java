package com.home.learn;

import com.home.learn.uber.OffsetMap;
import org.junit.jupiter.api.Test;

public class OffsetMapTest {
    @Test
    void test() {
        OffsetMap map = new OffsetMap();
        map.insert(5, 2);
        map.addToKey(3);
        System.out.println(map.getKey(8));
        map.addToValue(4);
        System.out.println(map.getKey(8));
        map.insert(12, 2);
        System.out.println(map.getKey(12));
        map.addToKey(2);
        System.out.println(map.getKey(10));
        System.out.println(map.getKey(14));
        map.addToValue(4);
        System.out.println(map.getKey(10));
        System.out.println(map.getKey(14));
    }
}
