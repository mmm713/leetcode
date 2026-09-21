package com.home.learn

import com.home.learn.library.TreeNode

class Medium_1080_InsufficientNodesInRootToLeafPaths {

    fun sufficientSubset(root: TreeNode?, limit: Int): TreeNode? {
        if (root == null) return null
        val l = limit - root.`val`
        if (root.left == null && root.right == null) { // root 是叶子
            // 如果 limit > 0 说明从根到叶子的路径和小于 limit，删除叶子，否则不删除
            return if (l > 0) null else root
        }
        if (root.left != null) root.left = sufficientSubset(root.left, l);
        if (root.right != null) root.right = sufficientSubset(root.right, l);
        // 如果儿子都被删除，就删 root，否则不删 root
        return if(root.left == null && root.right == null) null else root
    }
}

fun main() {
    val t = Medium_1080_InsufficientNodesInRootToLeafPaths()
    val root1 = TreeNode(5).apply {
        left = TreeNode(4).apply {
            left = TreeNode(11).apply {
                left = TreeNode(7)
                right = TreeNode(1)
            }
        }
        right = TreeNode(8).apply {
            left = TreeNode(17)
            right = TreeNode(4).apply {
                left = TreeNode(5)
                right = TreeNode(3)
            }
        }
    }
    // 预期树（层序）：[5,4,8,11,null,17,4,7,null,null,null,5]
    println(t.sufficientSubset(root1, 22))

    val root2 = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(-5)
        }
        right = TreeNode(-3).apply {
            left = TreeNode(4)
        }
    }
    // 预期树（层序）：[1,null,-3,4]
    println(t.sufficientSubset(root2, -1))
}
