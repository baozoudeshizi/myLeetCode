package novice.tree;

import java.util.Stack;

public class PreInPosTravelsal {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value=data;
        }
    }

    //前序，递归
    public static void preOrderRecur(Node head){
        if(head==null){
            return;
        }
        System.out.print(head.value+" ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    //中序，递归
    public static void inOrderRecur(Node head){
        if(head==null){
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value+" ");
        inOrderRecur(head.right);

    }

    //逆序，递归
    public static void posOrderRecur(Node head){
        if(head==null){
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value+" ");
    }

    //前序，非递归
    //定义一个栈，压入头节点，在stack非空时，{弹出头结点，再分别压入右节点和左节点}
    public static void preOrderUnRecur(Node head){
        System.out.print("pre-order:");
        if(head!=null){
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            while (!stack.isEmpty()){
                head=stack.pop();
                System.out.print(head.value+" ");
                if(head.right!=null){
                    stack.push(head.right);
                }
                if(head.left!=null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    //中序，非递归，head不仅仅是head，其实指的是当前节点
    //当栈中非空或者head!=null的时候，如果当前节点非空，则压入栈，当前节点向左孩子移动，这样就把树的左边一排都压入栈了。
    //如果当前节点已经到叶子节点指向空了，那么栈中则弹出并打印，再讲当前节点向右孩子移动
    public static void inOrderUnRecur(Node head){
        System.out.print("in-order:");
        if(head!=null){
            Stack<Node> stack=new Stack<Node>();
            while(!stack.isEmpty() || head!=null){
                if(head!=null){
                    stack.push(head);
                    head=head.left;
                }else{
                    head=stack.pop();
                    System.out.print(head.value +" ");
                    head=head.right;
                }
            }
        }
        System.out.println();
    }


    //后序，非递归，由前序改编
    //因为前序是中左右，所以我们调换下压栈顺序获得中右左，再讲中右左反转，则得到左右中。不过需要申请两个栈。
    public static void posOrderUnRecur(Node head){
        System.out.print("pos-order");
        if(head!=null){
            Stack<Node> s1=new Stack<Node>();
            Stack<Node> s2=new Stack<Node>();
            s1.push(head);
            while (!s1.isEmpty()){
                head=s1.pop();
                //System.out.print(head.value+" ");
                s2.push(head);//在前序中，这句是打印
                if(head.left!=null){
                    s1.push(head.left);
                }
                if(head.right!=null){
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()){
                System.out.print(s2.pop().value+" ");
            }

        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur(head);
        //posOrderUnRecur2(head);

    }


}
