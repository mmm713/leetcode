package com.home.learn

/**
 * LeetCode 44. Wildcard Matching（通配符匹配）
 * https://leetcode.com/problems/wildcard-matching/
 *
 * 给定字符串 s 和模式 p，判断 p 能否匹配整个 s，返回 Boolean。
 * - '?' 匹配恰好一个任意字符。
 * - '*' 匹配任意长度的字符序列，包括空字符串。
 * - 普通字母只能匹配相同字母。
 * 必须匹配整个字符串，而不是其中的某个子串。
 *
 * 约束：0 <= s.length, p.length <= 2000。
 * s 只包含小写英文字母；p 只包含小写英文字母、'?' 和 '*'。
 *
 * 示例：s = "aa", p = "a" -> false
 * 示例：s = "aa", p = "*" -> true
 * 示例：s = "cb", p = "?a" -> false
 */
class Hard_44_WildcardMatching {
    fun isMatch(s: String, p: String): Boolean {
        var si = 0; var pi = 0; var ss = -1; var ps = -1
        while (si < s.length) {
            if (s[si] == p.getOrNull(pi) || p.getOrNull(pi) == '?') {
                si++
                pi++
            } else if (p.getOrNull(pi) == '*') {
                ss = si
                ps = pi++
            } else if (ps != -1) {
                si = ++ss
                pi = ps + 1
            } else return false
        }
        return (pi until p.length).all { p[it] == '*' }
    }
}

// 实现 isMatch 后，直接运行 main 检查全部用例。
fun main() {
    data class Case(val name: String, val s: String, val p: String, val expected: Boolean)

    val cases = listOf(
        Case("示例 1：不能只匹配一部分", "aa", "a", false),
        Case("示例 2：星号匹配全部", "aa", "*", true),
        Case("示例 3：普通字母不匹配", "cb", "?a", false),
        Case("两个空串", "", "", true),
        Case("空串与星号", "", "*", true),
        Case("空串与连续星号", "", "***", true),
        Case("问号不能匹配空串", "", "?", false),
        Case("星号不能抵消问号", "", "*?*", false),
        Case("非空串与空模式", "a", "", false),
        Case("普通字母完全相同", "abc", "abc", true),
        Case("普通字母不同", "abc", "abd", false),
        Case("模式长于字符串", "ab", "abc", false),
        Case("单个问号", "a", "?", true),
        Case("多个问号", "abc", "???", true),
        Case("问号不能匹配多个字符", "ab", "?", false),
        Case("中间星号匹配空串", "ab", "a*b", true),
        Case("中间星号匹配多个字符", "axyzb", "a*b", true),
        Case("末尾星号匹配空串", "abc", "abc*", true),
        Case("开头星号匹配空串", "abc", "*abc", true),
        Case("连续星号与问号混合", "abc", "**a**?**c**", true),
        Case("多个星号分段匹配", "adceb", "*a*b", true),
        Case("多个通配符仍然无解", "acdcb", "a*c?b", false),
        Case("重复字母需要重新选择匹配位置", "aaaab", "*aab", true),
        Case("必须匹配到字符串末尾", "abcde", "*b*d", false),
        Case("星号两侧都有问号", "abc", "?*?", true),
        Case("字符不够两个问号", "a", "?*?", false),
        Case("最大长度普通匹配", "a".repeat(2000), "a".repeat(2000), true),
        Case("最大长度问号匹配", "a".repeat(2000), "?".repeat(2000), true),
        Case("最大长度连续星号", "a".repeat(2000), "*".repeat(2000), true),
        Case("较长输入末尾不匹配", "a".repeat(2000), "*a".repeat(999) + "*b", false)
    )

    val solution = Hard_44_WildcardMatching()
    var passed = 0
    for ((name, s, p, expected) in cases) {
        val actual = solution.isMatch(s, p)
        val success = actual == expected
        if (success) passed++
        println("${if (success) "PASS" else "FAIL"} | $name | expected=$expected, actual=$actual")
    }
    check(passed == cases.size) { "$passed/${cases.size} tests passed" }
    println("All ${cases.size} tests passed!")
}
