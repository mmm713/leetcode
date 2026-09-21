package com.home.learn

import kotlin.math.max

class Easy_3870_CountCommasInRange {

    //根据题目约束n≤100000，所以不会出现第二个逗号。
    fun countCommas(n: Int): Int {
        return max(n - 999, 0)
    }
}

fun main() {
    val t = Easy_3870_CountCommasInRange()
    println(t.countCommas(1002)) // 3
    println(t.countCommas(998)) // 0
}
