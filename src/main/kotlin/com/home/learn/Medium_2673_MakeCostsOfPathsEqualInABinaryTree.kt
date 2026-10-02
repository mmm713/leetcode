package com.home.learn

import kotlin.math.abs
import kotlin.math.max

/**
 * 2673. 使二叉树所有路径值相等的最小代价
 * https://leetcode.com/problems/make-costs-of-paths-equal-in-a-binary-tree/
 *
 * 给定一棵有 n 个节点的满二叉树，所有叶子位于同一层。
 * 节点编号为 1..n，根节点编号为 1。
 * 节点 i 的左孩子为 2 * i，右孩子为 2 * i + 1（如果孩子存在）。
 *
 * 数组 cost 的下标从 0 开始，cost[i] 表示编号为 i + 1 的节点的值。
 * 每次操作可以选择任意一个节点，将它的值增加 1，可以操作任意多次。
 * 路径值是该路径上所有节点值的总和，包括根节点和叶子节点。
 * 返回让所有从根到叶子的路径值相等所需的最少操作次数。
 *
 * 示例 1：n = 7, cost = [1,5,2,2,3,3,1]，输出 6。
 * 示例 2：n = 3, cost = [5,3,3]，输出 0。
 *
 * 约束：
 * - 3 <= n <= 100_000。
 * - n + 1 是 2 的幂。
 * - cost.size == n。
 * - 1 <= cost[i] <= 10_000。
 */
class Medium_2673_MakeCostsOfPathsEqualInABinaryTree {
    fun minIncrements(n: Int, cost: IntArray): Int {
        var res = 0
        for(i in n / 2 downTo 1) {
            res += abs(cost[i * 2] - cost[i * 2 - 1])
            cost[i - 1] += max(cost[i * 2 - 1], cost[i * 2])
        }
        return res
    }
}

fun main() {
    val sol = Medium_2673_MakeCostsOfPathsEqualInABinaryTree()
    val cases = listOf(
        Triple(7, intArrayOf(1, 5, 2, 2, 3, 3, 1), 6),
        Triple(3, intArrayOf(5, 3, 3), 0),
        Triple(3, intArrayOf(1, 1, 5), 4),
        Triple(7, intArrayOf(1, 1, 1, 1, 1, 1, 1), 0)
    )

    for ((n, cost, expected) in cases) {
        val actual = sol.minIncrements(n, cost.copyOf())
        println("n = $n, cost = ${cost.contentToString()}: ${if (actual == expected) "PASS" else "FAIL"}")
        println("实际：$actual，期望：$expected")
    }
}
