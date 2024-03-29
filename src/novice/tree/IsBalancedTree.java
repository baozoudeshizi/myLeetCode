package novice.tree;

/**
 * 判断一棵二叉树是否是平衡二叉树
 * 思路：1.检查左子树是否平衡； 2.检查右子树是否平衡  3.检查左右两颗子树的高度
 *
 * 所以要返回的数据看应该是是否平和和高度
 */

public class IsBalancedTree {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value=data;
        }
    }

    public static class  ReturnData{
        public boolean isB;
        public int h;

        public ReturnData(boolean isB, int h){
            this.isB=isB;
            this.h=h;
        }
    }

    //主函数
    public static boolean isBalance(Node head){
        return process(head).isB;
    }


    public static ReturnData process(Node head){
        if(head==null){
            return new ReturnData(true,0);
        }
        ReturnData leftData=process(head.left);
        if(!leftData.isB){
            return new ReturnData(false,0);
        }
        ReturnData rightData=process(head.right);
        if(!rightData.isB){
            return new ReturnData(false,0);
        }
        if(Math.abs(leftData.h-rightData.h)>1){
            return new ReturnData(false,0);
        }
        return new ReturnData(true,Math.max(leftData.h,rightData.h)+1);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalance(head));

    }


}
