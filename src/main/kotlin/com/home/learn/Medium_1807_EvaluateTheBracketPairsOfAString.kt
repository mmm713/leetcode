package com.home.learn

class Medium_1807_EvaluateTheBracketPairsOfAString {
    fun evaluate(s: String, knowledge: List<List<String>>): String {
        val k = knowledge.associate { it[0] to it[1] }
        val tmp = StringBuilder()
        val res = StringBuilder()
        var state = 0
        for ((i, c) in s.withIndex()) {
            if(c == '(') {
                state = 1
                continue
            } else if (c == ')') {
                res.append(k.getOrDefault(tmp.toString(), '?'))
                tmp.clear()
                state = 0
                continue
            } else when(state) {
                0 -> res.append(c)
                1 -> tmp.append(c)
            }
        }
        return res.toString()
    }
}

fun main() {
    val t = Medium_1807_EvaluateTheBracketPairsOfAString()
    println(t.evaluate(
        "(name)is(age)yearsold",
        listOf(listOf("name", "bob"), listOf("age", "two"))
    )) // bobistwoyearsold
    println(t.evaluate(
        "hi(name)",
        listOf(listOf("a", "b"))
    )) // hi?
    println(t.evaluate(
        "(a)(a)(a)aaa",
        listOf(listOf("a", "yes"))
    )) // yesyesyesaaa
}
