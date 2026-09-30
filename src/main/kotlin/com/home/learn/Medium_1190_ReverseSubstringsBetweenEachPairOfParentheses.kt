package com.home.learn

class Medium_1190_ReverseSubstringsBetweenEachPairOfParentheses {
    fun reverseParentheses(s: String): String {
        var i = 0
        val sb = StringBuilder()
        while(i < s.length) {
            if(s[i] == '(') {
                val r = reverse(s, i + 1)
                sb.append(r.first)
                i = r.second + 1
            } else {
                sb.append(s[i++])
            }
        }
        return sb.toString()
    }

    private fun reverse(s: String, start :Int): Pair<String, Int> {
        var i = start
        val sb = StringBuilder()
        while (s[i] != ')') {
            if (s[i] == '(') {
                val r = reverse(s, i + 1)
                sb.append(r.first)
                i = r.second
            } else {
                sb.append(s[i])
            }
            i++
        }
        return sb.reverse().toString() to i
    }
}

fun main() {
    val t = Medium_1190_ReverseSubstringsBetweenEachPairOfParentheses()
    println(t.reverseParentheses("(abcd)")) // dcba
    println(t.reverseParentheses("(u(love)i)")) // iloveu
    println(t.reverseParentheses("(ed(et(oc))el)")) // leetcode
}
