package com.home.learn

/**
 * 22. 括号生成
 * https://leetcode.com/problems/generate-parentheses/
 *
 * 给定整数 n，表示括号的对数。
 * 生成并返回所有由 n 对括号组成的有效括号字符串。
 * 有效括号必须正确配对，结果不能重复，可以按任意顺序返回。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))", "(()())", "(())()", "()(())", "()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 * 约束：1 <= n <= 8。
 */
class Medium_22_GenerateParentheses {
    fun generateParenthesis(n: Int): List<String> {
        //triple, current str, left used, right used
        val q = mutableListOf<Triple<String, Int, Int>>()
        val res = mutableListOf<String>()
        q.add(Triple("(", 1, 0))
        while (q.isNotEmpty()) {
            val cur = q.removeFirst()
            if(cur.second == n && cur.third == n) {
                res.add(cur.first)
            } else {
                if(cur.second < n) {
                    q.add(Triple(cur.first + "(", cur.second + 1, cur.third))
                }
                if(cur.third < cur.second) {
                    q.add(Triple(cur.first + ")", cur.second, cur.third + 1))
                }
            }
        }
        return res
    }
}

fun main() {
    val sol = Medium_22_GenerateParentheses()
    val cases = listOf(
        1 to listOf("()"),
        2 to listOf("(())", "()()"),
        3 to listOf("((()))", "(()())", "(())()", "()(())", "()()()")
    )

    for ((n, expected) in cases) {
        val actual = sol.generateParenthesis(n)
        // 忽略返回顺序，同时检查是否存在重复结果。
        val passed = actual.size == expected.size && actual.toSet() == expected.toSet()
        println("n = $n: ${if (passed) "PASS" else "FAIL"}")
        println("实际：$actual")
        println("期望：$expected")
    }
}
