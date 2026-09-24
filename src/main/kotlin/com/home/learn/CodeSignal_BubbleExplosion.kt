package com.home.learn

/**
 * Bubble Explosion — 气泡爆炸（根据题目截图整理）
 *
 * bubbles 是一个矩形棋盘，每个正整数代表气泡的颜色，初始没有空格。
 * 相邻仅指上、下、左、右共边的格子，不包括对角线。
 *
 * 执行一轮爆炸：
 * 1. 在初始棋盘上，若一个气泡至少有 2 个同色的相邻气泡，它就满足爆炸条件。
 * 2. 标记所有满足条件的气泡，以及与它们相邻的同色气泡。
 * 3. 所有被标记的气泡同时消失，留下空格。
 * 4. 剩下的气泡在各自列中向下掉落，保持原有上下顺序，顶部空格填 0。
 * 返回大小不变的二维数组。下落后不再触发第二轮爆炸。
 *
 * 截图示例：
 * 输入              同时消除后         下落后（返回值）
 * 3 1 2 1           3 0 2 1           0 0 0 1
 * 1 1 1 4           0 0 0 4           0 0 0 4
 * 3 1 2 2           0 0 2 2           0 0 2 2
 * 3 3 3 4           0 0 0 4           3 0 2 4
 *
 * 约束：
 * 1 <= bubbles.size <= 100
 * 1 <= bubbles[0].size <= 100
 * 1 <= bubbles[i][j] <= 10_000
 * 题目允许时间复杂度不超过 O(rows^2 * cols^2)。
 */
class CodeSignal_BubbleExplosion {
    private val DIRS = arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

    fun solution(bubbles: Array<IntArray>): Array<IntArray> {
        if(bubbles.isEmpty()) return emptyArray()
        val marked = Array(bubbles.size) { BooleanArray(bubbles[0].size) }
        for (i in bubbles.indices) {
            for (j in bubbles[0].indices) {
                if(shouldExplode(bubbles, i, j)) {
                    marked[i][j] = true
                    for((x, y) in DIRS) {
                        if(i + x >= bubbles.size ||
                            i + x < 0 ||
                            j + y >= bubbles[0].size ||
                            j + y < 0
                        ) continue
                        if (marked[i][j] && bubbles[i + x][j + y] == bubbles[i][j]) {
                            marked[i + x][j + y] = true
                        }
                    }
                }
            }
        }
        for (i in bubbles.indices) {
            for (j in bubbles[0].indices) {
                bubbles[i][j] = if(marked[i][j]) 0 else bubbles[i][j]
            }
        }
        for (j in bubbles[0].indices) {
            dropBubbles(bubbles, j)
        }
        return bubbles
    }

    private fun shouldExplode(bubbles: Array<IntArray>, i: Int, j: Int): Boolean {
        var counter = 0
        for((x, y) in DIRS) {
            if(i + x >= bubbles.size ||
                i + x < 0 ||
                j + y >= bubbles[0].size ||
                j + y < 0
                ) continue
            if (bubbles[i + x][j + y] == bubbles[i][j]) {
                counter++
            }
        }
        return counter >= 2
    }

    private fun dropBubbles(bubbles: Array<IntArray>, j: Int) {
        var zero = -1
        for(i in bubbles.size - 1 downTo 0) {
            if(bubbles[i][j] != 0 && zero != -1) {
                bubbles[zero--][j] = bubbles[i][j]
                bubbles[i][j] = 0
            } else if(bubbles[i][j] == 0 && zero == -1) {
                zero = i
            }
        }
    }
}

fun main() {
    val t = CodeSignal_BubbleExplosion()
    val bubbles = arrayOf(
        intArrayOf(3, 1, 2, 1),
        intArrayOf(1, 1, 1, 4),
        intArrayOf(3, 1, 2, 2),
        intArrayOf(3, 3, 3, 4)
    )
    val expected = arrayOf(
        intArrayOf(0, 0, 0, 1),
        intArrayOf(0, 0, 0, 4),
        intArrayOf(0, 0, 2, 2),
        intArrayOf(3, 0, 2, 4)
    )

    val actual = t.solution(bubbles)
    println("Actual:")
    actual.forEach { println(it.contentToString()) }
    println("Expected:")
    expected.forEach { println(it.contentToString()) }
    println("Matches: ${actual.contentDeepEquals(expected)}")
}
