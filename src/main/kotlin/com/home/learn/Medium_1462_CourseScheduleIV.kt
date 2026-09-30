package com.home.learn

class Medium_1462_CourseScheduleIV {
    fun checkIfPrerequisite(
        numCourses: Int,
        prerequisites: Array<IntArray>,
        queries: Array<IntArray>
    ): List<Boolean> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        prerequisites.forEach {
            graph.getOrPut(it[0]) { mutableListOf() }.add(it[1])
        }
        return queries.map { dfs(graph, it[0], it[1]) }
    }

    private fun dfs(graph : MutableMap<Int, MutableList<Int>>, i :Int, j: Int): Boolean {
        val node = graph.getOrDefault(i, listOf())
        for(n in node) {
            return n == j || dfs(graph, n, j)
        }
        return false
    }
}

fun main() {
    val t = Medium_1462_CourseScheduleIV()
    println(t.checkIfPrerequisite(
        2,
        arrayOf(intArrayOf(1, 0)),
        arrayOf(intArrayOf(0, 1), intArrayOf(1, 0))
    )) // [false, true]
    println(t.checkIfPrerequisite(
        2,
        emptyArray(),
        arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))
    )) // [false, false]
    println(t.checkIfPrerequisite(
        3,
        arrayOf(intArrayOf(1, 2), intArrayOf(1, 0), intArrayOf(2, 0)),
        arrayOf(intArrayOf(1, 0), intArrayOf(1, 2))
    )) // [true, true]
}
