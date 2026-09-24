package com.home.learn

class Easy_3345_SmallestDivisibleDigitProductI {

    fun smallestNumber(n: Int, t: Int): Int {
        var i = n
        while(true) {
            var p = 1
            var x = i
            while(x > 0) {
                p *= x % 10
                x /= 10
            }
            if(p % t == 0) return i
            i++
        }
    }
}

fun main() {
    val solution = Easy_3345_SmallestDivisibleDigitProductI()
    println(solution.smallestNumber(10, 2)) // 10
    println(solution.smallestNumber(15, 3)) // 16
}
