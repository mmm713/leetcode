package com.home.learn

class Hard_1621_NumberOfSetsOfKNonOverlappingLineSegments {
    private companion object {
        const val MOD = 1_000_000_007L
    }

    fun numberOfSets(n: Int, k: Int): Int {
        return combination(n + k - 1, 2 * k)
    }

    private fun combination(n: Int, r: Int): Int {
        if (r !in 0..n) return 0

        val factorial = LongArray(n + 1)
        factorial[0] = 1

        for (i in 1..n) {
            factorial[i] = factorial[i - 1] * i % MOD
        }

        val denominator = factorial[r] * factorial[n - r] % MOD
        return (factorial[n] * modPow(denominator, MOD - 2) % MOD).toInt()
    }

    private fun modPow(value: Long, exponent: Long): Long {
        var base = value
        var exp = exponent
        var result = 1L

        while (exp > 0) {
            if (exp % 2L == 1L) {
                result = result * base % MOD
            }
            base = base * base % MOD
            exp /= 2
        }
        return result
    }
}

fun main() {
    val t = Hard_1621_NumberOfSetsOfKNonOverlappingLineSegments()
    println(t.numberOfSets(4, 2)) // 5
    println(t.numberOfSets(3, 1)) // 3
    println(t.numberOfSets(30, 7)) // 796297179
}
