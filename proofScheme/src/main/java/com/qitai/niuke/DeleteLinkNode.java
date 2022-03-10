package com.qitai.niuke;

import org.junit.Test;

/**
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点。
 *
 * 1.此题对比原题有改动
 * 2.题目保证链表中节点的值互不相同
 * 3.该题只会输出返回的链表和结果做对比，所以若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 */
public class DeleteLinkNode {
    @Test
    public void test(){
        int[] nodes = {2,5,1,9};
        ListNode listNode = new ListNode(nodes[0]);
        fun(listNode,nodes,1);
        System.out.println(listNode.val);
        ListNode head = deleteNode(listNode,5);
        System.out.println(head.val);
    }
    public void fun(ListNode listNode,int[] nodes,int i){
        if (i>= nodes.length){
            return;
        }
        ListNode listNode1 = new ListNode(nodes[i]);
        listNode.next = listNode1;
        fun(listNode1,nodes,i+1);
    }
    public ListNode deleteNode (ListNode head, int val) {
        if (head.val==val){
            head = head.next;
            deleteNode(head,val);
        }else {
            fun(head,val);
        }
        return head;
    }
    public void fun(ListNode node,int val){
        if (node.next==null){
            return;
        }
        if (node.next.val==val){
            node.next = node.next.next;
            fun(node,val);
        }else {
            fun(node.next,val);
        }
    }
}
