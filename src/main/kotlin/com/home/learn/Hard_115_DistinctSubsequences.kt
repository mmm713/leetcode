package com.home.learn

class Hard_115_DistinctSubsequences {
    fun numDistinct(s: String, t: String): Int {
        val m = s.length
        val n = t.length
        val dp = Array(n + 1) { IntArray(m + 1) }
        dp[0].fill(1)
        for (i in 1..n) {
            for (j in 1..m) {
                dp[i][j] = dp[i][j - 1] + if (t[i - 1] == s[j - 1]) dp[i - 1][j - 1] else 0
            }
        }
        return dp[n][m]
    }
}

fun main() {
    val t = Hard_115_DistinctSubsequences()
    println(t.numDistinct("rabbbit", "rabbit")) // 3
    println(t.numDistinct("babgbag", "bag")) // 5
}
