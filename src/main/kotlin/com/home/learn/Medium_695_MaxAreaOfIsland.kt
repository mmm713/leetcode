package com.home.learn

import kotlin.math.max

/**
 * 695. 岛屿的最大面积
 *
 * 给定一个仅包含 0 和 1 的 m × n 网格 grid，0 表示水，1 表示陆地。
 * 岛屿由上下左右相邻的陆地组成，面积是其中陆地单元格的数量。
 * 返回最大的岛屿面积；如果没有岛屿，返回 0。
 *
 * 约束：1 <= m, n <= 50。
 */
class Medium_695_MaxAreaOfIsland {
    val dirs = arrayOf((0 to 1), (1 to 0), (0 to -1), (-1 to 0))
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        if(grid.isEmpty()) return 0
        val visited = Array(grid.size) { BooleanArray(grid[0].size) }
        var res = 0
        for(i in grid.indices) {
            for (j in grid[0].indices) {
                if(!visited[i][j] && grid[i][j] == 1) {
                    res = max(res, dfs(grid, i, j, visited))
                }
            }
        }
        return res
    }

    fun dfs(grid: Array<IntArray>, i: Int, j: Int, visited: Array<BooleanArray>): Int {
        if(grid.getOrNull(i)?.getOrNull(j) != 1) {
            return 0
        }
        visited[i][j] = true
        var sum = 1
        for((dr, dc) in dirs) {
            if(visited.getOrNull(i + dr)?.getOrNull(j + dc) == true)
                continue
            sum += dfs(grid, i + dr, j + dc, visited)
        }
        return sum
    }
}

fun main() {
    val sol = Medium_695_MaxAreaOfIsland()

    println(sol.maxAreaOfIsland(arrayOf(
        intArrayOf(0, 0, 1, 0),
        intArrayOf(1, 1, 1, 0),
        intArrayOf(0, 1, 0, 1)
    ))) // 5

    println(sol.maxAreaOfIsland(arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    ))) // 0

    println(sol.maxAreaOfIsland(arrayOf(
        intArrayOf(1, 1),
        intArrayOf(1, 1)
    ))) // 4

    println(sol.maxAreaOfIsland(arrayOf(
        intArrayOf(1, 0),
        intArrayOf(0, 1)
    ))) // 1（对角相邻不属于同一个岛屿）
}
