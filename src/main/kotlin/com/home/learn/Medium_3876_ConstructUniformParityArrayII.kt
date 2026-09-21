package com.home.learn

import kotlin.math.min

class Medium_3876_ConstructUniformParityArrayII {

    fun uniformArray(nums1: IntArray): Boolean {
        return nums1.min() % 2 == 1 || nums1.all { it % 2 == 0 }
    }
}

fun main() {
    val t = Medium_3876_ConstructUniformParityArrayII()
    println(t.uniformArray(intArrayOf(1, 4, 7))) // true
    println(t.uniformArray(intArrayOf(2, 3))) // false
    println(t.uniformArray(intArrayOf(4, 6))) // true
}
