package com.home.learn

class Medium_2948_MakeLexicographicallySmallestArrayBySwappingElements {

    fun lexicographicallySmallestArray(nums: IntArray, limit: Int): IntArray {
        val n = nums.size
        val pos = nums.indices.sortedBy { nums[it] }.toIntArray()

        val ans = IntArray(n)
        var start = 0
        for (i in 0 until n) {
            if (i == n-1 || nums[pos[i + 1]] - nums[pos[i]] > limit) {
                // subPos 是 ans 中的一组空位（不一定有序）
                // 我们需要把 subPos 对应的 nums 中的数从小到大地填入空位（从左到右填）
                // 为了能从左到右填，需要把 subPos 排序
                val subPos = pos.copyOfRange(start, i + 1)
                subPos.sort()
                for((j, idx) in subPos.withIndex()) {
                    ans[idx] = nums[pos[start + j]]
                }
                start = i + 1
            }
        }
        return ans
    }
}

fun main() {
    val t = Medium_2948_MakeLexicographicallySmallestArrayBySwappingElements()
    println(t.lexicographicallySmallestArray(intArrayOf(1, 5, 3, 9, 8), 2).joinToString())
    println(t.lexicographicallySmallestArray(intArrayOf(1, 7, 6, 18, 2, 1), 3).joinToString())
    println(t.lexicographicallySmallestArray(intArrayOf(1, 7, 28, 19, 10), 3).joinToString())
}
