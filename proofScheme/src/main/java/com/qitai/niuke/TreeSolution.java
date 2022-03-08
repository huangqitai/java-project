package com.qitai.niuke;

import org.junit.Test;

import java.util.Arrays;

/**
 * 描述
 * 给定节点数为 n 的二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建出如下图所示。
 *
 *
 * 提示:
 * 1.vin.length == pre.length
 * 2.pre 和 vin 均无重复元素
 * 3.vin出现的元素均出现在 pre里
 * 4.只需要返回根结点，系统会自动输出整颗树做答案对比
 * 数据范围：n \le 2000n≤2000，节点的值 -10000 \le val \le 10000−10000≤val≤10000
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 */
public class TreeSolution {

    @Test
    public void treeTest(){
        int[] pre = {1,2,4,7,3,5,6,8};//根左右
        int[] vin = {4,7,2,1,5,3,8,6};//左根右
        reConstructBinaryTree(pre,vin);
    }

    /**
     * 二叉树前序遍历的第一个元素即是二叉树的根节点，可以由根节点将中序遍历分为左右树两个数组
     * 前序遍历与中序遍历的左右元素是相等的，中序遍历由根节点平分的左树数组元素数量对应的前序遍历
     * 前相等数量的元素就是左边树的前序遍历数组
     * 前序遍历平分后的右边树第一个元素就是根节点的右节点，前序遍历平分后的左边树第一个元素就是根节点的左节点
     * 一直由此平分下去，直到左右树数组仅剩一个元素时设置为响应节点后停止递归
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] vin) {
        if (pre==null||vin==null||pre.length==0||vin.length==0){
            return null;
        }
        TreeNode rootTreeNode = new TreeNode(pre[0]);
        fun(rootTreeNode,pre,vin);
        return rootTreeNode;
    }
    public void fun(TreeNode rootTreeNode,int[] pre,int[] vin){
        int rootIndex = 0;
        for (int i  = 0; i < vin.length; i++) {
            if (vin[i] == pre[0]){
                rootIndex = i;
            }
        }
        int[] left = new int[rootIndex];
        int[] right = new int[vin.length-rootIndex-1];
        for (int j  = 0; j < vin.length; j++) {
            if (j<left.length){
                left[j] = vin[j];
            }else if(j == left.length){
            }else {
                right[j- rootIndex-1] = vin[j];
            }
        }
        int[] preLeft = new int[rootIndex];
        int[] preRight = new int[pre.length-rootIndex-1];
        for (int j  = 1; j < pre.length; j++) {
            if (j<left.length+1){
                preLeft[j-1] = pre[j];
            }else {
                preRight[j- rootIndex-1] = pre[j];
            }
        }
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(preLeft));
        System.out.println(Arrays.toString(right));
        System.out.println(Arrays.toString(preRight));
        if (left.length==1){
            rootTreeNode.left = new TreeNode(preLeft[0]);
        }else if (left.length>1){
            rootTreeNode.left = new TreeNode(preLeft[0]);
            fun(rootTreeNode.left,preLeft,left);
        }
        if (right.length==1){
            rootTreeNode.right = new TreeNode(preRight[0]);
        }else if (right.length>1){
            rootTreeNode.right = new TreeNode(preRight[0]);
            fun(rootTreeNode.right,preRight,right);
        }
    }
}
