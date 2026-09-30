package com.home.learn

/**
 * LeetCode 430. Flatten a Multilevel Doubly Linked List（扁平化多级双向链表）
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 *
 * 每个节点除了 prev、next，还可能通过 child 指向另一条双向链表的头节点。
 * 将所有节点连接成一条双向链表：某节点的子链表应放在该节点之后、原 next 之前。
 * 子链表内也可能存在更深层的 child，需要同样处理。
 * 返回扁平化后的头节点，正确维护 prev、next，并将所有 child 设置为 null。
 * 使用原有节点；输入为空时返回 null。
 * 本地扩展：节点值允许为 null，此时跳过该节点，但仍展开其 child，再处理原 next。
 * 返回第一个保留的节点；如果所有节点值均为 null，则返回 null。
 *
 * 节点总数不超过 1000；1 <= 节点值 <= 100_000。
 *
 * 示例：主链 1 <-> 2，节点 1 的 child 为节点 3，结果为 1 <-> 3 <-> 2。
 * 示例：主链 1-2-3-4-5-6，3.child = 7-8-9-10，8.child = 11-12，
 *       结果为 1-2-3-7-8-11-12-9-10-4-5-6（所有相邻节点双向连接）。
 */
class Medium_430_FlattenAMultilevelDoublyLinkedList {
    // 嵌套定义避免与其他题目的 Node 冲突；val 扩展为可空类型。
    class Node(var `val`: Int?) {
        var prev: Node? = null
        var next: Node? = null
        var child: Node? = null
    }

    fun flatten(head: Node?): Node? {
        if (head == null) return head
        val sudo = Node(null)
        dfs(sudo, head)
        val result = sudo.next
        result?.prev = null
        return result
    }

    fun dfs(prev: Node?, curr: Node?): Node? {
        if(curr == null) return prev
        // 在修改任何指针前保存原来的两条分支。
        val next = curr.next
        val child = curr.child
        curr.prev = null
        curr.next = null
        curr.child = null

        val last = if (curr.`val` != null) {
            curr.prev = prev
            prev?.next = curr
            curr
        } else {
            prev // 跳过当前节点，子链表仍接在已有结果的尾部。
        }
        val tail = dfs(last, child)
        return dfs(tail, next)
    }
}

