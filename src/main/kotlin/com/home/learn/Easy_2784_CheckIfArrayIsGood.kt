package com.home.learn

import kotlin.math.abs

class Easy_2784_CheckIfArrayIsGood {
    fun isGood(nums: IntArray): Boolean {
        var res = false
        for(i in 0 until nums.size) {
            val num = abs(nums[i]) - 1
            if(num < 0 || num > nums.size - 2) {
                return false
            }
            if(nums[num] < 0) {
                if(num == nums.size - 2 && !res) {
                    res = true
                } else {
                    return false
                }
            } else {
                nums[num] = -nums[num]
            }
        }
        return res
    }
}

fun main() {
    val t = Easy_2784_CheckIfArrayIsGood()
    println(t.isGood(intArrayOf(2, 1, 3))) // false
    println(t.isGood(intArrayOf(1, 3, 3, 2))) // true
    println(t.isGood(intArrayOf(1, 1))) // true
    println(t.isGood(intArrayOf(3, 4, 4, 1, 2, 1))) // false
}
