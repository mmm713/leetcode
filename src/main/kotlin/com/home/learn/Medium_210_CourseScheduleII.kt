package com.home.learn

/**
 * LeetCode 210. Course Schedule II（课程表 II）
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * 一共有 numCourses 门课程，编号为 0 到 numCourses - 1。
 * prerequisites 中的 [a, b] 表示：学习课程 a 之前必须先完成课程 b。
 * 返回一个能完成全部课程的学习顺序；多个顺序都合法时，返回任意一个即可。
 * 如果无法完成全部课程，返回空数组。
 *
 * 约束：1 <= numCourses <= 2000。
 * 0 <= prerequisites.size <= numCourses * (numCourses - 1)。
 * 每对依赖包含两个不同的合法课程编号，所有依赖对互不重复。
 *
 * 示例 1：numCourses = 2, prerequisites = [[1, 0]] -> [0, 1]
 * 示例 2：numCourses = 4, prerequisites = [[1, 0], [2, 0], [3, 1], [3, 2]]
 *         -> [0, 1, 2, 3] 或 [0, 2, 1, 3]
 * 示例 3：numCourses = 1, prerequisites = [] -> [0]
 */
class Medium_210_CourseScheduleII {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val graph = prerequisites.groupBy({ it[1] }, { it[0] })
        val inDegree = IntArray(numCourses).apply { prerequisites.forEach { this[it[0]]++ } }
        val q = ArrayDeque(inDegree.indices.filter{ inDegree[it] == 0 })
        val res = mutableListOf<Int>()
        while(q.isNotEmpty()) {
            val cur = q.removeFirst()
            res.add(cur)
            graph[cur]?.forEach {
                if (--inDegree[it] == 0) q.addLast(it)
            }
        }
        return if (res.size == numCourses) {
            res.toIntArray()
        } else {
            IntArray(0)
        }
    }
}

// 实现 findOrder 后运行 main。测试会接受任意合法顺序，不要求和示例完全一致。
fun main() {
    data class Case(
        val name: String,
        val numCourses: Int,
        val prerequisites: Array<IntArray>,
        val possible: Boolean = true
    )

    val cases = listOf(
        Case("多门课程无依赖", 5, emptyArray()),
        Case("示例 1：简单依赖", 2, arrayOf(intArrayOf(1, 0))),
        Case("示例 2：菱形依赖", 4, arrayOf(
            intArrayOf(1, 0), intArrayOf(2, 0), intArrayOf(3, 1), intArrayOf(3, 2)
        )),
        Case("示例 3：单门课程", 1, emptyArray()),
        Case("编号方向与学习顺序相反", 4, arrayOf(
            intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3)
        )),
        Case("一门课是多门课的前置", 5, Array(4) { intArrayOf(it + 1, 0) }),
        Case("一门课依赖多门课", 5, Array(4) { intArrayOf(4, it) }),
        Case("独立依赖链与孤立课程", 7, arrayOf(
            intArrayOf(1, 0), intArrayOf(2, 1), intArrayOf(4, 3)
        )),
        Case("依赖输入顺序打乱", 5, arrayOf(
            intArrayOf(4, 3), intArrayOf(2, 1), intArrayOf(3, 2), intArrayOf(1, 0)
        )),
        Case("直接依赖与间接依赖并存", 3, arrayOf(
            intArrayOf(1, 0), intArrayOf(2, 1), intArrayOf(2, 0)
        )),
        Case("两门课程互相依赖", 2, arrayOf(intArrayOf(0, 1), intArrayOf(1, 0)), false),
        Case("三门课程构成环", 3, arrayOf(
            intArrayOf(1, 0), intArrayOf(2, 1), intArrayOf(0, 2)
        ), false),
        Case("可完成部分课程但另有环", 6, arrayOf(
            intArrayOf(1, 0), intArrayOf(3, 2), intArrayOf(2, 3), intArrayOf(4, 3)
        ), false),
        Case("从无前置课程出发仍会遇到环", 4, arrayOf(
            intArrayOf(1, 0), intArrayOf(2, 1), intArrayOf(3, 2), intArrayOf(1, 3)
        ), false),
        Case("最大课程数无依赖", 2000, emptyArray()),
        Case("最大课程数长链", 2000, Array(1999) { intArrayOf(it + 1, it) }),
        Case("最大课程数长环", 2000, Array(2000) { intArrayOf((it + 1) % 2000, it) }, false)
    )

    // 仅检查返回结果是否合法，不提供求解算法。
    fun validate(case: Case, order: IntArray): String? {
        if (!case.possible) {
            return if (order.isEmpty()) null else "存在环时应返回空数组"
        }
        if (order.size != case.numCourses) return "应返回全部 ${case.numCourses} 门课程"
        val position = IntArray(case.numCourses) { -1 }
        for ((index, course) in order.withIndex()) {
            if (course !in position.indices) return "课程编号越界：$course"
            if (position[course] != -1) return "课程重复：$course"
            position[course] = index
        }
        for ((course, prerequisite) in case.prerequisites) {
            if (position[prerequisite] >= position[course]) {
                return "课程 $prerequisite 必须在课程 $course 之前"
            }
        }
        return null
    }

    val solution = Medium_210_CourseScheduleII()
    var passed = 0
    for (case in cases) {
        val actual = solution.findOrder(case.numCourses, case.prerequisites.map { it.copyOf() }.toTypedArray())
        val error = validate(case, actual)
        if (error == null) passed++
        println("${if (error == null) "PASS" else "FAIL"} | ${case.name} | ${error ?: "合法结果"}")
        if (error != null) println("actual=${actual.take(20)}${if (actual.size > 20) " ..." else ""}")
    }
    check(passed == cases.size) { "$passed/${cases.size} tests passed" }
    println("All ${cases.size} tests passed!")
}
