package com.home.learn.wish;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SwapRedLightsTest {
    @Test
    void test() {
        SwapRedLights lights = new SwapRedLights();
        System.out.println(Arrays.toString(lights.maxGreenLights("RGGRGGRRG")));
        System.out.println(Arrays.toString(lights.maxGreenLights("RRGRRGGRRG")));
        System.out.println(Arrays.toString(lights.maxGreenLights("RGRGRRG")));
        System.out.println(Arrays.toString(lights.maxGreenLights("RRGGGGGRRRRGRRRG")));
        System.out.println(Arrays.toString(lights.maxGreenLights("RGRGRRGRRRG")));
        System.out.println(Arrays.toString(lights.maxGreenLights("RRRRRGRGRRGRRRG")));
        System.out.println(Arrays.toString(lights.maxGreenLights("GGGGGRGRRGRRRG")));
    }
}
