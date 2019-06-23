package novice.tree;

/**
 * 在二叉树中找到一个节点的后继节点
 【题目】 现在有一种新的二叉树节点类型如下:
 public class Node {
 public int value; public Node left;
 public Node right; public Node parent;
 public Node(int data)
 { this.value = data; }
 }
 该结构比普通二叉树节点结构多了一个指向父节点的parent指针。假设有一棵Node类型的节点组成的二叉树，
 树中每个节点的parent指针都正确地指向自己的父节点，头节点的parent指向null。
 只给一个在二叉树中的某个节点node，请实现返回node的后继节点的函数。
 在二叉树的中序遍历的序列中，node的下一个节点叫作node的后继节点。


 如何在二叉树中找一个节点的前驱节点呢？
 如果x有左子树，则左子树上最右的节点就是。
 如果x没有左子树，向上找，当前节点是它父节点的右孩子就停。
 */

public class SuccessorNode {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data){
            this.value=data;
        }
    }

    public static Node getSuccessorNode(Node node){//node 是树中的某一个节点
        if(node==null){
            return node;
        }
        //当前节点有右子树的时候，直接找到当前右子树中最左的节点
        if(node.right!=null){
            return getLeftMost(node.right);
        }else{//如果没有右子树，则获得其父节点。node和parent都向上移动吗，直到node是parent的左孩子
            Node parent = node.parent;
            while (parent!=null && parent.left !=node){//这里parent!=null的目的是为了node是最右叶子节点的情况
                node=parent;
                parent=node.parent;
            }
            return parent;
        }
    }

    public static Node getLeftMost(Node node) {
        if(node==null){
            return null;
        }
        while (node.left!=null){
            node=node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }
}

