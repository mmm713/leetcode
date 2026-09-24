package com.home.learn

import kotlin.math.min

class Medium_1658_MinimumOperationsToReduceXToZero {
    fun minOperations(nums: IntArray, x: Int): Int {
        val target = nums.sum() - x
        if(target < 0) {
            return -1
        }
        var l = 0
        var sum = 0
        var res = Int.MAX_VALUE
        for((r, e) in nums.withIndex()) {
            sum += e
            while(sum > target) {
                sum -= nums[l++]
            }
            if(sum == target) {
                res = min(res, nums.size - (r - l + 1))
            }
        }
        return if (res == Int.MAX_VALUE) -1 else res
    }
}

fun main() {
    val t = Medium_1658_MinimumOperationsToReduceXToZero()
    println(t.minOperations(intArrayOf(1, 1, 4, 2, 3), 5)) // 2
    println(t.minOperations(intArrayOf(5, 6, 7, 8, 9), 4)) // -1
    println(t.minOperations(intArrayOf(3, 2, 20, 1, 1, 3), 10)) // 5
}
