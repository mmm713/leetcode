package com.home.learn

class Medium_3756_ConcatenateNonZeroDigitsAndMultiplyBySumII {

    fun sumAndMultiply(s: String, queries: Array<IntArray>): IntArray {
        val mod = 1_000_000_007L
        // 下标 i 对应前 i 个字符
        val preSums = s.runningFold(0) { sum, ch ->
            sum + (ch - '0')
        }.toIntArray()

        val prefix = s.runningFold(0L) { num, ch ->
            if (ch == '0') num else (num * 10 + (ch - '0')) % mod
        }.toLongArray()

        val count = s.runningFold(0) { total, ch ->
            total + if (ch == '0') 0 else 1
        }.toIntArray()

        val pow10 = generateSequence(1L) { it * 10 % mod }
            .take(s.length + 1)
            .toList()
            .toLongArray()

        return IntArray(queries.size) { i ->
            val (l, r) = queries[i]
            val sum = preSums[r + 1] - preSums[l]
            val digits = count[r + 1] - count[l]
            val num = (prefix[r + 1] - prefix[l] * pow10[digits] % mod + mod) % mod
            (sum.toLong() * num % mod).toInt()
        }
    }
}

fun main() {
    val t = Medium_3756_ConcatenateNonZeroDigitsAndMultiplyBySumII()
    println(t.sumAndMultiply("1000", arrayOf(
        intArrayOf(0, 3),
        intArrayOf(1, 1)
    )).contentToString()) // [1, 0]
    println(t.sumAndMultiply("10203004", arrayOf(
        intArrayOf(0, 7),
        intArrayOf(1, 3),
        intArrayOf(4, 6)
    )).contentToString()) // [12340, 4, 9]
    println(t.sumAndMultiply("9876543210", arrayOf(
        intArrayOf(0, 9)
    )).contentToString()) // [444444137]
}
