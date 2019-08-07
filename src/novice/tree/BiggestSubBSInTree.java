package novice.tree;


public class BiggestSubBSInTree {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value=data;
        }
    }

    //主方法，返回最大搜索子树的头节点
    public static Node getMax(Node head){
        return process(head).maxBSTHead;
    }

    public static class ReturnType{
        public Node maxBSTHead; //最大所左子树的头
        public int maxBSTSize; //最大搜索子树的节点数
        public int min; //最大搜索子树上的最小值
        public int max; // 最大搜索子树上的最大值

        public ReturnType(Node maxBSTHead, int maxBSTSize, int min, int max) {
            this.maxBSTHead = maxBSTHead;
            this.maxBSTSize = maxBSTSize;
            this.min = min;
            this.max = max;
        }
    }

    public static ReturnType process(Node x){
        if(x==null){
            return  new ReturnType(null,0,Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        ReturnType lData = process(x.left); //默认得到左树的全部信息
        ReturnType rData = process(x.right); //默认得到右树的全部信息

        //以x为头的子树的最小值是：左树最小，右数组小，x的值，三者中最小的
        //最大值也同理
        int min = Math.min(x.value,Math.min(lData.min,rData.min));
        int max = Math.max(x.value,Math.max(lData.max,rData.max));

        //若只考虑最大值在左子树和右子树上，求maxBSTSize和maxBSTHead
        int maxBSTSize = Math.max(lData.maxBSTSize,rData.maxBSTSize);
        Node maxBSTHead = lData.maxBSTSize >= rData.maxBSTSize ? lData.maxBSTHead : rData.maxBSTHead;

        //利用收集的信息，可以判断另外一种情况
        //lData.maxBSTHead == x.left表示判断当前左子树的最大头结点是否是左子树的头结点，如果不是，则必然不能与x相连形成更大的树
        if(lData.maxBSTHead == x.left && rData.maxBSTHead == x.right && x.value>lData.max && x.value<rData.min){
            maxBSTSize = lData.maxBSTSize + rData.maxBSTSize + 1;
            maxBSTHead = x;
        }
        return new ReturnType(maxBSTHead,maxBSTSize,min,max);
    }
}
