package com.home.learn

import kotlin.math.*

class Medium_2091_RemovingMinimumAndMaximumFromArray {

    fun minimumDeletions(nums: IntArray): Int {
        if (nums.size < 3)
            return nums.size
        var imin = 0
        var imax = 0
        var min = Integer.MAX_VALUE
        var max = Integer.MIN_VALUE
        for ((i, element) in nums.withIndex()) {
            if (element < min) {
                min = element
                imin = i
            }
            if (element > max) {
                max = element
                imax = i
            }
        }
        val d0 = min(imin, imax) - 0
        val d1 = abs(imax - imin) - 1
        val d2 = nums.size - 1 - max(imin, imax)
        val res = min(d0 + d1, min(d0 + d2, d1 + d2))
        return res + 2
    }
}

fun main() {
    val t = Medium_2091_RemovingMinimumAndMaximumFromArray()
    println(t.minimumDeletions(intArrayOf(2, 10, 7, 5, 4, 1, 8, 6))) // 5
    println(t.minimumDeletions(intArrayOf(0, -4, 19, 1, 8, -2, -3, 5))) // 3
    println(t.minimumDeletions(intArrayOf(101))) // 1
}
