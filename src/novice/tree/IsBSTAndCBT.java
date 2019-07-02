package novice.tree;

import novice.stackAndQueue.QueueByTwoStacks;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定二叉树的一个头结点head，已知其中没有重复值的节点。
 * 判断一棵树是否是搜索二叉树、判断一棵树是否是完全二叉树
 *
 * 搜索二叉树就是左节点都小于右节点，所以只要中序遍历时，进行比较即可。
 *
 * 完全二叉树得判断几种情况：
 * 1. 任何一个节点，如果有右节点而没有左节点，则返回false
 * 2. 在满足一的情况下，任何一个节点，如果左右两个节点不是都在（有左没右或者左右都没有），则该节点后面的节点都是叶节点，否则返回false
 */

public class IsBSTAndCBT {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value=value;
        }
    }

    static int nodeValue=Integer.MIN_VALUE;

    public static boolean isBST(Node head){
        //可以找到中序遍历的代码，比较前一个值和后一个值的大小
        if(head==null){
            return false;
        }
        isBST(head.left);
        if(nodeValue<head.value){
            nodeValue=head.value;
        }else{
            return false;
        }
        isBST(head.right);
        return true;
    }

    public static boolean isCBT(Node head){
        if(head==null){
            return true;
        }
        Queue<Node> queue =  new LinkedList<Node>();
        boolean leaf=false;
        queue.offer(head);
        while (!queue.isEmpty()){
            head=queue.poll();
            if((leaf&&(head.left!=null || head.right!=null))
                    ||
                    (head.left==null && head.right!=null)){
                return false;
            }
            if(head.left!=null){
                queue.offer(head.left);
            }
            if(head.right!=null){
                queue.offer(head.right);
            }else{
                leaf=true;
            }
        }
        return true;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        printTree(head);
        System.out.println(isBST(head));
        System.out.println(isCBT(head));

    }

}
