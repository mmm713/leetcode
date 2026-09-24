package com.home.learn

class Easy_3550_SmallestIndexWithDigitSumEqualToIndex {
    fun smallestIndex(nums: IntArray): Int {
        for((i, element) in nums.withIndex()) {
            var sum = 0
            var x = element
            while(x > 0) {
                sum += x % 10
                x /= 10
            }
            if(sum == i) {
                return i
            }
        }
        return -1
    }
}

fun main() {
    val t = Easy_3550_SmallestIndexWithDigitSumEqualToIndex()
    println(t.smallestIndex(intArrayOf(1, 3, 2))) // 2
    println(t.smallestIndex(intArrayOf(1, 10, 11))) // 1
    println(t.smallestIndex(intArrayOf(1, 2, 3))) // -1
}
