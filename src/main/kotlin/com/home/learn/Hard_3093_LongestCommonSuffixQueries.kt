package com.home.learn

/**
 * 3093. 最长公共后缀查询
 * https://leetcode.com/problems/longest-common-suffix-queries/
 *
 * 给定字符串数组 wordsContainer 和 wordsQuery。
 * 对每个查询，在 wordsContainer 中选出与它公共后缀最长的字符串。
 * 如果多个字符串的公共后缀长度相同，选择字符串长度最短的；
 * 如果长度仍相同，选择在 wordsContainer 中下标最小的。
 * 返回每个查询所选字符串的下标（从 0 开始）。公共后缀可以为空。
 *
 * 约束：
 * - 两个数组的长度分别在 1..10_000 之间。
 * - 每个字符串长度在 1..5_000 之间，仅包含小写英文字母。
 * - 每个数组中所有字符串的总长度分别不超过 500_000。
 */
class Hard_3093_LongestCommonSuffixQueries {
    class TrieNode (
        val children: Array<TrieNode?> = arrayOfNulls<TrieNode>(26),
        val smallestIdx: IntArray = IntArray(26)
    )
    fun stringIndices(wordsContainer: Array<String>, wordsQuery: Array<String>): IntArray {
        val trie = TrieNode() // dummy head
        val default = wordsContainer.indices.minBy { wordsContainer[it].length }
        for (i in wordsContainer.indices) {
            var cur = trie
            for (j in wordsContainer[i].lastIndex downTo 0) {
                val index = wordsContainer[i][j] - 'a'
                if (cur.children[index] == null ||
                    wordsContainer[i].length < wordsContainer[cur.smallestIdx[index]].length
                ) {
                    cur.smallestIdx[index] = i
                }
                cur = cur.children[index] ?: TrieNode().also { cur.children[index] = it }
            }
        }
        val res = IntArray(wordsQuery.size) { default }
        for(i in wordsQuery.indices) {
            res[i] = query(trie, wordsQuery[i]).takeIf { it != -1 } ?: res[i]
        }
        return res
    }

    fun query(trie: TrieNode, word: String): Int {
        var res = -1
        var cur = trie
        for(i in word.lastIndex downTo 0) {
            if(cur.children[word[i] - 'a'] != null) {
                res = cur.smallestIdx[word[i] - 'a']
                cur = cur.children[word[i] - 'a']!!
            } else {
                return res
            }
        }
        return res
    }
}

fun main() {
    val sol = Hard_3093_LongestCommonSuffixQueries()

    println(sol.stringIndices(
        wordsContainer = arrayOf("abcd", "bcd", "xbcd"),
        wordsQuery = arrayOf("cd", "bcd", "xyz")
    ).contentToString()) // [1, 1, 1]

    println(sol.stringIndices(
        wordsContainer = arrayOf("abcdefgh", "poiuygh", "ghghgh"),
        wordsQuery = arrayOf("gh", "acbfgh", "acbfegh")
    ).contentToString()) // [2, 0, 2]

    println(sol.stringIndices(
        wordsContainer = arrayOf("cat", "bat", "a"),
        wordsQuery = arrayOf("at", "xyz", "a")
    ).contentToString()) // [0, 2, 2]
}
