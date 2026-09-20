package com.home.learn

import kotlin.math.max
import kotlin.math.min

class Medium_1401_CircleAndRectangleOverlapping {

    fun checkOverlap(
        radius: Int, xCenter: Int, yCenter: Int,
        x1: Int, y1: Int, x2: Int, y2: Int
    ): Boolean {
        val x = max(x1, min(xCenter, x2))
        val y = max(y1, min(yCenter, y2))
        return (x - xCenter) * (x - xCenter) + (y - yCenter) * (y - yCenter) <= radius * radius
    }
}

fun main() {
    val t = Medium_1401_CircleAndRectangleOverlapping()
    println(t.checkOverlap(1, 0, 0, 1, -1, 3, 1)) // true
    println(t.checkOverlap(1, 1, 1, 1, -3, 2, -1)) // false
    println(t.checkOverlap(1, 0, 0, -1, 0, 0, 1)) // true
}
