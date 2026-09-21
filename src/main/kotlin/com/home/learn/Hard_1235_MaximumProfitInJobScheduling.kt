package com.home.learn

class Hard_1235_MaximumProfitInJobScheduling {
    data class Job(val start: Int, val end: Int, val profit: Int)

    fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val jobs = profit.indices.map { i ->
            Job(startTime[i], endTime[i], profit[i])
        }.sortedBy { it.end }

        // dp[i] 表示前 i 个工作的最大收益
        val dp = IntArray(jobs.size + 1)
        jobs.forEachIndexed { i, job ->
            // p 是前面满足 end <= job.start 的工作数量，允许首尾相接
            val p = -jobs.binarySearch(0, i) {
                if (it.end <= job.start) -1 else 1
            } - 1
            dp[i + 1] = maxOf(dp[i], dp[p] + job.profit)
        }
        return dp.last()
    }
}

fun main() {
    val t = Hard_1235_MaximumProfitInJobScheduling()
    println(t.jobScheduling(
        intArrayOf(1, 2, 3, 3),
        intArrayOf(3, 4, 5, 6),
        intArrayOf(50, 10, 40, 70)
    )) // 120
    println(t.jobScheduling(
        intArrayOf(1, 2, 3, 4, 6),
        intArrayOf(3, 5, 10, 6, 9),
        intArrayOf(20, 20, 100, 70, 60)
    )) // 150
    println(t.jobScheduling(
        intArrayOf(1, 1, 1),
        intArrayOf(2, 3, 4),
        intArrayOf(5, 6, 4)
    )) // 6
    println(t.jobScheduling(
        intArrayOf(1), intArrayOf(2), intArrayOf(50)
    )) // 50
    println(t.jobScheduling(
        intArrayOf(1, 1, 1, 3),
        intArrayOf(3, 3, 3, 4),
        intArrayOf(10, 20, 100, 5)
    )) // 105
}
