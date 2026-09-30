package com.home.learn

import java.util.Stack

class Easy_1470_ShuffleTheArray {
    fun shuffle(nums: IntArray, n: Int): IntArray {
        for ((i, e) in nums.withIndex()) {
            var x = e
            if(x < 0) {
                continue
            }
            var cur = i
            while(true) {
                val next = if(cur < n) cur * 2 else (cur - n) * 2 + 1
                if(next == i) {
                    nums[i] = -x
                    break
                }
                nums[next] = -x.also { x = nums[next] }
                cur = next
            }
        }
        nums.indices.forEach { nums[it] = -nums[it] }
        return nums
    }
}

fun main() {
    val t = Easy_1470_ShuffleTheArray()
    println(t.shuffle(intArrayOf(2, 5, 1, 3, 4, 7), 3).contentToString()) // [2, 3, 5, 4, 1, 7]
    println(t.shuffle(intArrayOf(1, 2, 3, 4, 4, 3, 2, 1), 4).contentToString()) // [1, 4, 2, 3, 3, 2, 4, 1]
    println(t.shuffle(intArrayOf(1, 1, 2, 2), 2).contentToString()) // [1, 2, 1, 2]
}
