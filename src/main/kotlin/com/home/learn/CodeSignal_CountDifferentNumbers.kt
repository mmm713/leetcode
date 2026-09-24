package com.home.learn

/**
 * 第 1 题：统计不等于前两个元素的数字个数（根据截图整理，标题自拟）。
 *
 * 给定整数数组 numbers，统计既不等于 numbers[0]，也不等于 numbers[1] 的元素个数。
 * 若对应位置不存在，就不使用该位置的值作为排除条件。
 * 重复元素按出现次数计算，不去重。数组下标从 0 开始。
 *
 * 示例：
 * [4, 3, 2, 3, 2, 5, 4, 3] -> 3（剩下 2、2、5）
 * [3, 3, 1, 1, 3] -> 2
 * [-2] -> 0
 *
 * 截图说明 O(numbers.size^2) 的实现可以通过；具体数据范围未拍到。
 */
class CodeSignal_CountDifferentNumbers {
    fun solution(numbers: IntArray): Int {
        if (numbers.size <= 1) {
            return 0
        }
        if (numbers.size == 2) {
            return if(numbers[0] == numbers[1]) 0 else 1
        }
        var res = 0
        for (i in 2 until numbers.size) {
            res += if(numbers[i] != numbers[0] && numbers[i] != numbers[1]) 1 else 0
        }
        return res
    }
}

fun main() {
    val t = CodeSignal_CountDifferentNumbers()
    println(t.solution(intArrayOf(4, 3, 2, 3, 2, 5, 4, 3))) // 3
    println(t.solution(intArrayOf(3, 3, 1, 1, 3))) // 2
    println(t.solution(intArrayOf(-2))) // 0
}
