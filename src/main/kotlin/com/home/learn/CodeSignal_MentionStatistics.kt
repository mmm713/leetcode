package com.home.learn

/**
 * 第 3 题：群聊成员被提及的消息数（根据截图整理，标题自拟）。
 *
 * members 是成员 ID 数组，messages 是聊天消息数组。
 * ID 格式为 "id" 加上 1..999 的正整数，例如 id1、id123。
 * mention 以 @ 开头，其后是一个或多个以逗号分隔的 ID，例如 @id1,id123。
 * 合法 mention 前后是空格，或位于消息开头、结尾；其中使用的 ID 格式合法。
 * 普通文本中也可能有 @，但普通文本中的 @ 不作为单词的首字符，例如 ex@mple。
 *
 * 统计每个成员在多少条消息里被提及：
 * - 同一成员在同一条消息里被提及多次，只计一次。
 * - 忽略不在 members 中的 ID。
 * - 返回所有成员，包括计数为 0 的成员。
 * - 每项格式为 "用户ID=次数"，例如 "id123=2"。
 * - 按次数降序排列；次数相同时按 ID 字符串的字典序升序排列，不按数字大小排序。
 *
 * 截图示例 members = ["id123", "id234", "id7", "id321"]。
 * 返回 ["id123=2", "id321=2", "id7=1", "id234=0"]。
 * 原始消息的右侧被截断，main 中使用按截图解释重建的等价示例，不是完整原文。
 * 具体数据范围和完整复杂度提示未拍到。
 */
class CodeSignal_MentionStatistics {
    fun solution(members: Array<String>, messages: Array<String>): Array<String> {
        TODO("在这里实现")
    }
}

fun main() {
    val t = CodeSignal_MentionStatistics()
    val members = arrayOf("id123", "id234", "id7", "id321")
    // 重建示例：保留截图解释中的重复提及、非成员和普通文本 @ 等情况。
    val messages = arrayOf(
        "Hey @id123,id321 review this PR please! @id123 thanks",
        "Hey @id7 nice appro@ch! Great job! @id800 thanks ex@mple",
        "@id123,id321 thx!"
    )
    val expected = arrayOf("id123=2", "id321=2", "id7=1", "id234=0")
    val actual = t.solution(members, messages)
    println("Actual: ${actual.contentToString()}")
    println("Expected: ${expected.contentToString()}")
    println("Matches: ${actual.contentEquals(expected)}")
}
