package com.home.learn

import kotlin.math.max

/**
 * 第 4 题：每次建房后的最长连续房屋段（根据截图整理，标题自拟）。
 *
 * 在整数数轴上建房，初始没有房屋。按 queries 的顺序在对应位置建造一栋房屋。
 * 每建好一栋，记录整个区域内最长连续房屋段的房屋数量，返回这些长度组成的数组。
 * 连续表示位置相差 1，例如位置 1、2、3 组成长度为 3 的连续段。
 *
 * 输入保证：
 * - queries 中的建房位置互不相同。
 * - 每次建房时，左右相邻位置至少有一个没有房屋。
 *   因此不会通过填补一个空位连接左右两段已有房屋；这是输入保证，不需要拒绝操作。
 *
 * 示例：queries = [2, 1, 3]，返回 [1, 2, 3]。
 * 在 2 建房后最长为 1，在 1 建房后最长为 2，在 3 建房后最长为 3。
 * 具体数据范围和复杂度提示未拍到。
 */
class CodeSignal_LongestHouseSegment {
    fun solution(queries: IntArray): IntArray {
        val res = IntArray(queries.size)
        var max = 0
        val ltr = mutableMapOf<Int, Int>()
        val rtl = mutableMapOf<Int, Int>()
        for((i, q) in queries.withIndex()) {
            if(ltr.containsKey(q + 1)) {
                ltr[q] = ltr[q + 1]!!
                ltr.remove(q + 1)
                rtl[ltr[q]!!] = q
                max = max(max, ltr[q]!! - q + 1)
            } else if(rtl.containsKey(q - 1)) {
                rtl[q] = rtl[q - 1]!!
                rtl.remove(q - 1)
                ltr[rtl[q]!!] = q
                max = max(max, q - rtl[q]!! + 1)
            } else {
                ltr[q] = q
                rtl[q] = q
                max = max(max, 1)
            }
            res[i] = max
        }
        return res
    }
}

fun main() {
    val t = CodeSignal_LongestHouseSegment()
    // 所有用例均满足：位置唯一，插入时左右邻居不会同时存在。
    // 尚未实现 solution 时，运行仍会在 TODO 处停止。
    val cases = listOf(
        Triple("截图示例", intArrayOf(2, 1, 3), intArrayOf(1, 2, 3)),
        Triple("只有一栋", intArrayOf(5), intArrayOf(1)),
        Triple("始终互不相邻", intArrayOf(2, 4, 6, 8), intArrayOf(1, 1, 1, 1)),
        Triple("连续向右扩展", intArrayOf(3, 4, 5, 6, 7), intArrayOf(1, 2, 3, 4, 5)),
        Triple("连续向左扩展", intArrayOf(7, 6, 5, 4, 3), intArrayOf(1, 2, 3, 4, 5)),
        Triple("同一段交替扩展两端", intArrayOf(5, 4, 6, 3, 7), intArrayOf(1, 2, 3, 4, 5)),
        Triple("新建孤立段不降低最大值", intArrayOf(2, 3, 4, 10, 20), intArrayOf(1, 2, 3, 3, 3)),
        Triple("两段交替增长并列", intArrayOf(2, 10, 3, 9, 4, 8), intArrayOf(1, 1, 2, 2, 3, 3)),
        Triple("较短段后来超过最长段", intArrayOf(2, 3, 10, 11, 12, 13), intArrayOf(1, 2, 2, 2, 3, 4)),
        Triple("两段中间保留一个空位", intArrayOf(2, 6, 3, 5), intArrayOf(1, 1, 2, 2)),
        Triple("多个区间混合扩展", intArrayOf(10, 30, 11, 29, 50, 12, 28, 49, 48, 47),
            intArrayOf(1, 1, 2, 2, 2, 3, 3, 3, 3, 4))
    )

    for ((name, queries, expected) in cases) {
        val actual = t.solution(queries)
        println("$name: ${queries.contentToString()}")
        println("Actual: ${actual.contentToString()}")
        println("Expected: ${expected.contentToString()}")
        check(actual.contentEquals(expected)) { "用例失败：$name" }
        println("PASS")
        println()
    }
}
