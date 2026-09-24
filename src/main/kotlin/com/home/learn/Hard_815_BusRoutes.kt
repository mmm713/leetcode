package com.home.learn

import java.util.PriorityQueue

class Hard_815_BusRoutes {
    fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
        if(source == target) return 0
        val uMap = HashMap<Int, MutableSet<Int>>() //union之间的联通关系
        val sMap = HashMap<Int, MutableSet<Int>>() //stop属于哪些union
        for((i, r) in routes.withIndex()) {
            //每次都是第一次见到
            uMap[i] = mutableSetOf()
            for(s in r) {
                sMap.getOrPut(s) { mutableSetOf() }.apply {
                    for (b in this) {
                        uMap[b]?.add(i)
                        uMap[i]?.add(b)
                    }
                    add(i)
                }
            }
        }
        //push start, every edge weight 1
        val dis = IntArray(routes.size) { Int.MAX_VALUE }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second }).apply {
            sMap[source]?.forEach { route ->
                offer(route to 1)
                dis[route] = 1
            }
        }
        if(sMap[target]?.find{ dis[it] == 1 } != null) {
            return 1
        }
        while (pq.isNotEmpty()) {
            val curr = pq.poll()
            uMap[curr.first]?.filter {
                dis[it] >= curr.second + 1
            }?.forEach {
                if(sMap[target]?.contains(it) == true) {
                    return curr.second + 1
                }
                pq.offer(it to curr.second + 1)
                dis[it] = curr.second + 1
            }
        }
        return -1
    }
}

fun main() {
    val t = Hard_815_BusRoutes()
    println(t.numBusesToDestination(
        arrayOf(
            intArrayOf(1, 2, 7),
            intArrayOf(3, 6, 7)
        ),
        1,
        6
    )) // 2
    println(t.numBusesToDestination(
        arrayOf(
            intArrayOf(2),
            intArrayOf(2, 8)
        ),
        2,
        8
    )) // 1
    println(t.numBusesToDestination(
        arrayOf(
            intArrayOf(7, 12),
            intArrayOf(4, 5, 15),
            intArrayOf(6),
            intArrayOf(15, 19),
            intArrayOf(9, 12, 13)
        ),
        15,
        12
    )) // -1
}
