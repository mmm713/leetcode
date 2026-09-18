package com.home.learn

class Easy_796_RotateString {

    fun rotateString(s: String, goal: String): Boolean {
        val dg = goal + goal
        return s.length == goal.length && s in dg
    }
}

fun main() {
    val t = Easy_796_RotateString()
    println(t.rotateString("abcde", "cdeab")) // true
    println(t.rotateString("abcde", "abced")) // false
}
