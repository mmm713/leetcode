package com.home.learn.bytedance;

import com.home.learn.library.RandomListNode;

public class CopyListRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        RandomListNode runner = head;
        //自我复制
        while(runner != null){
            RandomListNode insert = new RandomListNode(runner.label);
            insert.next = runner.next;
            runner.next = insert;
            runner = insert.next;
        }
        runner = head;
        //定向随机
        while(runner != null){
            runner.next.random = (runner.random == null) ? null : runner.random.next;
            runner = runner.next.next;
        }
        runner = head.next;
        RandomListNode result = runner;
        //拆分链表
        while(runner != null){
            head.next = runner.next;
            head = head.next;
            if(head != null) runner.next = head.next;
            runner = runner.next;
        }
        return result;
    }
}
