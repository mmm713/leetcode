package com.home.learn

import kotlin.math.max

class Easy_3471_FindTheLargestAlmostMissingInteger {

    fun largestInteger(nums: IntArray, k: Int): Int {
        if (k == nums.size) {
            return nums.max()
        }
        if(k == 1) {
            return nums.asIterable()
                .groupingBy { it }
                .eachCount()
                .filterValues { it == 1 }
                .keys
                .maxOrNull() ?: -1
        }
        return listOf(nums.first(), nums.last())
            .filter { candidate -> nums.count { it == candidate } == 1 }
            .maxOrNull() ?: -1
    }
}

fun main() {
    val t = Easy_3471_FindTheLargestAlmostMissingInteger()
    println(t.largestInteger(intArrayOf(3, 9, 2, 1, 7), 3)) // 7
    println(t.largestInteger(intArrayOf(3, 9, 7, 2, 1, 7), 4)) // 3
    println(t.largestInteger(intArrayOf(0, 0), 1)) // -1
}
