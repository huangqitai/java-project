package com.qitai.niuke;

import org.junit.Test;

import java.util.Stack;


public class Solution {
    /**
     * 两个栈实现队列
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        while(!stack2.empty()){
            stack1.push(stack2.pop());
        }
        stack1.push(node);
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
    }

    public int pop() {
        return stack2.pop();
    }

    /**
     * 旋转数组
     * @param array {3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * @return
     */
    public int minNumberInRotateArray(int [] array) {
        if (array.length==0) return 0;
        int min=array[0];
        for (int i=0;i<array.length;i++){
             min = array[i]>min?min:array[i];
        }
        return min;
    }
    @Test
    public void testarr(){
        int [] array = {3,4,5,1,2};
        System.out.println(minNumberInRotateArray(array));
    }


    @Test
    public void JumpFloor() {
    /**
     * 正数、负数转二进制，其中负数是其补码标识
     */
        String binary5 = Integer.toBinaryString(160);
        int len1 = binary5.length();
        String newstring1 = binary5.replaceAll("1","");
        int newlen1 = newstring1.length();
        System.out.println(len1-newlen1);

    }

    public double Power(double base, int exponent) {
        if(base==0&&exponent==0) return 0;
        if(base==0){
            return 0;
        }
        if(exponent==0){
            return 0;
        }

        if(exponent==1){
            return base;
        }
        return Power(base,exponent-1)*base;
    }

    @Test
    public void testPower(){
        //System.out.println(Power(2.2,5));

        int [] od = new int[10];
        System.out.println(od.length);
    }


    public void reOrderArray(int [] array) {
        int [] odd;
        int [] even;
        int oddlen = 0;
        int evenlen = 0;

        for(int i=0;i<array.length;i++){
            if(array[i]%2!=0){
                oddlen++;
            }else {
                evenlen++;
            }
        }
        odd = new int[oddlen];
        even = new int[evenlen];

        oddlen = 0;
        evenlen = 0;
        for(int i=0;i<array.length;i++){
            if(array[i]%2!=0){
                odd[oddlen] = array[i];
                oddlen++;
            }else {
                even[evenlen] = array[i];
                evenlen++;
            }
        }

        for (int i=0;i<odd.length;i++){
            array[i] = odd[i];
        }
        for (int i=odd.length;i<(odd.length)+(even.length);i++){
            array[i] = even[i-(odd.length)];
        }

        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+"     ");
        }

    }

    @Test
    public void testArr(){
        int [] arr = {1,2,3,4,5,6,7};
        reOrderArray(arr);
    }

    @Test
    public void testNode(){
        ListNode listNode = new ListNode(0);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(4);
       /* while (listNode!=null){
            System.out.print(listNode.val+"     ");
            listNode = listNode.next;
        }*/
        //System.out.println(FindKthToTail(listNode,2).val);
        System.out.println(ReverseList(listNode).val);
    }

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
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1==null||list2==null){
            return null;
        }
        int count = 0;
        while (list1!=null||list2!=null){
            if (list1!=null){
                count++;
                list1 = list1.next;
            }
            if (list2!=null){
                count++;
                list2 = list2.next;
            }
        }
        ListNode[] listNodes = new ListNode[count];


        return null;
    }

    public ListNode Merge1(ListNode list1,ListNode list2) {
        if (list1==null||list2==null){
            return null;
        }
        int count = 0;
        ListNode list1after = null;
        while (list1!=null) {
            while (list2!=null){
                if (list1.val<list2.val){
                    list1after = list2;
                    list1.next = list2;
                    list1.next.next = list1after;
                }else {

                }
            }
        }

        return null;
    }
    /**
     * 输入一个链表，反转链表后，输出新链表的表头
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        if (head==null){
            return null;
        }
        ListNode newHead = null;
        ListNode pPrev = null;
        while(head!=null){
            pPrev = head;
            head = head.next;
            pPrev.next = newHead;
            newHead = pPrev;
        }
        return newHead;
    }

    /**
     * 输入一个链表，输出倒数第K个元素
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        if (head==null){
            return null;
        }
        ListNode nowNode = head;
        int count = 0;
        while (head!=null){
            count++;
            head = head.next;
        }
        if (k>count){
            return null;
        }
        int index = count-k+1;
        count = 0;
        while (nowNode!=null){
            count++;
            if (count==index){
                return nowNode;
            }
            nowNode = nowNode.next;
        }
        return null;
    }

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}