package com.home.learn

import java.util.PriorityQueue

class Easy_1086_HighFive {

    fun highFive(items: Array<IntArray>): Array<IntArray> {
        val mem = mutableMapOf<Int, PriorityQueue<Int>>()
        items.forEach {
            val heap = mem.getOrPut(it[0]) { PriorityQueue() }
            heap.offer(it[1])
            if (heap.size > 5) {
                heap.poll()
            }
        }
        return mem.toList()
            .sortedBy{ it.first }
            .map { (id, heap) -> intArrayOf(id, heap.sum() / 5)}
            .toTypedArray()
    }
}

fun main() {
    val t = Easy_1086_HighFive()
    println(t.highFive(arrayOf(
        intArrayOf(1, 91), intArrayOf(1, 92), intArrayOf(2, 93),
        intArrayOf(2, 97), intArrayOf(1, 60), intArrayOf(2, 77),
        intArrayOf(1, 65), intArrayOf(1, 87), intArrayOf(1, 100),
        intArrayOf(2, 100), intArrayOf(2, 76)
    )).contentDeepToString()) // [[1, 87], [2, 88]]
    println(t.highFive(arrayOf(
        intArrayOf(1, 100), intArrayOf(7, 100), intArrayOf(1, 100),
        intArrayOf(7, 100), intArrayOf(1, 100), intArrayOf(7, 100),
        intArrayOf(1, 100), intArrayOf(7, 100), intArrayOf(1, 100),
        intArrayOf(7, 100)
    )).contentDeepToString()) // [[1, 100], [7, 100]]
}
