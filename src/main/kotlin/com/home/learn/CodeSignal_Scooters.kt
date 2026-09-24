package com.home.learn

/**
 * Scooters — 滑板车骑行距离
 *
 * 从位置 0 出发，向右到达 finish。
 * scooters 中的每个数表示一辆滑板车的位置，位置互不相同，数组不保证有序。
 * 每辆滑板车最多向右行驶 10 个单位距离。
 *
 * 行进规则：
 * 1. 使用当前位置的车；如果没有，就步行到前方最近的车。
 * 2. 骑到电量耗尽或到达 finish，途中经过其他车时不换车。
 * 3. 没到终点就重复以上步骤；前方没有可用的车就步行到终点。
 * 返回骑滑板车的总距离，步行距离不计入。
 *
 * 示例：finish = 23, scooters = [7, 4, 14]，返回 19。
 * 步行 0 -> 4，骑行 4 -> 14（10），换车骑行 14 -> 23（9）。
 *
 * 根据公开整理版及示例转述，非官方原文。
 */
class CodeSignal_Scooters {
    fun solution(finish: Int, scooters: IntArray): Int {
        val hash = BooleanArray(finish)
        scooters.forEach {
            if (it in 0 until finish) hash[it] = true
        }
        var res = 0
        var pos = 0
        while (pos < finish) {
            if (!hash[pos]) {
                pos++
            } else {
                pos += 10
                res += 10
            }
        }
        return res - (pos - finish)
    }
}

fun main() {
    val t = CodeSignal_Scooters()
    println(t.solution(23, intArrayOf(7, 4, 14))) // 19
    println(t.solution(27, intArrayOf(15, 7, 3, 10))) // 20
    println(t.solution(10, intArrayOf())) // 0
}
