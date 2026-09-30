package com.home.learn

/**
 * LeetCode 691. Stickers to Spell Word（贴纸拼词）
 * https://leetcode.com/problems/stickers-to-spell-word/
 *
 * 给定贴纸种类 stickers 和目标字符串 target，每种贴纸都有无限张。
 * 可以剪下贴纸中的字母并任意重排，拼出 target；不需要的字母可以丢弃。
 * 每张贴纸上的每个字母只能使用一次，使用同种贴纸多张时分别计数。
 * 返回所需贴纸的最少张数；无法拼出则返回 -1。
 *
 * 约束：1 <= stickers.size <= 50；1 <= stickers[i].length <= 10；
 * 1 <= target.length <= 15；所有字符串只包含小写英文字母。
 *
 * 示例 1：stickers = ["with", "example", "science"], target = "thehat" -> 3
 * 可以使用两张 "with" 和一张 "example"。
 * 示例 2：stickers = ["notice", "possible"], target = "basicbasic" -> -1
 */
class Hard_691_StickersToSpellWord {
    fun minStickers(stickers: Array<String>, target: String): Int {
        TODO("自己实现")
    }

}

// 实现 minStickers 后，直接运行 main 检查全部用例。
// 除官方示例外，包含为边界测试构造的字母串，均满足上述字符和长度约束。
fun main() {
    data class Case(val name: String, val stickers: Array<String>, val target: String, val expected: Int)

    val cases = listOf(
        Case("官方示例 1", arrayOf("with", "example", "science"), "thehat", 3),
        Case("官方示例 2", arrayOf("notice", "possible"), "basicbasic", -1),
        Case("单字母匹配", arrayOf("a"), "a", 1),
        Case("单字母缺失", arrayOf("a"), "b", -1),
        Case("一张完整匹配", arrayOf("hello"), "hello", 1),
        Case("字母可重排", arrayOf("abc"), "cba", 1),
        Case("多余字母可丢弃", arrayOf("abcdef"), "ace", 1),
        Case("同种贴纸可重复使用", arrayOf("ab"), "aaabbb", 3),
        Case("同一张上的重复字母可用", arrayOf("aaa"), "aaaaa", 2),
        Case("不同字母需求量不相等", arrayOf("ab"), "aaaab", 4),
        Case("组合两种贴纸", arrayOf("ab", "cd"), "abcd", 2),
        Case("每个字母来自不同贴纸", arrayOf("a", "b", "c"), "abcabc", 6),
        Case("多个方案取最少", arrayOf("a", "b", "ab"), "aabb", 2),
        Case("单张覆盖优于组合", arrayOf("ab", "bc", "abc"), "abc", 1),
        Case("无关贴纸不影响答案", arrayOf("xyz", "ab", "c"), "abc", 2),
        Case("目标中只有一个字母缺失", arrayOf("ab", "bc"), "abcd", -1),
        Case("字母齐全但需要多张", arrayOf("ab", "ac"), "aabbcc", 4),
        Case("不能只按单张覆盖数量贪心", arrayOf("abcd", "abe", "cdf"), "abcdef", 2),
        Case("目标长度上限", arrayOf("a"), "a".repeat(15), 15),
        Case("贴纸长度上限", arrayOf("a".repeat(10)), "a".repeat(15), 2),
        Case("目标包含十五种字母", arrayOf("abcdefghij", "klmno"), "abcdefghijklmno", 2),
        Case("大量无关贴纸", Array(50) { if (it == 49) "a" else "z".repeat(it % 10 + 1) }, "aaa", 3)
    )

    val solution = Hard_691_StickersToSpellWord()
    var passed = 0
    for ((name, stickers, target, expected) in cases) {
        val actual = solution.minStickers(stickers.copyOf(), target)
        val success = actual == expected
        if (success) passed++
        println("${if (success) "PASS" else "FAIL"} | $name | expected=$expected, actual=$actual")
    }
    check(passed == cases.size) { "$passed/${cases.size} tests passed" }
    println("All ${cases.size} tests passed!")
}
