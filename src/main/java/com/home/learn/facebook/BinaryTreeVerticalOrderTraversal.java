package com.home.learn.facebook;

import com.home.learn.library.TreeNode;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        Queue<Integer> cols = new ArrayDeque<>();
        q.offer(root);
        cols.offer(0);
        int min = 0;
        int max = 0;
        while(!q.isEmpty()) {
            TreeNode node = q.poll();
            int col = cols.poll();
            if(!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(node.val);
            if(node.left != null) {
                q.offer(node.left);
                cols.offer(col - 1);
                min = Math.min(min, col - 1);
            }
            if(node.right != null) {
                q.offer(node.right);
                cols.offer(col + 1);
                max = Math.max(max, col + 1);
            }
        }
        for(int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }

    public List<List<Integer>> verticalOrderPair(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Pair<Integer,TreeNode>> q = new ArrayDeque<>();
        q.offer(Pair.of(0, root));
        int min = 0;
        int max = 0;
        while(!q.isEmpty()) {
            Pair<Integer,TreeNode> node = q.poll();
            if(!map.containsKey(node.getKey())) {
                map.put(node.getKey(), new ArrayList<>());
            }
            map.get(node.getKey()).add(node.getValue().val);
            if(node.getValue().left != null) {
                q.offer(Pair.of(node.getKey() - 1, node.getValue().left));
                min = Math.min(min, node.getKey() - 1);
            }
            if(node.getValue().right != null) {
                q.offer(Pair.of(node.getKey() + 1, node.getValue().right));
                max = Math.max(max, node.getKey() + 1);
            }
        }
        for(int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}
