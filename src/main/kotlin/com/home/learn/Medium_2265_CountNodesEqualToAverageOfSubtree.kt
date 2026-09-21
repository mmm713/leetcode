package com.home.learn

import com.home.learn.library.TreeNode

class Medium_2265_CountNodesEqualToAverageOfSubtree {

    fun averageOfSubtree(root: TreeNode?): Int {
        return dfs(root).third
    }

    //pair returns how many nodes, and sub tree sum, total eql
    private fun dfs(root: TreeNode?): Triple<Int, Int, Int> {
        if(root == null) {
            return Triple(0, 0, 0)
        }
        val l = dfs(root.left)
        val r = dfs(root.right)
        val sum = l.second + r.second + root.`val`
        val count = l.first + r.first + 1
        val res = l.third + r.third +  if(root.`val` == (sum / count)) 1 else 0
        return Triple(count, sum, res)
    }
}

fun main() {
    val t = Medium_2265_CountNodesEqualToAverageOfSubtree()
    val root = TreeNode(4).apply {
        left = TreeNode(8).apply {
            left = TreeNode(0)
            right = TreeNode(1)
        }
        right = TreeNode(5).apply {
            right = TreeNode(6)
        }
    }
    println(t.averageOfSubtree(root)) // 5
    println(t.averageOfSubtree(TreeNode(1))) // 1
}
