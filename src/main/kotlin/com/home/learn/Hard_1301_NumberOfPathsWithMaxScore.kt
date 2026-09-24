package com.home.learn

class Hard_1301_NumberOfPathsWithMaxScore {
    data class Mem(
        var sum: Int = -1,
        var count: Int = 0
    )

    fun pathsWithMaxScore(board: List<String>): IntArray {
        val mod = 1_000_000_007L;
        val m = board.size
        val n = board[0].length
        val mem = Array(m + 1) { Array(n + 1) { Mem() } }
        mem[0][0].sum = 0
        mem[0][0].count = 1
        for(i in 0 until m) {
            for(j in 0 until n) {
                val c = board[i][j]
                if(c == 'X') {
                    continue
                }
                val prev = arrayOf(mem[i][j], mem[i + 1][j], mem[i][j + 1])
                val max = prev.maxOf { it.sum }
                val cnt = prev.filter { it.sum == max }.sumOf { it.count }
                mem[i + 1][j + 1].count = (cnt % mod).toInt()
                if (max >= 0) {
                    val score = c.digitToIntOrNull() ?: 0
                    mem[i + 1][j + 1].sum = max + score
                }
            }
        }
        return if(mem[m][n].sum < 0) intArrayOf(0, 0) else intArrayOf(mem[m][n].sum, mem[m][n].count)
    }
}

fun main() {
    val t = Hard_1301_NumberOfPathsWithMaxScore()
    println(t.pathsWithMaxScore(listOf("E23", "2X2", "12S")).contentToString()) // [7, 1]
    println(t.pathsWithMaxScore(listOf("E12", "1X1", "21S")).contentToString()) // [4, 2]
    println(t.pathsWithMaxScore(listOf("E11", "XXX", "11S")).contentToString()) // [0, 0]
}
