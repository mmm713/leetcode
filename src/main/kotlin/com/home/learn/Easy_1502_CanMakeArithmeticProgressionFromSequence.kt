package com.home.learn

class Easy_1502_CanMakeArithmeticProgressionFromSequence {
    fun canMakeArithmeticProgression(arr: IntArray): Boolean {
        if (arr.size < 2) return true
        val max = arr.max()
        val min = arr.min()
        if(max == min) {
            return true
        }
        val n = arr.size
        if((max - min) % (n - 1) != 0) {
            return false
        }
        val d = (max - min) / (n - 1)
        val visited = BooleanArray(n)
        //min + k * d每个k只能出现一次
        for(a in arr) {
            val k = (a - min) / d
            if((a - min) % d != 0 || visited[k]) {
                return false
            }
            visited[k] = true
        }
        return true
    }
}

fun main() {
    val t = Easy_1502_CanMakeArithmeticProgressionFromSequence()
    println(t.canMakeArithmeticProgression(intArrayOf(3, 5, 1))) // true
    println(t.canMakeArithmeticProgression(intArrayOf(1, 2, 4))) // false
}
