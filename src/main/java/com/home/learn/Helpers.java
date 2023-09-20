package com.home.learn;

import com.home.learn.library.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Helpers {
    public static int gcd(int a, int b) {
        if (a == 0) { return b; }
        return gcd(b % a, a);
    }
    public static int getGCD(int a, int b) {
        while (a > 0 && b > 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        if (a == 0) { return b; }
        return a;
    }

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

    static class TreePrinter {
        public static void printNode(TreeNode root) {
            int maxLevel = TreePrinter.maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || TreePrinter.isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            TreePrinter.printWhitespaces(firstSpaces);

            List<TreeNode> newNodes = new ArrayList<>();
            for (TreeNode node : nodes) {
                if (node != null) {
                    System.out.print(node.val);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                TreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println();

            for (int i = 1; i <= endgeLines; i++) {
                for (TreeNode node : nodes) {
                    TreePrinter.printWhitespaces(firstSpaces - i);
                    if (node == null) {
                        TreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }
                    if (node.left != null)
                        System.out.print("/");
                    else
                        TreePrinter.printWhitespaces(1);

                    TreePrinter.printWhitespaces(i + i - 1);

                    if (node.right != null)
                        System.out.print("\\");
                    else
                        TreePrinter.printWhitespaces(1);

                    TreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private static int maxLevel(TreeNode node) {
            if (node == null)
                return 0;

            return Math.max(TreePrinter.maxLevel(node.left), TreePrinter.maxLevel(node.right)) + 1;
        }

        private static <T> boolean isAllElementsNull(List<T> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }
            return true;
        }
    }

    public static long combination(long n, long r) {
        return factorial(n) / factorial(r) / factorial(n-r);
    }

    private static long factorial(long n) {
        long fact = 1;
        long i = 1;
        while(i <= n) {
            fact *= i;
            i++;
        }
        return fact;
    }
}
