package com.home.learn.leetcode.segment.tree;

import java.util.ArrayList;
import java.util.List;

public class BusiestServers {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        Node root = buildTree(0, k - 1);
        int[] cnt = new int[k];
        int max = 0;
        for (int i = 0; i < arrival.length; i++) {
            // 没有空虚的服务器
            if (arrival[i] < root.end) {
                continue;
            }
            int pos = i % k;
            // 查询 pos 到 k-1 的区间
            int x = query(root, pos, k - 1, arrival[i]);
            if (x == -1) {
                // 查询 0 到 pos-1的区间
                x = query(root, 0, pos - 1, arrival[i]);
            }
            // 计数
            cnt[x]++;
            // 保存最大值
            max = Math.max(max, cnt[x]);
            // 更新服务器x的值
            update(root, x, arrival[i] + load[i]);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] == max) {
                list.add(i);
            }
        }
        return list;
    }

    private int query(Node root, int l, int r, int start) {
        if (root.l == root.r) {
            if (root.l >= l && root.l <= r) {
                return root.l;
            }
            return -1;
        }
        int mid = (root.l + root.r) >>> 1;
        int val = -1;
        // 访问左子树
        if (l <= mid && start >= root.left.end) {
            val = query(root.left, l, r, start);
        }
        if (val != -1) {
            return val;
        }
        // 访问右子树
        if (r > mid && start >= root.right.end) {
            val = query(root.right, l, r, start);
        }
        return val;
    }

    private void update(Node root, int x, int end) {
        if (root.l == root.r) {
            // 更新叶子节点
            root.end = end;
            return;
        }
        int mid = (root.l + root.r) >>> 1;
        if (x <= mid) {
            update(root.left, x, end);
        } else {
            update(root.right, x, end);
        }
        // 父节点的结束时间是左右子节点结束时间的小值
        root.end = Math.min(root.left.end, root.right.end);
    }

    private Node buildTree(int left, int right) {
        Node node = new Node(left, right);
        if (left == right) {
            return node;
        }
        int mid = (left + right) >>> 1;
        node.left = buildTree(left, mid);
        node.right = buildTree(mid + 1, right);
        return node;
    }

    static class Node {
        // 任务结束时间
        int end = 0;
        // 记录服务器ID,范围
        int l, r;
        // 服务器左右子节点
        Node left = null, right = null;

        public Node(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}
