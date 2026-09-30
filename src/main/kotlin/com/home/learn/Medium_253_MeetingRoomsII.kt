package com.home.learn

import java.util.PriorityQueue
import kotlin.math.max

/**
 * LeetCode 253. Meeting Rooms II（会议室 II）
 * https://leetcode.com/problems/meeting-rooms-ii/
 *
 * 给定会议时间 intervals，每项 [start, end] 表示一场会议的开始和结束时间。
 * 同一会议室不能同时举行重叠的会议，求安排所有会议所需的最少会议室数量。
 * 时间不能改变；一场会议结束的时刻，可以立即开始下一场会议。
 * 输入不保证按时间排序，可以有相同时间段。
 *
 * 约束：1 <= intervals.size <= 10_000；0 <= start < end <= 1_000_000。
 * 示例：[[0, 30], [5, 10], [15, 20]] -> 2
 * 示例：[[7, 10], [2, 4]] -> 1
 */
class Medium_253_MeetingRoomsII {
    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        if(intervals.isEmpty()) return 0
        intervals.sortBy { it[0] }
        val pq = PriorityQueue<Int>().also { it.add(intervals[0][1]) }
        var res = 1
        for(i in 1 until intervals.size) {
            while(!pq.isEmpty() && pq.peek() <= intervals[i][0]) {
                pq.poll()
            }
            pq.add(intervals[i][1])
            res = max(res, pq.size)
        }
        return res
    }
}

// 实现 minMeetingRooms 后直接运行 main。
fun main() {
    data class Case(val name: String, val intervals: Array<IntArray>, val expected: Int)
    fun intervals(vararg times: Pair<Int, Int>) = times.map { intArrayOf(it.first, it.second) }.toTypedArray()

    val cases = listOf(
        Case("官方示例 1", intervals(0 to 30, 5 to 10, 15 to 20), 2),
        Case("官方示例 2：输入未排序", intervals(7 to 10, 2 to 4), 1),
        Case("单场会议", intervals(0 to 1), 1),
        Case("结束与开始相同可复用", intervals(0 to 5, 5 to 10, 10 to 15), 1),
        Case("全部重叠", intervals(0 to 10, 1 to 9, 2 to 8, 3 to 7), 4),
        Case("完全相同的会议", intervals(1 to 5, 1 to 5, 1 to 5), 3),
        Case("同时开始", intervals(0 to 1, 0 to 2, 0 to 3), 3),
        Case("同时结束", intervals(0 to 5, 1 to 5, 2 to 5), 3),
        Case("长会议覆盖多场短会议", intervals(0 to 100, 1 to 2, 2 to 3, 3 to 4), 2),
        Case("同一时刻多场结束和开始", intervals(0 to 5, 1 to 5, 5 to 6, 5 to 7), 2),
        Case("最大重叠出现在中间", intervals(0 to 2, 3 to 9, 4 to 8, 5 to 7, 10 to 11), 3),
        Case("乱序混合", intervals(8 to 12, 0 to 4, 3 to 6, 6 to 9), 2),
        Case("时间上界", intervals(0 to 1_000_000, 999_999 to 1_000_000), 2),
        Case("一万场连续会议", Array(10_000) { intArrayOf(it, it + 1) }, 1),
        Case("一万场同时举行", Array(10_000) { intArrayOf(0, 1_000_000) }, 10_000)
    )

    val solution = Medium_253_MeetingRoomsII()
    var passed = 0
    for ((name, input, expected) in cases) {
        val actual = solution.minMeetingRooms(input.map { it.copyOf() }.toTypedArray())
        val success = actual == expected
        if (success) passed++
        println("${if (success) "PASS" else "FAIL"} | $name | expected=$expected, actual=$actual")
    }
    check(passed == cases.size) { "$passed/${cases.size} tests passed" }
    println("All ${cases.size} tests passed!")
}
