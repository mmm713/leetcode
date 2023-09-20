package com.home.learn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    @Test
    void test() {
        System.out.println(Helpers.combination(13, 0));
        System.out.println(Helpers.combination(13, 1));
        System.out.println(Helpers.combination(13, 2));
    }
}
