package com.home.learn

/**
 * LeetCode 2267. Check if There Is a Valid Parentheses String Path
 * 检查是否有合法括号字符串路径
 * https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/
 *
 * 给定 m 行 n 列的字符网格 grid，每个格子是 '(' 或 ')'。
 * 从左上角 (0, 0) 出发，每次只能向右或向下移动，到达右下角 (m - 1, n - 1)。
 * 按经过顺序连接所有格子的字符（包含起点和终点）。
 * 判断是否存在一条路径，使得到的字符串是合法括号字符串。
 * 合法示例："()"、"(())"、"()()"；不合法示例：")("、"(()"。
 * 存在则返回 true，否则返回 false。
 *
 * 约束：1 <= m, n <= 100；grid 为矩形，所有元素均为 '(' 或 ')'。
 *
 * 示例 1（每个字符串表示一行）：["(((", ")()", "(()", "(()"] -> true
 * 存在得到 "()(())" 或 "((()))" 的路径。
 * 示例 2：["))", "(("] -> false
 */
class Hard_2267_CheckIfThereIsAValidParenthesesStringPath {
    fun hasValidPath(grid: Array<CharArray>): Boolean {
        if (grid.isEmpty() || grid[0].isEmpty()) return false
        val m = grid.size
        val n = grid[0].size
        if ((m + n - 1) % 2 != 0 || grid[0][0] != '(' || grid[m - 1][n - 1] != ')') {
            return false
        }
        val failed = Array(m) { Array(n) { BooleanArray(m + n) } }
        return dfs(grid, 0, 0, 0, failed)
    }

    // path 是进入当前格子之前，尚未配对的左括号数量。
    private fun dfs(
        grid: Array<CharArray>, path: Int, i: Int, j: Int,
        failed: Array<Array<BooleanArray>>
    ): Boolean {
        if (failed[i][j][path]) return false

        val balance = path + if (grid[i][j] == '(') 1 else -1
        val remaining = grid.lastIndex - i + grid[0].lastIndex - j
        // 右括号过多，或剩余格子即使全为右括号也无法配平。
        if (balance < 0 || balance > remaining) {
            failed[i][j][path] = true
            return false
        }
        if (remaining == 0) return balance == 0

        if (j < grid[0].lastIndex && dfs(grid, balance, i, j + 1, failed)) return true
        if (i < grid.lastIndex && dfs(grid, balance, i + 1, j, failed)) return true

        // 两个方向都失败，之后遇到相同位置和相同余额时可以直接跳过。
        failed[i][j][path] = true
        return false
    }
}

class Solution_Hard_2267 {
    val dirs = arrayOf((0 to 1), (1 to 0))
    fun hasValidPath(grid: Array<CharArray>): Boolean {
        return !(grid.isEmpty() || grid.last().last() != ')') &&
                dfs(grid, 0, 0, 0, Array(grid.size) { Array(grid[0].size) { BooleanArray(grid.size + grid[0].size) } })
    }

    fun dfs(grid: Array<CharArray>, path: Int, i: Int, j: Int, failed: Array<Array<BooleanArray>>): Boolean {
        if(i == grid.lastIndex && j == grid[0].lastIndex) {
            return path == 1
        }
        if (failed[i][j][path]) return false
        val delta = if (grid[i][j] == '(') 1 else -1
        if (path + delta < 0 || path + delta - (grid.lastIndex - i + grid[0].lastIndex - j) > 0) {
            failed[i][j][path] = true
            return false
        }
        for((dr, dc) in dirs) {
            val x = i + dr
            val y = j + dc
            if(grid.getOrNull(x) == null || grid[x].getOrNull(y) == null) { continue }
            if(dfs(grid, path + delta, x, y, failed)) return true
        }
        failed[i][j][path] = true
        return false
    }
}

// 直接运行 main 检查全部用例。
fun main() {
    data class Case(val name: String, val rows: List<String>, val expected: Boolean)

    val cases = listOf(
        Case("官方示例 1", listOf("(((", ")()", "(()", "(()"), true),
        Case("官方示例 2", listOf("))", "(("), false),
        Case("单格左括号", listOf("("), false),
        Case("单格右括号", listOf(")"), false),
        Case("最短横向合法路径", listOf("()"), true),
        Case("最短纵向合法路径", listOf("(", ")"), true),
        Case("顺序相反", listOf(")("), false),
        Case("单行嵌套括号", listOf("((()))"), true),
        Case("单行并列括号", listOf("()()()"), true),
        Case("数量相等但中途不合法", listOf("())(()"), false),
        Case("单行左括号过多", listOf("((()"), false),
        Case("单行右括号过多", listOf("()))"), false),
        Case("单列嵌套括号", listOf("(", "(", ")", ")"), true),
        Case("路径字符数为奇数", listOf("((", "()"), false),
        Case("起点是右括号", listOf(")((", "())"), false),
        Case("终点是左括号", listOf("(()", ")(("), false),
        Case("全是左括号", listOf("(((", "((("), false),
        Case("全是右括号", listOf(")))", ")))"), false),
        Case("必须先向右走", listOf("(()", ")))"), true),
        Case("必须先向下走", listOf("())", "())"), true),
        Case("有多条合法路径", listOf("(()", "())"), true),
        Case("起终点正确但无法配对", listOf("(((", "(()"), false),
        Case("最长单行合法路径", listOf("(".repeat(50) + ")".repeat(50)), true),
        Case("大网格存在深嵌套路径", List(100) { r ->
            CharArray(99) { c -> if (r + c < 99) '(' else ')' }.concatToString()
        }, true),
        Case("大网格所有路径缺一个右括号", List(100) { r ->
            CharArray(99) { c -> if (r + c < 100) '(' else ')' }.concatToString()
        }, false),
        Case("最大网格路径长度为奇数", List(100) { r ->
            CharArray(100) { c -> if ((r + c) % 2 == 0) '(' else ')' }.concatToString()
        }, false)
    )

    val solution = Hard_2267_CheckIfThereIsAValidParenthesesStringPath()
    var passed = 0
    for ((name, rows, expected) in cases) {
        val actual = solution.hasValidPath(rows.map { it.toCharArray() }.toTypedArray())
        val success = actual == expected
        if (success) passed++
        println("${if (success) "PASS" else "FAIL"} | $name | expected=$expected, actual=$actual")
    }
    check(passed == cases.size) { "$passed/${cases.size} tests passed" }
    println("All ${cases.size} tests passed!")
}
