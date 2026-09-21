package com.home.learn

class Medium_3871_CountCommasInRangeII {

    fun countCommas(n: Long): Long {
        var ans = 0L;
        // 从低到高，枚举逗号的位置
        var low = 1000L
        while (low <= n) {
            // [low, n] 中的每个数都在这个位置上有一个逗号
            ans += n - low + 1;
            low *= 1000L
        }
        return ans;
    }
}

fun main() {
    val t = Medium_3871_CountCommasInRangeII()
    println(t.countCommas(1002L)) // 3
    println(t.countCommas(998L)) // 0
}
