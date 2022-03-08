package com.qitai.niuke;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 * 给定一个二叉树其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的next指针。
 * 示例:
 * 输入:{8,6,10,5,7,9,11},8
 * 返回:9
 * 解析:这个组装传入的子树根节点，其实就是整颗树，中序遍历{5,6,7,8,9,10,11}，
 * 根节点8的下一个节点就是9，应该返回{9,10,11}，后台只打印子树的下一个节点，所以只会打印9，
 *
 * 输入描述：
 * 输入分为2段，第一段是整体的二叉树，第二段是给定二叉树节点的值，后台会将这2个参数组装为一个二叉树局部的子树传入到函数GetNext里面，用户得到的输入只有一个子树根节点
 * 返回值描述：
 * 返回传入的子树根节点的下一个节点，后台会打印输出这个节点
 * 示例1
 * 输入：{8,6,10,5,7,9,11},8
 * 返回值：9
 *
 * 示例2
 * 输入：{8,6,10,5,7,9,11},6
 * 返回值：7
 *
 * 示例3
 * 输入：{1,2,#,#,3,#,4},4
 * 返回值：1
 *
 * 示例4
 * 输入：{5},5
 * 返回值："null"
 */
public class TreeLinkNodeSolution {
    int i = 1;
    @Test
    public void test(){
        int[] treeVals = {8,6,10,5,7,9,11};
        int[] treeVals1 = {1,2,-1,-1,3,-1,4};
        int[] treeVals2 = {2,-1,3,-1,4,-1,5};
        int val = 8;
        TreeLinkNode treeLinkNode = buildTree(treeVals2);
        System.out.println(treeLinkNode.val);
        TreeLinkNode valTreeLinkNode = getTree(treeLinkNode,5);
        TreeLinkNode next = GetNext(valTreeLinkNode);
        System.out.println(next.val);
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode==null){
            return null;
        }
        //节点右子节点不为空，
        if (pNode.right!=null){
            //右子节点的左子节点不为空，则中序遍历的下一个节点是右子节点的左子节点
            if (pNode.right.left!=null){
                return pNode.right.left;
            }
            //右子节点的左子节点为空，则中序遍历的下一个节点是右子节点
            return pNode.right;
        }
        //如果节点右子节点为空且没有父节点，则没有中序遍历的下一节点
        if (pNode.next==null){
            return null;
        }

        //父节点的左子节点不为空且当前节点就是父节点的左子节点，则下一节点是父节点
        if (pNode.next.left==pNode){
            return pNode.next;
        }
        //当前节点是父节点的右子节点
        TreeLinkNode next = pNode.next;
        while (true){
            if (next.next==null){
                return null;
            }
            if (next==next.next.left){
                return next.next;
            }
            next = next.next;
        }
    }

    public TreeLinkNode getTree(TreeLinkNode rootTreeLinkNode,int val){
        if (rootTreeLinkNode.left!=null&&rootTreeLinkNode.left.val==val){
            return rootTreeLinkNode.left;
        }
        if (rootTreeLinkNode.right!=null&&rootTreeLinkNode.right.val==val){
            return rootTreeLinkNode.right;
        }
        if (rootTreeLinkNode.left!=null){
            return getTree(rootTreeLinkNode.left,val);
        }
        if (rootTreeLinkNode.right!=null){
            return getTree(rootTreeLinkNode.right,val);
        }
        return null;
    }

    /**
     * 根据数组组建树，-1标识空节点
     * @param treeVals
     * @return
     */
    public TreeLinkNode buildTree(int[] treeVals){
        TreeLinkNode treeLinkNode = new TreeLinkNode(treeVals[0]);
        List<TreeLinkNode> treeLinkNodes = new ArrayList<>();
        treeLinkNodes.add(treeLinkNode);
        fun(treeLinkNodes,treeVals);
        return treeLinkNode;
    }
    public void fun(List<TreeLinkNode> treeLinkNodes, int[] treeVals){
        if (i>= treeVals.length){
            return;
        }
        List<TreeLinkNode> funTreeLinkNodes = new ArrayList<>();
        for (TreeLinkNode treeLinkNode:treeLinkNodes){
            if (treeVals[i]!=-1){
                treeLinkNode.left = new TreeLinkNode(treeVals[i]);
                treeLinkNode.left.next = treeLinkNode;
                funTreeLinkNodes.add(treeLinkNode.left);
            }
            i++;
            if (treeVals[i]!=-1){
                treeLinkNode.right = new TreeLinkNode(treeVals[i]);
                treeLinkNode.right.next = treeLinkNode;
                funTreeLinkNodes.add(treeLinkNode.right);
            }
            i++;
        }
        fun(funTreeLinkNodes,treeVals);
    }
}
