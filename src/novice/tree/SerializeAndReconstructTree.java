package novice.tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 介绍二叉树的序列化和反序列化,如果是叶节点，则其子节点用#号表示
 */
public class SerializeAndReconstructTree {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value=data;
        }
    }

    public static String serialByPre(Node head){
        if(head==null){
            return "#_";
        }
        String res =  head.value+ "_";//这里用_的原因是防止1，2和12这种情况分不清
        res+=serialByPre(head.left);
        res+=serialByPre(head.right);
        return res;
    }


    //将字符串分解并放入队列中
    public static Node reconByPreString(String preStr){
        String[] values=preStr.split("_");
        Queue<String> queue=new LinkedList<String>();
        for(int i=0; i<values.length;i++){
            queue.offer(values[i]);//往队尾插入元素
        }
        return reconPreOrder(queue);
    }

    //先序，反序列化
    public static Node reconPreOrder(Queue<String> queue) {
        String value=queue.poll();
        if(value.equals("#")){
            return null;
        }
        Node head =  new Node(Integer.valueOf(value));
        head.left=reconPreOrder(queue);
        head.right=reconPreOrder(queue);
        return head;
    }

    //按照层序列化
    public static String serialByLevel(Node head){
        if(head==null){
            return "#_";
        }
        String res=head.value+"_";
        Queue<Node> queue=new LinkedList<Node>();
        queue.offer(head);
        while (!queue.isEmpty()){
            head=queue.poll();
            if(head.left!=null){
                res+=head.left.value+"_";
                queue.offer(head.left);
            }else{
                res+="#_";
            }
            if(head.right!=null){
                res+=head.right.value+"_";
                queue.offer(head.right);
            }else{
                res+="#_";
            }
        }
        return res;
    }

    //按照层，反序列化
    public static Node reconByLevelString(String levelStr){
        String[] values=levelStr.split("_");
        int index=0;
        Node head=generateNodeByString(values[index++]);
        Queue<Node> queue = new LinkedList<Node>();
        if(head!=null){
            queue.offer(head);
        }
        Node node=null;
        while (!queue.isEmpty()){
            node=queue.poll();
            node.left=generateNodeByString(values[index++]);
            node.right=generateNodeByString(values[index++]);
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        return head;
    }

    public static Node generateNodeByString(String val) {
        if(val.equals("#")){
            return null;
        }
        return new Node(Integer.valueOf(val));
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
        Node head = null;
        printTree(head);

        String pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        String level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(100);
        head.left = new Node(21);
        head.left.left = new Node(37);
        head.right = new Node(-42);
        head.right.left = new Node(0);
        head.right.right = new Node(666);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

    }
}

