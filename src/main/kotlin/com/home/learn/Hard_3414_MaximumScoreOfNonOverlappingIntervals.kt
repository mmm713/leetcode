package com.home.learn

class Hard_3414_MaximumScoreOfNonOverlappingIntervals {
    data class Interval(val l: Int, val r: Int, val weight: Int, val id: Int)
    data class State(val sum: Long = 0L, val ids: List<Int> = emptyList())

    fun maximumWeight(intervals: List<List<Int>>): IntArray {
        val n = intervals.size
        // 1. 构建并按右端点 r 升序排序
        val a = intervals.mapIndexed { i, (l, r, weight) ->
            Interval(l, r, weight, i)
        }.sortedBy { it.r }

        // f[i][j] 表示前 i 个区间中选至多 j 个的最大状态
        val f = Array(n + 1) { Array(5) { State() } }

        for (i in 0 until n) {
            val cur = a[i]
            // 插入位置 p 等于前面满足 r < cur.l 的区间数量
            val p = -a.binarySearch(0, i) {
                if (it.r < cur.l) -1 else 1
            } - 1

            for (j in 1..4) {
                // 情况 1: 不选当前区间
                val skip = f[i][j]

                // 情况 2: 选当前区间
                val prev = f[p][j - 1]
                val take = State(
                    prev.sum + cur.weight,
                    (prev.ids + cur.id).sorted()
                )

                f[i + 1][j] = if (
                    take.sum > skip.sum ||
                    (take.sum == skip.sum && compareIds(take.ids, skip.ids) < 0)
                ) take else skip
            }
        }

        return f[n][4].ids.toIntArray()
    }

    private fun compareIds(a: List<Int>, b: List<Int>): Int {
        for (i in 0 until minOf(a.size, b.size)) {
            if (a[i] != b[i]) return a[i].compareTo(b[i])
        }
        return a.size.compareTo(b.size)
    }
}

fun main() {
    val t = Hard_3414_MaximumScoreOfNonOverlappingIntervals()
    println(t.maximumWeight(listOf(
        listOf(1, 3, 2),
        listOf(4, 5, 2),
        listOf(1, 5, 5),
        listOf(6, 9, 3),
        listOf(6, 7, 1),
        listOf(8, 9, 1)
    )).contentToString()) // [2, 3]
    println(t.maximumWeight(listOf(
        listOf(5, 8, 1),
        listOf(6, 7, 7),
        listOf(4, 7, 3),
        listOf(9, 10, 6),
        listOf(7, 8, 2),
        listOf(11, 14, 3),
        listOf(3, 5, 5)
    )).contentToString()) // [1, 3, 5, 6]
}
