package com.home.learn.library;

public class TreeNode {
     public int val;
     public TreeNode left;
     public TreeNode right;
     public TreeNode(int x) { val = x; }

     @Override
     public String toString() {
          return val + "_" + left + "_" + right;
     }
}
