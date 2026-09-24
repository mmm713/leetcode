package com.home.learn

class Medium_1559_DetectCyclesIn2DGrid {
    private val DIRS = arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

    fun containsCycle(grid: Array<CharArray>): Boolean {
        val m = grid.size
        val n = grid[0].size
        val visited = Array(m) { BooleanArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if(!visited[i][j] && dfs(i, j, -1, -1, grid, visited)) {
                    return true
                }
            }
        }
        return false
    }

    private fun dfs(i: Int, j: Int, prevI: Int, prevJ: Int, grid: Array<CharArray>, visited: Array<BooleanArray>): Boolean {
        visited[i][j] = true
        for ((dr, dc) in DIRS) {
            val nextI = i + dr
            val nextJ = j + dc
            if((nextI != prevI || nextJ != prevJ) &&
                (nextI >= 0 && nextI < grid.size && nextJ >= 0 && nextJ < grid[nextI].size) &&
                (grid[i][j] == grid[nextI][nextJ]) &&
                (visited[nextI][nextJ] || dfs(nextI, nextJ, i, j, grid, visited))) {
                return true
            }
        }
        return false
    }
}

fun main() {
    val t = Medium_1559_DetectCyclesIn2DGrid()
    println(t.containsCycle(arrayOf(
        "aaaa".toCharArray(),
        "abba".toCharArray(),
        "abba".toCharArray(),
        "aaaa".toCharArray()
    ))) // true
    println(t.containsCycle(arrayOf(
        "ccca".toCharArray(),
        "cdcc".toCharArray(),
        "ccec".toCharArray(),
        "fccc".toCharArray()
    ))) // true
    println(t.containsCycle(arrayOf(
        "abb".toCharArray(),
        "bzb".toCharArray(),
        "bba".toCharArray()
    ))) // false
    println(t.containsCycle(arrayOf(
        "b".toCharArray(),
        "b".toCharArray()
    ))) // false
}
