package com.home.learn

import kotlin.math.min

class Medium_1477_FindTwoNonOverlappingSubArraysEachWithTargetSum {

    fun minSumOfLengths(arr: IntArray, target: Int): Int {
        val n = arr.size
        // sufMin[i] 表示左端点 >= i 的和为 target 的最短子数组长度
        // 不存在子数组时，长度设为 n+1
        val sufMin = IntArray(n)
        var minL = n + 1
        var sum = 0
        var r = n - 1
        for (l in n - 1 downTo 0) {
            sum += arr[l]
            while(sum > target) {
                sum -= arr[r]
                r--
            }
            if(sum == target) {
                minL = min(minL, r - l + 1)
            }
            sufMin[l] = minL //保存此刻的min
        }

        var ans = n + 1;
        sum = 0
        var l = 0
        for(r in 0 until n-1) {
            sum += arr[r]
            while(sum > target) {
                sum -= arr[l]
                l++
            }
            if(sum == target) {
                ans = ans.coerceAtMost(r - l + 1 + sufMin[r + 1])
            }
        }
        return if (ans > n) -1 else ans
    }
}

fun main() {
    val t = Medium_1477_FindTwoNonOverlappingSubArraysEachWithTargetSum()
    println(t.minSumOfLengths(intArrayOf(3, 2, 2, 4, 3), 3)) // 2
    println(t.minSumOfLengths(intArrayOf(7, 3, 4, 7), 7)) // 2
    println(t.minSumOfLengths(intArrayOf(4, 3, 2, 6, 2, 3, 4), 6)) // -1
}
