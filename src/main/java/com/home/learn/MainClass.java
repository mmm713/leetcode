package com.home.learn;

import com.home.learn.google.DeleteNodesAndReturnForest;
import com.home.learn.library.TreeNode;

import java.util.*;

public class MainClass {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node1;
        node1.left = node2;
        node1.right = node3;
        root.right = node4;
        node4.left = node5;
        node2.right = node6;
        Helpers.TreePrinter.printNode(root);
        int[] toDel = {1, 5};
        DeleteNodesAndReturnForest test = new DeleteNodesAndReturnForest();
        List<TreeNode> res = test.delNodes(root, toDel);
        res.forEach(Helpers.TreePrinter::printNode);
    }
}
