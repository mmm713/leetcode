package com.home.learn.facebook;

import com.home.learn.leetcode.tree.BinaryTreeLongestConsecutiveSequence;
import com.home.learn.library.TreeNode;
import org.junit.jupiter.api.Test;

public class BinaryTreeLongestConsecutiveSequenceTest {
    @Test
    void test() {
        BinaryTreeLongestConsecutiveSequence sequence = new BinaryTreeLongestConsecutiveSequence();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        System.out.println(sequence.longestConsecutive(root));
    }

    @Test
    void test2() {
        BinaryTreeLongestConsecutiveSequence sequence = new BinaryTreeLongestConsecutiveSequence();
        TreeNode root = new TreeNode(9);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(1);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        System.out.println(sequence.longestConsecutiveII(root));
    }

    @Test
    void test3() {
        BinaryTreeLongestConsecutiveSequence sequence = new BinaryTreeLongestConsecutiveSequence();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left = new TreeNode(2);
        System.out.println(sequence.longestConsecutiveII(root));
    }
}
