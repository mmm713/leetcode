package com.home.learn

class Easy_3498_ReverseDegreeOfAString {

    fun reverseDegree(s: String): Int {
        return s.indices.sumOf { i -> (26 - (s[i] - 'a')) * (i + 1) }
    }
}

fun main() {
    val t = Easy_3498_ReverseDegreeOfAString()
    println(t.reverseDegree("abc")) // 148
    println(t.reverseDegree("zaza")) // 160
}
