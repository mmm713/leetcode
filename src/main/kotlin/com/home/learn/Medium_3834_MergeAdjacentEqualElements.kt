package com.home.learn

class Medium_3834_MergeAdjacentEqualElements {
    fun mergeAdjacent(nums: IntArray): LongArray {
        val res = mutableListOf<Long>()
        for (i in nums.indices) {
            res.add(nums[i].toLong())
            while(res.size >= 2 && res.last() == res[res.lastIndex - 1]) {
                res.removeLast()
                res[res.lastIndex] *= 2
            }
        }
        return res.toLongArray()
    }
}

fun main() {
    val t = Medium_3834_MergeAdjacentEqualElements()
    println(t.mergeAdjacent(intArrayOf(3, 1, 1, 2)).contentToString()) // [3, 4]
    println(t.mergeAdjacent(intArrayOf(2, 2, 4)).contentToString()) // [8]
    println(t.mergeAdjacent(intArrayOf(3, 7, 5)).contentToString()) // [3, 7, 5]
}
