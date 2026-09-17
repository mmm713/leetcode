package com.home.learn

class Hard_3734_LexicographicallySmallestPalindromicPermutationGreaterThanTarget {

    fun lexPalindromicPermutation(s: String, target: String): String {
        val left = IntArray(26)
        s.forEach { left[it - 'a']++ }
        var mid = ""
        for(i in 0 until 26) {
            val c = left[i]
            if(c % 2 == 0)
                continue
            //奇数字母只能有一个
            if(mid.isNotBlank())
                return ""
            // 记录填在正中间的字母
            mid = "${'a'+i}"
            left[i]--
        }
        val n = s.length
        // 先假设答案左半与 target 的左半（不含正中间）相同
        for(i in 0 until n / 2)
            left[target[i] - 'a'] -= 2
        if(valid(left)) {
            // 特殊情况：把 target 左半翻转到右半，能否比 target 大？
            val l = target.substring(0, n/2)
            val r = mid + l.reversed()
            if (r > target.substring(n/2, n))
                return l + r
        }

        for(i in n/2-1 downTo 0) {
            val b = target[i] - 'a'
            left[b] += 2 // 撤销消耗
            if (!valid(left)) // [0,i-1] 无法做到全部一样
                continue

            // 把 target[i] 增大到 j
            for (j in (b+1) until 26) {
                if (left[j] == 0)
                    continue

                // 找到答案（下面的循环在整个算法中只会跑一次）
                left[j] -= 2
                var l = target.substring(0, i) + ('a' + j)
                l += ((0 until 26).joinToString(""){('a' + it).toString().repeat(left[it]/2)})
                val r = l.reversed()
                return l + mid + r
            }
        }
        return ""
    }

    fun valid(l: IntArray): Boolean {
        return l.find { it < 0 } == null
    }
}

fun main() {
    val t = Hard_3734_LexicographicallySmallestPalindromicPermutationGreaterThanTarget()
    println(t.lexPalindromicPermutation("baba", "abba")) // baab
    println(t.lexPalindromicPermutation("baba", "bbaa")) // ""
    println(t.lexPalindromicPermutation("abc", "abb"))   // ""
    println(t.lexPalindromicPermutation("aac", "abb"))   // aca
}