// 运行 main，同时检查顺序、节点身份、双向指针和 child 清空。
fun main() {
    data class Case(
        val name: String,
        val head: Medium_430_FlattenAMultilevelDoublyLinkedList.Node?,
        val expected: List<Medium_430_FlattenAMultilevelDoublyLinkedList.Node>
    )

    // levels 中每个列表描述一条原始同层链；编号用于标识节点，而非依赖节点值。
    fun fixture(
        name: String, levels: List<List<Int>>, children: List<Pair<Int, Int>>,
        expectedIds: List<Int>, repeatedValues: Boolean = false, nullIds: Set<Int> = emptySet()
    ): Case {
        val nodes = levels.flatten().associateWith {
            Medium_430_FlattenAMultilevelDoublyLinkedList.Node(
                if (it in nullIds) null else if (repeatedValues) 7 else it
            )
        }
        for (level in levels) {
            for ((left, right) in level.zipWithNext()) {
                nodes.getValue(left).next = nodes.getValue(right)
                nodes.getValue(right).prev = nodes.getValue(left)
            }
        }
        for ((parent, child) in children) nodes.getValue(parent).child = nodes.getValue(child)
        return Case(name, levels.firstOrNull()?.firstOrNull()?.let { nodes.getValue(it) },
            expectedIds.map { nodes.getValue(it) })
    }

    val cases = listOf(
        fixture("官方示例 1", listOf((1..6).toList(), (7..10).toList(), listOf(11, 12)),
            listOf(3 to 7, 8 to 11), listOf(1, 2, 3, 7, 8, 11, 12, 9, 10, 4, 5, 6)),
        fixture("官方示例 2：头节点有 child", listOf(listOf(1, 2), listOf(3)),
            listOf(1 to 3), listOf(1, 3, 2)),
        fixture("官方示例 3：空链表", emptyList(), emptyList(), emptyList()),
        fixture("单节点", listOf(listOf(1)), emptyList(), listOf(1)),
        fixture("普通双向链表", listOf((1..5).toList()), emptyList(), (1..5).toList()),
        fixture("尾节点有子链表", listOf(listOf(1, 2), listOf(3, 4)),
            listOf(2 to 3), listOf(1, 2, 3, 4)),
        fixture("中间节点有子链表", listOf(listOf(1, 2, 3), listOf(4, 5)),
            listOf(2 to 4), listOf(1, 2, 4, 5, 3)),
        fixture("同层多个节点有 child", listOf(listOf(1, 2, 3), listOf(4, 5), listOf(6), listOf(7, 8)),
            listOf(1 to 4, 2 to 6, 3 to 7), listOf(1, 4, 5, 2, 6, 3, 7, 8)),
        fixture("子链表头节点再次分叉", listOf(listOf(1, 2), listOf(3, 4), listOf(5, 6)),
            listOf(1 to 3, 3 to 5), listOf(1, 3, 5, 6, 4, 2)),
        fixture("子链表尾节点再次分叉", listOf(listOf(1, 2), listOf(3, 4), listOf(5, 6)),
            listOf(1 to 3, 4 to 5), listOf(1, 3, 4, 5, 6, 2)),
        fixture("重复值仍需保留节点身份", listOf(listOf(1, 2), listOf(3, 4)),
            listOf(1 to 3), listOf(1, 3, 4, 2), repeatedValues = true),
        fixture("1000 个同层节点", listOf((1..1000).toList()), emptyList(), (1..1000).toList()),
        fixture("1000 层 child 链", (1..1000).map { listOf(it) },
            (1 until 1000).map { it to it + 1 }, (1..1000).toList()),
        fixture("单个 null 值节点", listOf(listOf(1)), emptyList(), emptyList(), nullIds = setOf(1)),
        fixture("跳过头节点后返回原 next", listOf(listOf(1, 2, 3)), emptyList(),
            listOf(2, 3), nullIds = setOf(1)),
        fixture("跳过头节点但优先保留 child", listOf(listOf(1, 2), listOf(3, 4)),
            listOf(1 to 3), listOf(3, 4, 2), nullIds = setOf(1)),
        fixture("跳过中间节点仍展开 child", listOf(listOf(1, 2, 3), listOf(4, 5)),
            listOf(2 to 4), listOf(1, 4, 5, 3), nullIds = setOf(2)),
        fixture("跳过尾部节点后正确终止", listOf(listOf(1, 2, 3)), emptyList(),
            listOf(1), nullIds = setOf(2, 3)),
        fixture("空值尾节点的 child 仍然保留", listOf(listOf(1, 2), listOf(3)),
            listOf(2 to 3), listOf(1, 3), nullIds = setOf(2)),
        fixture("连续空值 child 节点", listOf(listOf(1, 2), listOf(3), listOf(4, 5)),
            listOf(1 to 3, 3 to 4), listOf(5, 2), nullIds = setOf(1, 3, 4)),
        fixture("整个 child 链被跳过后接回 next", listOf(listOf(1, 2), listOf(3, 4)),
            listOf(1 to 3), listOf(1, 2), nullIds = setOf(3, 4)),
        fixture("多层节点全部为空值", listOf(listOf(1, 2), listOf(3, 4), listOf(5)),
            listOf(1 to 3, 3 to 5), emptyList(), nullIds = setOf(1, 2, 3, 4, 5))
    )

    fun validate(case: Case, head: Medium_430_FlattenAMultilevelDoublyLinkedList.Node?): String? {
        if (head !== case.expected.firstOrNull()) return "返回值应为第一个保留的节点，或 null"
        var current = head
        // 按期望节点数限定遍历次数，即使产生环也不会无限循环。
        for ((index, expected) in case.expected.withIndex()) {
            if (current !== expected) return "第 $index 个节点身份或顺序不正确"
            if (expected.`val` == null) return "结果中不应保留 null 值节点"
            if (expected.prev !== case.expected.getOrNull(index - 1)) return "第 $index 个节点 prev 不正确"
            if (expected.next !== case.expected.getOrNull(index + 1)) return "第 $index 个节点 next 不正确"
            if (expected.child != null) return "第 $index 个节点 child 未清空"
            current = expected.next
        }
        if (current != null) return "链表存在多余节点或环"
        return null
    }

    val solution = Medium_430_FlattenAMultilevelDoublyLinkedList()
    var passed = 0
    for (case in cases) {
        val error = validate(case, solution.flatten(case.head))
        if (error == null) passed++
        println("${if (error == null) "PASS" else "FAIL"} | ${case.name} | ${error ?: "结构正确"}")
    }
    check(passed == cases.size) { "$passed/${cases.size} tests passed" }
    println("All ${cases.size} tests passed!")
}
