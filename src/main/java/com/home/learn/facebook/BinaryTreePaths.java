package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root != null) {
            dfs(root, "", result);
        }
        return result;
    }

    private void dfs(TreeNode root, String tmp, List<String> result) {
        tmp += (tmp.equals("") ? "" : "->") + root.val;
        if(root.left != null) {
            dfs(root.left, tmp, result);
        }
        if(root.right != null) {
            dfs(root.right, tmp, result);
        }
        if(root.left == null && root.right == null) {
            result.add(tmp);
        }
    }
}
