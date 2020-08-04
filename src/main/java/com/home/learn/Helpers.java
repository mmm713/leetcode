package com.home.learn;

import java.util.Arrays;
import java.util.List;

public class Helpers {
    public static void print2D(int[][] mat) {
        for (int[] row : mat)
            System.out.println(Arrays.toString(row));
    }
    public static void print2D(List<List<Integer>> lists) {
        for (List<Integer> list : lists)
            System.out.println(Arrays.toString(list.toArray()));
    }

    public static void print1D(List<Integer> list) {
        System.out.println(Arrays.toString(list.toArray()));
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
