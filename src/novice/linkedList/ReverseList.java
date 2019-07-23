package novice.linkedList;

public class ReverseList {

    public static class Node{
        public int value;
        public Node next;

        public Node(int data){
            this.value=data;
        }
    }

    public static Node reverseList(Node head){
        Node pre=null;
        Node next=null;
        while(head!=null){
            next=head.next; //next保存一下当前节点的下一节点
            head.next=pre; //当前节点下一个节点已经保存过了，所以转换指针方向指向前一个pre
            //向后移动
            pre=head; //pre走到head
            head=next; //head走到next
        }
        return pre;
    }

    public static class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;
        public DoubleNode(int data){
            this.value=data;
        }
    }

    public DoubleNode reverseList(DoubleNode head){
        DoubleNode pre=null;
        DoubleNode next=null;
        while(head != null){
            next=head.next;
            head.next=pre;
            head.last=next; //双向指针
            //向后移动
            pre=head; //pre走到head
            head=next; //head走到next
        }
        return pre;
    }
}
