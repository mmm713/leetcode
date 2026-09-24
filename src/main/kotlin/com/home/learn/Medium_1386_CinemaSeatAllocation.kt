package com.home.learn

class Medium_1386_CinemaSeatAllocation {

    fun maxNumberOfFamilies(n: Int, reservedSeats: Array<IntArray>): Int {
        val masks = reservedSeats
            .filter { it[1] in 2..9 }
            .groupBy({ it[0] }, { it[1] })
            .map { (_, seats) ->
                seats.fold(0) { mask, seat ->
                    mask or (1 shl (seat - 1))
                }
            }
        val reservedRowFamilies = masks.sumOf { mask ->
            val leftFree = (mask and 0b0000011110) == 0
            val middleFree = (mask and 0b0001111000) == 0
            val rightFree = (mask and 0b0111100000) == 0

            val count: Int = when {
                leftFree && rightFree -> 2
                leftFree || middleFree || rightFree -> 1
                else -> 0
            }
            count
        }
        return (n - masks.size) * 2 + reservedRowFamilies
    }
}

fun main() {
    val t = Medium_1386_CinemaSeatAllocation()
    println(t.maxNumberOfFamilies(3, arrayOf(
        intArrayOf(1, 2),
        intArrayOf(1, 3),
        intArrayOf(1, 8),
        intArrayOf(2, 6),
        intArrayOf(3, 1),
        intArrayOf(3, 10)
    ))) // 4
    println(t.maxNumberOfFamilies(2, arrayOf(
        intArrayOf(2, 1),
        intArrayOf(1, 8),
        intArrayOf(2, 6)
    ))) // 2
    println(t.maxNumberOfFamilies(4, arrayOf(
        intArrayOf(4, 3),
        intArrayOf(1, 4),
        intArrayOf(4, 6),
        intArrayOf(1, 7)
    ))) // 4
}
