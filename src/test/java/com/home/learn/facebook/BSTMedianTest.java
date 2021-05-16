package com.home.learn.facebook;

import com.home.learn.leetcode.sortnsearch.BinarySearchTreeMedian;
import com.home.learn.library.TreeNode;
import org.junit.jupiter.api.Test;

public class BSTMedianTest {
    @Test
    void test() {
        BinarySearchTreeMedian median = new BinarySearchTreeMedian();
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(9);
        root.right = new TreeNode(16);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(11);
        System.out.println(median.findMedian(root));
        root.right.left = new TreeNode(14);
        System.out.println(median.findMedian(root));
    }
}
