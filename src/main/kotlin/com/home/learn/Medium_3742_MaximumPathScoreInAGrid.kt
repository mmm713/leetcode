package com.home.learn

import kotlin.math.max

class Medium_3742_MaximumPathScoreInAGrid {

    val dirs = arrayOf(
        intArrayOf(1, 0), // 下
        intArrayOf(0, 1)  // 右
    )
    fun maxPathScore(grid: Array<IntArray>, k: Int): Int {
        val m = grid.size
        val n = grid[0].size
        val mem = Array(m) {
            Array(n) {
                IntArray(k + 1) { -1 }
            }
        }
        mem[0][0][0] = 0;
        for (i in 0 until m) {
            for (j in 0 until n) {
                for (c in 0 .. k) {
                    if(mem[i][j][c] == -1)
                        continue
                    if(i + 1 < m) {
                        val v = grid[i + 1][j]
                        val cost = if (v == 0) 0 else 1
                        if(c + cost <= k) {
                            mem[i + 1][j][c + cost] = max(mem[i + 1][j][c + cost], mem[i][j][c] + v)
                        }
                    }
                    if (j + 1 < n) {
                        val v = grid[i][j + 1]
                        val cost = if (v == 0) 0 else 1
                        if(c + cost <= k) {
                            mem[i][j + 1][c + cost] = max(mem[i][j + 1][c + cost], mem[i][j][c] + v)
                        }
                    }
                }
            }
        }
        return mem[m-1][n-1].max()
    }
}

fun main() {
    val t = Medium_3742_MaximumPathScoreInAGrid()
    println(t.maxPathScore(arrayOf(intArrayOf(0, 1), intArrayOf(2, 0)), 1)) // 2
    println(t.maxPathScore(arrayOf(intArrayOf(0, 1), intArrayOf(1, 2)), 1)) // -1
}
