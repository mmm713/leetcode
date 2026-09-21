package com.home.learn

class Hard_940_DistinctSubsequencesII {

    fun distinctSubseqII(s: String): Int {
        val MOD = 1_000_000_007
        val f = IntArray(26)
        var total = 0

        for (ch in s) {
            val c = ch - 'a'
            // total 中不含以当前字符结尾的子序列数（+MOD 防止负数）
            val others = (total - f[c] + MOD) % MOD
            f[c] = (1 + total) % MOD
            total = (f[c] + others) % MOD
        }

        return total
    }
}

fun main() {
    val t = Hard_940_DistinctSubsequencesII()
    println(t.distinctSubseqII("aaa")) // 3
    println(t.distinctSubseqII("abc")) // 7
    println(t.distinctSubseqII("aba")) // 6
}
