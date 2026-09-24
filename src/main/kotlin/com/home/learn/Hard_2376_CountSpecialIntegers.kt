package com.home.learn

class Hard_2376_CountSpecialIntegers {

    fun countSpecialNumbers(n: Int): Int {
        val ns = n.toString()
        val mem = Array(ns.length) { IntArray(1 shl 10) { -1 } }
        return dfs(0, 0, true, false, ns, mem)
    }

    private fun dfs(i: Int, mask :Int, isLimit :Boolean, isNum :Boolean, ns :String, mem : Array<IntArray>) : Int {
        if(i == ns.length) {
            return if (isNum) 1 else 0
        }
        if(!isLimit && isNum && mem[i][mask] != -1) {
            return mem[i][mask] //之前计算过
        }
        var res = 0
        if (!isNum) { // 可以跳过当前数位
            res = dfs(i + 1, mask, isLimit = false, isNum = false, ns, mem = mem)
        }
        // 如果前面填的数字都和 n 的一样，那么这一位至多填数字 s[i]（否则就超过 n 啦）
        val up = if (isLimit) ns[i] - '0' else 9;
        // 枚举要填入的数字 d
        // 如果前面没有填数字，则必须从 1 开始（因为不能有前导零）
        var d = if (isNum) 0 else 1
        while(d <= up) {
            if(((mask shr d) and 1) == 0) {
                res += dfs(i + 1, mask or (1 shl d), isLimit && d == up, true, ns, mem = mem)
            }
            d++
        }
        if (!isLimit && isNum) {
            mem[i][mask] = res
        }
        return res
    }
}

fun main() {
    val t = Hard_2376_CountSpecialIntegers()
    println(t.countSpecialNumbers(20)) // 19
    println(t.countSpecialNumbers(5)) // 5
    println(t.countSpecialNumbers(135)) // 110
}
