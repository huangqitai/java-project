package com.qitai.niuke;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListNodeTest {
    List<Integer> vals = new ArrayList<>();
    List<ListNode> listNodes = new ArrayList<>();
    @Test
    public void testNode1(){
        ListNode listNode = new ListNode(0);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(4);

        ListNode listNode1 = new ListNode(3);
        listNode1.next = new ListNode(4);
        listNode1.next.next = new ListNode(5);
        listNode1.next.next.next = new ListNode(6);
        listNode1.next.next.next.next = new ListNode(7);
        Merge(listNode,listNode1);
    }

    /**
     * 合并链表，单调不减
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1==null&&list2==null) return null;
        if (list1!=null&&list2==null) return list1;
        if (list1==null&&list2!=null) return list2;
        while (list2!=null){
            vals.add(list2.val);
            list2 = list2.next;
        }
        while (list1!=null){
            vals.add(list1.val);
            list1 = list1.next;
        }
        Collections.sort(vals);
        for (int val : vals ) {
            ListNode listNode = new ListNode(val);
            listNodes.add(listNode);
        }
        for (int i=0;i<listNodes.size()-1;i++){
            listNodes.get(i).next = listNodes.get(i+1);
        }
        return listNodes.get(0);
    }

    private void insert(){}

}

