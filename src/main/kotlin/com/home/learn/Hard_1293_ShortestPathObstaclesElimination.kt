package com.home.learn

class Hard_1293_ShortestPathObstaclesElimination {
    private val DIRS = arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

    fun shortestPath(grid: Array<IntArray>, k: Int): Int {
        val m = grid.size; val n = grid[0].size
        val oCnt = grid.sumOf { row -> row.count { it == 1 } }
        if(k > m + n - 3 || k > oCnt) {
            return m + n - 2
        }
        var res = -1
        val queue = ArrayDeque(mutableListOf(Triple(0, 0, k)))
        val visit = Array(m) { IntArray(n) { Int.MIN_VALUE } }
        visit[0][0] = k
        while(queue.isNotEmpty()) {
            res++
            repeat(queue.size) {
                val cur = queue.removeFirst()
                if(visit[cur.first][cur.second] > cur.third) {
                    return@repeat
                }
                if(cur.first == m - 1 && cur.second == n - 1) {
                    return res
                }
                for ((dr, dc) in DIRS) {
                    val nr = cur.first + dr
                    val nc = cur.second + dc
                    if (nr !in 0 until m || nc !in 0 until n) continue
                    if (grid[nr][nc] == 0 && visit[nr][nc] < cur.third) {
                        visit[nr][nc] = cur.third
                        queue.add(Triple(nr, nc, visit[nr][nc]))
                    }
                    if (grid[nr][nc] == 1 && cur.third > 0 && visit[nr][nc] < cur.third - 1) {
                        visit[nr][nc] = cur.third - 1
                        queue.add(Triple(nr, nc, visit[nr][nc]))
                    }
                }
            }
        }
        return -1
    }

    fun shortestPathSimple(grid: Array<IntArray>, k: Int): Int {
        val m = grid.size; val n = grid[0].size
        val oCnt = grid.sumOf { row -> row.count { it == 1 } }
        if(k > m + n - 3 || k > oCnt) {
            return m + n - 2
        }
        var res = -1
        val queue = ArrayDeque(mutableListOf(Triple(0, 0, k)))
        val visit = Array(m) { IntArray(n) { Int.MIN_VALUE } }
        visit[0][0] = k
        while(queue.isNotEmpty()) {
            res++
            repeat(queue.size) {
                val (r, c, remain) = queue.removeFirst()
                if (visit[r][c] > remain) return@repeat
                if (r == m - 1 && c == n - 1) return res

                for ((dr, dc) in DIRS) {
                    val nr = r + dr
                    val nc = c + dc
                    if (nr !in 0 until m || nc !in 0 until n) continue

                    val nk = if (grid[nr][nc] == 1) remain - 1 else remain
                    if (nk < 0 || nk <= visit[nr][nc]) continue

                    visit[nr][nc] = nk
                    queue.addLast(Triple(nr, nc, nk))
                }
            }
        }
        return -1
    }
}

fun main() {    val t = Hard_1293_ShortestPathObstaclesElimination()
    val grid = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(1, 1, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 1, 1),
        intArrayOf(0, 0, 0)
    )
    println(t.shortestPath(grid, 1))
}