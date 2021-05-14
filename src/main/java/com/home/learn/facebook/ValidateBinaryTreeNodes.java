package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidateBinaryTreeNodes {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] parent = new int[n];
        for(int i = 0; i < n; i++) {
            if(leftChild[i] != -1) parent[leftChild[i]]++;
            if(rightChild[i] != -1) parent[rightChild[i]]++;
        }
        int root = -1, count = 0;
        for(int i = 0; i < n; i++) {
            if(parent[i] == 0) {
                root = i;
                count++;
            }
        }
        if(root == -1 || count != 1) {
            return false;
        }
        boolean[] visited = new boolean[n];
        if(!dfs(root, visited, leftChild, rightChild)) {
            return false;
        }
        for(boolean vs: visited) {
            if(!vs) return false;
        }
        return true;
    }

    public boolean dfs(int node, boolean[] visited, int[] leftChild, int[] rightChild) {
        if(node == -1) return true;
        if(visited[node]) return false;
        visited[node] = true;
        return dfs(leftChild[node], visited, leftChild, rightChild) && dfs(rightChild[node], visited, leftChild, rightChild);
    }

    public boolean validateBinaryTreeNodesV1(TreeNode[] roots, TreeNode[] leftChild, TreeNode[] rightChild) {
        int n = roots.length;
        Map<TreeNode, Integer> inDegrees = new HashMap<>();
        for(int i = 0; i < n; i++) {
            TreeNode left = leftChild[i];
            TreeNode right = rightChild[i];
            TreeNode tempRoot = roots[i];
            tempRoot.left = left;
            tempRoot.right = right;
            inDegrees.put(tempRoot, inDegrees.getOrDefault(tempRoot, 0));
            inDegrees.put(left, inDegrees.getOrDefault(left, 0) + 1);
            inDegrees.put(right, inDegrees.getOrDefault(right, 0) + 1);
        }
        TreeNode root = null;
        int count = 0;
        for(Map.Entry<TreeNode, Integer> entry : inDegrees.entrySet()) {
            if(entry.getValue() == 0) {
                root = entry.getKey();
                count++;
            }
        }
        if(root == null || count != 1) {
            return false;
        }
        Set<TreeNode> visited = new HashSet<>(n);
        if(!dfsTreeNode(root, visited)) {
            return false;
        }
        return visited.size() == roots.length;
    }

    public boolean dfsTreeNode(TreeNode root, Set<TreeNode> visited) {
        if(root == null) return true;
        if(visited.contains(root)) return false;
        visited.add(root);
        return dfsTreeNode(root.left, visited) && dfsTreeNode(root.right, visited);
    }
}
