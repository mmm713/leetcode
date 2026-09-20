package com.home.learn

import kotlin.math.max
import kotlin.math.min

class Medium_3904_SmallestStableIndexII {

    fun firstStableIndex(nums: IntArray, k: Int): Int {
        val maxValues = nums.runningReduce { currentMax, value ->
            max(currentMax, value)
        }

        val minValues = nums
            .reversedArray()
            .runningReduce { currentMin, value ->
                min(currentMin, value)
            }
            .reversed()

        return nums.indices.firstOrNull { i ->
            maxValues[i] - minValues[i] <= k
        } ?: -1
    }
}

fun main() {
    val t = Medium_3904_SmallestStableIndexII()
    println(t.firstStableIndex(intArrayOf(5, 0, 1, 4), 3)) // 3
    println(t.firstStableIndex(intArrayOf(3, 2, 1), 1)) // -1
    println(t.firstStableIndex(intArrayOf(0), 0)) // 0
}
