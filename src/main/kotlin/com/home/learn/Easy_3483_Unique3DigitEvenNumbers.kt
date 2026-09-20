package com.home.learn

class Easy_3483_Unique3DigitEvenNumbers {

    fun totalNumbers(digits: IntArray): Int {
        val cnt = IntArray(10)
        for (digit in digits) {
            cnt[digit]++
        }
        val kinds = cnt.count { it > 0 }
        val nonZeros = (1..9).count { cnt[it] > 0 }
        val singles = (1..9).count { cnt[it] == 1 }
        var ans = 0
        // 枚举个位填偶数 d
        for(d in 0 until 10 step 2) {
            if(cnt[d] == 0)
                continue
            // 十位填任意数字
            var k = kinds
            if (cnt[d] == 1) {
                k--;
            }
            // 百位填任意非零数字
            var nz = nonZeros
            if(d > 0 && cnt[d] == 1) {
                nz--
            }

            // 恰好出现一次的非零数字，不能同时填入十位和百位
            var s = singles
            if(d > 0) {
                if (cnt[d] == 1) {
                    s--
                } else if (cnt[d] == 2) {
                    s++ // 个位数填入 d 后，d 恰好出现一次
                }
            }
            ans += k * nz - s
        }
        return ans
    }
}

fun main() {
    val t = Easy_3483_Unique3DigitEvenNumbers()
    println(t.totalNumbers(intArrayOf(1, 2, 3, 4))) // 12
    println(t.totalNumbers(intArrayOf(0, 2, 2))) // 2
    println(t.totalNumbers(intArrayOf(6, 6, 6))) // 1
    println(t.totalNumbers(intArrayOf(1, 3, 5))) // 0
}
