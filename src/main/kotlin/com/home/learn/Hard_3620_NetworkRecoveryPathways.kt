package com.home.learn

import java.util.PriorityQueue
import kotlin.math.min

class Hard_3620_NetworkRecoveryPathways {
    data class Network (
        var edges: Array<Pair<Int, Int>>
    )
    fun findMaxPathScore(edges: Array<IntArray>, online: BooleanArray, k: Long): Int {
        val n = online.size
        val grouped = edges.filter { online[it[0]] && online[it[1]] }.groupBy { it[0] }
        val costs = grouped.values.flatten().map { it[2] }
        var low = costs.minOrNull() ?: return -1
        var high = costs.maxOrNull() ?: return -1
        val e = Array(n) { node ->
            val outgoing = grouped[node].orEmpty()
            Network(
                edges = outgoing.map { Pair(it[1], it[2]) }.toTypedArray()
            )
        }
        if(!canReach(low, e, k)) {
            return -1
        }
        while (low <= high) {
            val mid = low + (high - low) / 2
            if (canReach(mid, e, k)) { // 可行性检查
                low = mid + 1
            } else {
                high = mid - 1
            }
        }
        return high
    }

    private fun canReach(mid: Int, e: Array<Network>, k: Long): Boolean {
        //迪杰斯特拉，pair 节点idx，当前路径cost
        val firstEdges = e[0].edges.filter { it.second >= mid }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second }).apply {
            addAll(firstEdges)
        }
        //记忆数组最小cost
        val mem = IntArray(e.size) { Int.MAX_VALUE }
        firstEdges.forEach { (to, cost) ->
            mem[to] = cost
        }
        while(pq.isNotEmpty()) {
            val cur = pq.poll()
            if(cur.second > k) {
                return false
            }
            if(cur.first == e.size - 1) {
                return true
            }
            e[cur.first].edges.filter { it.second >= mid && (mem[cur.first] + it.second < mem[it.first])}.forEach {
                mem[it.first] = mem[cur.first] + it.second
                pq.offer(Pair(it.first, mem[cur.first] + it.second))
            }
        }
        return false
    }
}

fun main() {
    val t = Hard_3620_NetworkRecoveryPathways()
    println(t.findMaxPathScore(
        arrayOf(
            intArrayOf(0, 2, 70),
            intArrayOf(1, 2, 27),
            intArrayOf(0, 1, 32)
        ),
        booleanArrayOf(true, true, true),
        118L
    )) // 70
    println(t.findMaxPathScore(
        arrayOf(
            intArrayOf(0, 1, 5),
            intArrayOf(1, 3, 10),
            intArrayOf(0, 2, 3),
            intArrayOf(2, 3, 4)
        ),
        booleanArrayOf(true, true, true, true),
        10L
    )) // 3
    println(t.findMaxPathScore(
        arrayOf(
            intArrayOf(0, 1, 7),
            intArrayOf(1, 4, 5),
            intArrayOf(0, 2, 6),
            intArrayOf(2, 3, 6),
            intArrayOf(3, 4, 2),
            intArrayOf(2, 4, 6)
        ),
        booleanArrayOf(true, true, true, false, true),
        12L
    )) // 6
}
