package com.home.learn

/**
 * 第 2 题：找出文件数量最多的 bucket（根据截图整理，标题自拟）。
 *
 * 按顺序处理 commands 中的命令：
 * - "goto <bucket_name>"：切换到指定 bucket，保证该 bucket 存在。
 * - "create <filename>"：在当前 bucket 创建文件；若同名文件已存在，则不做任何事。
 * 不同 bucket 可以保存同名文件，切换 bucket 不会清除已有文件。
 * 执行完所有命令后，返回文件数量最多的 bucket 名称。
 *
 * 保证第一条命令是 goto，至少有一条 create，最多文件的 bucket 唯一。
 * 示例中 bucketA 有 2 个文件，bucketB 有 0 个，bucketC 有 3 个，返回 "bucketC"。
 * 截图说明 O(commands.size^2) 的实现可以通过；具体数据范围未拍到。
 */
class CodeSignal_LargestBucket {
    fun solution(commands: Array<String>): String {
        val fs = mutableMapOf<String, MutableSet<String>>()
        var max = -1
        var res = ""
        var cur = ""
        for (command in commands) {
            val cmd = command.split(" ")
            when(cmd[0]) {
                "goto" -> cur = cmd[1]
                "create" -> {
                    fs.getOrPut(cur) { mutableSetOf() }.add(cmd[1])
                    if(fs[cur]!!.size > max) {
                        max = fs[cur]!!.size
                        res = cur
                    }
                }
            }
        }
        return res
    }
}

fun main() {
    val t = CodeSignal_LargestBucket()
    val commands = arrayOf(
        "goto bucketA",
        "create fileA",
        "create fileB",
        "create fileA",
        "goto bucketB",
        "goto bucketC",
        "create fileA",
        "create fileB",
        "create fileC"
    )
    println(t.solution(commands)) // bucketC
}
