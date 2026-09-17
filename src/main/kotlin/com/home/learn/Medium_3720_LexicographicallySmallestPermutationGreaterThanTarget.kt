package com.home.learn

class Medium_3720_LexicographicallySmallestPermutationGreaterThanTarget {
    fun lexGreaterPermutation(s: String, target: String): String {
        val t = target.toCharArray()
        val n = t.size
        val left = IntArray(26)
        s.forEach { left[it - 'a']++ }
        target.forEach { left[it - 'a']-- }
        next@ for (i in n -1 downTo 0) {
            val b = t[i] - 'a'
            left[b]++ //撤销消耗
            for (c in left) {
                if(c < 0) {// [0,i-1] 无法做到全部一样
                    continue@next
                }
            }
            for (j in b + 1 until 26) {
                if(left[j] == 0)
                    continue
                left[j]--
                val prefix = target.substring(0, i) + ('a' + j)
                val suffix = (0 until 26).joinToString("") { ('a' + it).toString().repeat(left[it]) }
                return prefix + suffix
            }
        }
        return ""
    }
}

fun main() {
    val sol = Medium_3720_LexicographicallySmallestPermutationGreaterThanTarget()
    sol.lexGreaterPermutation(s = "abc", target = "bba")
}