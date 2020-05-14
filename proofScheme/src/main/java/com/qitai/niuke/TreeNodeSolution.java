package com.qitai.niuke;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNodeSolution {
    String rootPre="";
    @Test
    public void Test(){
        TreeNode treeNode0 = new TreeNode(0);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);

        treeNode0.left = treeNode1;
        treeNode0.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.left = treeNode5;
        treeNode2.right = treeNode6;
        treeNode3.left = treeNode7;
        treeNode3.right = treeNode8;
        treeNode4.left = treeNode9;
        HasSubtree(treeNode0,treeNode2);
    }

    /**
     * 判断B二叉树是否是A二叉树的子结构
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root2==null||root1==null) return false;

        preOrderTraverse1(root1);
        String root1Pre = rootPre;
        rootPre = "";
        preOrderTraverse1(root2);
        String root2Pre = rootPre;

        if (root1Pre.contains(root2Pre)) return true;
        return false;
    }
    public void preOrderTraverse1(TreeNode root) {
        if (root != null) {
            rootPre += root.val;
            preOrderTraverse1(root.left);
            preOrderTraverse1(root.right);
        }
    }


    @Test
    public void Test1(){
        TreeNode treeNode0 = new TreeNode(0);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);

        treeNode0.left = treeNode1;
        treeNode0.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.left = treeNode5;
        treeNode2.right = treeNode6;
        treeNode3.left = treeNode7;
        treeNode3.right = treeNode8;
        treeNode4.left = treeNode9;
        Mirror(treeNode0);
    }
    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     * @param root
     */
    public void Mirror(TreeNode root) {
        preOrderTraverse2(root);
    }
    public void preOrderTraverse2(TreeNode root) {
        if (root != null) {
            if (hasChild(root)){
                TreeNode treeNode = root.left;
                root.left = root.right;
                root.right = treeNode;
            }
            System.out.print(root.val+"     ");
            preOrderTraverse2(root.left);
            preOrderTraverse2(root.right);
        }
    }
    private boolean hasChild(TreeNode treeNode){
        if (treeNode.left!=null||treeNode.right!=null) return true;
        else return false;
    }
    private boolean hasRight(TreeNode treeNode){
        if (treeNode.right!=null) return true;
        else return false;
    }

    private boolean hasLeft(TreeNode treeNode){
        if (treeNode.left!=null) return true;
        else return false;
    }



    @Test
    public void Test2(){
        TreeNode treeNode0 = new TreeNode(0);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);

        treeNode0.left = treeNode1;
        treeNode0.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.left = treeNode5;
        treeNode2.right = treeNode6;
        treeNode3.left = treeNode7;
        treeNode3.right = treeNode8;
        treeNode4.left = treeNode9;
        PrintFromTopToBottom(treeNode0);
    }
    /**
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     */
    List<Integer> integerList = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if (root==null) return null;
        queue.offer(root);
        while (!queue.isEmpty()){
            
        }
        return null;
    }

}
