package novice.tree;

/**
 * 给定一个完全二叉树的头节点，返回这棵树的节点个数
 *
 * 思路：这里借用了满二叉树的性质：总共节点数为2^(h)-1。然后找右子树的深度来判断左右子树谁为满二叉树，
 * 谁是完全二叉树这样满二叉树可以求解，而完全二叉树则可以继续递归求解
 */
public class CompleteTreeNodeNumber {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value=data;
        }
    }


    public static int nodeNum(Node head){
        if(head==null){
            return 0;
        }
        return bs(head,1,mostLeftLevel(head,1));
    }

    //bs的返回值表示以node为头节点的完全二叉树的节点数是多少
    //level表示node所在的层数
    public static int bs(Node node,int level,int h){
        if(level==h){
            return 1;
        }
        if(mostLeftLevel(node.right,level+1)==h){
            //如果右子树的的最深层到达最底层,那么左边时满二叉树，右边还是一个完全二叉树
            return ((1<<(h-level))+bs(node.right,level+1,h));
        }else{
            //如果右子树的最深层没有到达最底层，那么右边为满二叉树，但是少一层；左边时完全二叉树
            return (bs(node.left,level+1,h)+(1<<(h-level-1)));
        }

    }


    public static int mostLeftLevel(Node node, int level){
        while (node!=null){
            level++;
            node=node.left;
        }
        return level-1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));

    }
}
