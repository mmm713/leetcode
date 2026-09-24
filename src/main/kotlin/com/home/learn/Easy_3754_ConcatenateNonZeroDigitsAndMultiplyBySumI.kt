package com.home.learn

class Easy_3754_ConcatenateNonZeroDigitsAndMultiplyBySumI {

    fun sumAndMultiply(n: Int): Long {
        var nn = n
        var mul = 1
        var sum = 0L
        var num = 0L
        while (nn != 0) {
            val cur = nn % 10
            if (cur > 0) {
                sum += cur
                num += cur * mul
                mul *= 10
            }
            nn /= 10
        }
        return num * sum
    }
}

fun main() {
    val t = Easy_3754_ConcatenateNonZeroDigitsAndMultiplyBySumI()
    println(t.sumAndMultiply(10203004)) // 12340
    println(t.sumAndMultiply(1000)) // 1
}
