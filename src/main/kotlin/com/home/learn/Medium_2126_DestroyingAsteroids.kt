package com.home.learn

class Medium_2126_DestroyingAsteroids {
    fun asteroidsDestroyed(mass: Int, asteroids: IntArray): Boolean {
        asteroids.sort()
        var currentMass = mass.toLong()
        for (asteroid in asteroids) {
            if (currentMass < asteroid) {
                return false
            }
            currentMass += asteroid
        }
        return true
    }
}

fun main() {
    val t = Medium_2126_DestroyingAsteroids()
    println(t.asteroidsDestroyed(10, intArrayOf(3, 9, 19, 5, 21))) // true
    println(t.asteroidsDestroyed(5, intArrayOf(4, 9, 23, 4))) // false
    println(t.asteroidsDestroyed(1, intArrayOf(1))) // true
    println(t.asteroidsDestroyed(1, intArrayOf(2))) // false
    println(t.asteroidsDestroyed(100000, IntArray(100000) { 100000 })) // true
}
