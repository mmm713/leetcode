package com.home.learn.uber;

import com.home.learn.library.TreeNode;

import java.util.*;

public class SerializeDeserializeBinaryTree {
    private static final String NULL ="X";
    private static final String SPLITTER =",";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root,sb);
        return sb.toString();
    }
    private void buildString(TreeNode node, StringBuilder sb){
        if(node == null){
            sb.append(NULL);
            sb.append(SPLITTER);
        } else {
            sb.append(node.val);
            sb.append(SPLITTER);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(SPLITTER)));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue){
        String s = queue.poll();
        if(s.equals(NULL)) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.parseInt(s));
            node.left = buildTree(queue);
            node.right = buildTree(queue);
            return node;
        }
    }
}
