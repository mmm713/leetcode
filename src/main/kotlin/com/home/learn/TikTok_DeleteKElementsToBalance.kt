package com.home.learn

/**
 * Delete k Elements to Balance
 * 来源：https://www.hack2hire.com/question-bank/companies/tiktok/coding-questions/6867207da2d59460201f2cad/practice?questionId=68672293a2d59460201f2cae
 *
 * 给定长度为 n 的整数数组 nums 和整数 k，必须删除恰好 k 个连续元素。
 * 剩余元素保持原来的相对顺序，并从 0 开始重新编号。
 * 如果新数组偶数下标的元素和等于奇数下标的元素和，则称它为平衡数组。
 * 返回能使数组平衡的最小删除起点（原数组下标）；不存在则返回 -1。
 * 删除全部元素后，两边的和均为 0，因此空数组也平衡。
 *
 * 约束：1 <= k <= n <= 100_000；-1_000_000_000 <= nums[i] <= 1_000_000_000。
 *
 * 示例 1：nums = [2, 1, 6, 4], k = 1，返回 1。
 * 删除下标 1 后得到 [2, 6, 4]，偶数下标和为 2 + 4，奇数下标和为 6。
 * 示例 2：nums = [1, 1, 1, 1, 1, 1], k = 2，返回 0。
 * 示例 3：nums = [1, 2, 3], k = 1，返回 -1。
 */
class TikTok_DeleteKElementsToBalance {
    fun findSmallestIndex(nums: IntArray, k: Int): Int {
        val preEvenSum = nums.runningFoldIndexed(0L) { idx, acc, value ->
            if (idx % 2 == 0) acc + value else acc
        }.drop(1)
        val preOddSum = nums.runningFoldIndexed(0L) { idx, acc, value ->
            if (idx % 2 == 1) acc + value else acc
        }.drop(1)
        for(i in k - 1 until nums.size) {
            val preEven = preEvenSum.getOrElse(i - k) { 0L }
            val preOdd = preOddSum.getOrElse(i - k) { 0L }
            var postEven = preEvenSum[nums.size - 1] - preEvenSum[i]
            var postOdd = preOddSum[nums.size - 1] - preOddSum[i]
            if(k % 2 == 1) {
                postEven = postOdd.also { postOdd = postEven }
            }
            if(preEven + postEven == preOdd + postOdd) {
                return i - k + 1
            }
        }
        return -1
    }
}

// 在 IntelliJ 中直接运行此 main。实现 TODO 后，每个用例都会检查实际结果。
fun main() {
    data class Case(val name: String, val nums: IntArray, val k: Int, val expected: Int)

    val cases = listOf(
        Case("题目示例 1", intArrayOf(2, 1, 6, 4), 1, 1),
        Case("题目示例 2：多个答案取最小", intArrayOf(1, 1, 1, 1, 1, 1), 2, 0),
        Case("题目示例 3：无解", intArrayOf(1, 2, 3), 1, -1),
        Case("网站用例：删除三个无解", intArrayOf(3, 7, 2, 8, 1, 6, 4, 5), 3, -1),
        Case("网站用例：删除开头两个", intArrayOf(4, 2, 2, 2, 2, 2), 2, 0),
        Case("网站用例：删除四个无解", intArrayOf(1, 5, 3, 9, 2, 6, 4, 8, 1, 3), 4, -1),
        Case("网站用例：删除中间两个", intArrayOf(1, 3, 2, 2, 3, 1), 2, 2),
        Case("单元素全部删除", intArrayOf(7), 1, 0),
        Case("多元素全部删除", intArrayOf(-3, 0, 8, 2), 4, 0),
        Case("只能删除最后一个", intArrayOf(4, 4, 9), 1, 2),
        Case("只能删除开头一个", intArrayOf(9, 4, 4), 1, 0),
        Case("偶数 k 只能删除末尾", intArrayOf(4, 4, 9, 8), 2, 2),
        Case("奇数 k 删除中间", intArrayOf(5, 9, 8, 7, 5), 3, 1),
        Case("负数", intArrayOf(-2, -1, -6, -4), 1, 1),
        Case("正负数混合", intArrayOf(1, -1, 9, -2), 1, 2),
        Case("全零取最小", intArrayOf(0, 0, 0, 0, 0), 3, 0),
        Case("只剩一个零", intArrayOf(0, 5, 7), 2, 1),
        Case("只剩非零元素无解", intArrayOf(5, 7), 1, -1),
        Case("原数组平衡但删除后无解", intArrayOf(1, 1, 1, 1), 1, -1),
        Case("大数求和", IntArray(9) { 1_000_000_000 }, 1, 0),
        Case("避免整数溢出造成假相等",
            intArrayOf(294_967_296, -1_000_000_000, 1_000_000_000,
                -1_000_000_000, 1_000_000_000, 123), 1, -1),
        Case("最大长度且无解", IntArray(100_000) { 1 }, 1, -1),
        Case("最大长度且多个解", IntArray(100_000) { 1_000_000_000 }, 2, 0)
    )

    val solution = TikTok_DeleteKElementsToBalance()
    var passed = 0
    for ((name, nums, k, expected) in cases) {
        val actual = solution.findSmallestIndex(nums.copyOf(), k)
        val success = actual == expected
        if (success) passed++
        println("${if (success) "PASS" else "FAIL"} | $name | expected=$expected, actual=$actual")
    }
    check(passed == cases.size) { "$passed/${cases.size} tests passed" }
    println("All ${cases.size} tests passed!")
}
