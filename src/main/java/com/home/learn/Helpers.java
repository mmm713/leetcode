package com.home.learn;

import java.util.Arrays;

public class Helpers {
    public static void print2D(int[][] mat) {
        for (int[] row : mat)
            System.out.println(Arrays.toString(row));
    }

    public static void print2D(String[][] mat) {
        for (String[] row : mat)
            System.out.println(Arrays.toString(row));
    }
    public static void print1D(int[] mat) {
        System.out.println(Arrays.toString(mat));
    }

    public static void print1D(String[] mat) {
        System.out.println(Arrays.toString(mat));
    }
}
