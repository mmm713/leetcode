package com.home.learn

import java.util.PriorityQueue

/**
 * LeetCode 2402. Meeting Rooms III（会议室 III）
 * https://leetcode.com/problems/meeting-rooms-iii/
 *
 * 有 n 间会议室，编号为 0 到 n - 1。meetings 中的 [start, end] 表示会议原定时间。
 * 所有原定开始时间互不相同，输入不保证有序，时间区间为左闭右开 [start, end)。
 * 分配规则：
 * 1. 有空闲会议室时，使用编号最小的那间。
 * 2. 没有空闲会议室时，推迟会议，直到有房间空闲；会议持续时间保持不变。
 * 3. 多场会议等待时，原定开始时间较早的优先。同一时刻多个房间可用时选最小编号。
 * 返回举办会议次数最多的会议室编号；次数相同则返回最小编号。
 *
 * 约束：1 <= n <= 100；1 <= meetings.size <= 100_000。
 * 0 <= start < end <= 500_000；start 互不相同。
 * 示例：n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]] -> 0
 * 示例：n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]] -> 1
 */
class Hard_2402_MeetingRoomsIII {
    fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
        meetings.sortBy { it[0] }
        val idle = PriorityQueue<Int>().also { it.addAll(0 until n) }
        val busy = PriorityQueue(compareBy<Pair<Int, Long>> { it.second }.thenBy { it.first })
        val res = IntArray(n)
        for(m in meetings) {
            while(busy.peek()?.let { it.second <= m[0] } == true) {
                idle.add(busy.poll().first)
            }
            var room: Int
            if(idle.isNotEmpty()) {
                room = idle.poll()
                busy.add((room to m[1].toLong()))
            } else {
                val b = busy.poll()
                room = b.first
                busy.add((room to b.second + m[1] - m[0]))
            }
            res[room]++
        }
        return res.indices.maxBy { res[it] }
    }
}

// 实现 mostBooked 后直接运行 main。
fun main() {
    data class Case(val name: String, val n: Int, val meetings: Array<IntArray>, val expected: Int)
    fun meetings(vararg times: Pair<Int, Int>) = times.map { intArrayOf(it.first, it.second) }.toTypedArray()

    val cases = listOf(
        Case("用户用例：四间会议室预期零号", 4,
            meetings(48 to 49, 22 to 30, 13 to 31, 31 to 46, 37 to 46,
                32 to 36, 25 to 36, 49 to 50, 24 to 34, 6 to 41), 0),
        Case("用户用例：四间会议室乱序输入", 4,
            meetings(12 to 44, 27 to 37, 48 to 49, 46 to 49, 24 to 44, 32 to 38, 21 to 49, 13 to 30), 1),
        Case("官方示例 1", 2, meetings(0 to 10, 1 to 5, 2 to 7, 3 to 4), 0),
        Case("官方示例 2", 3, meetings(1 to 20, 2 to 10, 3 to 5, 4 to 9, 6 to 8), 1),
        Case("单间单场", 1, meetings(0 to 1), 0),
        Case("单间连续延期", 1, meetings(0 to 10, 1 to 3, 2 to 4), 0),
        Case("会议室多于会议", 5, meetings(0 to 10, 1 to 9), 0),
        Case("没有重叠始终使用零号", 3, meetings(0 to 2, 3 to 5, 6 to 8), 0),
        Case("结束时刻立即复用", 2, meetings(0 to 2, 2 to 4, 4 to 6), 0),
        Case("一号举办次数更多", 2, meetings(0 to 10, 1 to 2, 3 to 4, 5 to 6), 1),
        Case("所有房间次数相同", 3, meetings(0 to 10, 1 to 10, 2 to 10), 0),
        Case("同时释放选择最小编号", 2, meetings(0 to 5, 1 to 5, 2 to 3, 3 to 4, 4 to 5), 0),
        Case("多个房间已空闲按编号选择", 3, meetings(0 to 5, 1 to 4, 6 to 7, 7 to 8), 0),
        Case("延期保持时长和原定顺序", 2, meetings(0 to 10, 1 to 4, 2 to 8, 3 to 5, 4 to 6), 1),
        Case("官方示例乱序输入", 3, meetings(6 to 8, 3 to 5, 1 to 20, 4 to 9, 2 to 10), 1),
        Case("一百间房间", 100, Array(100) { intArrayOf(it, 500_000) }, 0),
        Case("十万场连续会议", 100, Array(100_000) { intArrayOf(it, it + 1) }, 0),
        Case("十万场延期且实际结束时间超过 Int", 2, Array(100_000) { intArrayOf(it, 500_000) }, 0)
    )

    val solution = Hard_2402_MeetingRoomsIII()
    var passed = 0
    for ((name, n, input, expected) in cases) {
        val actual = solution.mostBooked(n, input.map { it.copyOf() }.toTypedArray())
        val success = actual == expected
        if (success) passed++
        println("${if (success) "PASS" else "FAIL"} | $name | expected=$expected, actual=$actual")
    }
    check(passed == cases.size) { "$passed/${cases.size} tests passed" }
    println("All ${cases.size} tests passed!")
}
