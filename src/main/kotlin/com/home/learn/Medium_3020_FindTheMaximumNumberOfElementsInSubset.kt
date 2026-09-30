package com.home.learn

import kotlin.math.max

class Medium_3020_FindTheMaximumNumberOfElementsInSubset {
    fun maximumLength(nums: IntArray): Int {
        val cnt = nums.asIterable().groupingBy { it.toLong() }.eachCount().toMutableMap()
        val oneCnt = cnt.remove(1) ?: 0
        var ans = if(oneCnt % 2 == 1) oneCnt else oneCnt - 1
        for(c in cnt.keys) {
            var res = 0
            var x = c
            while((cnt[x] ?: 0) > 1) {
                res += 2
                x *= x
            }
            ans = max(ans, res + if ((cnt[x] ?: 0) > 0) 1 else -1)
        }
        return ans
    }
}

fun main() {
    val t = Medium_3020_FindTheMaximumNumberOfElementsInSubset()
    println(t.maximumLength(intArrayOf(5, 4, 1, 2, 2))) // 3
    println(t.maximumLength(intArrayOf(1, 3, 2, 4))) // 1
}
