package novice.linkedList;

/*
* 两个单链表相交的一系列问题
* 【题目】 在本题中，单链表可能有环，也可能无环。给定两个 单链表的头节点 head1和head2，这两个链表可能相交，
* 也可能 不相交。请实现一个函数， 如果两个链表相交，请返回相交的 第一个节点;如果不相交，返回null 即可。
* 要求:如果链表1 的长度为N，链表2的长度为M，时间复杂度请达到 O(N+M)，额外 空间复杂度请达到O(1)。
*
*
*
* 思路：这题较难，要分几种情况考虑。首先要考虑，该链表是否有环。所以分成以下三种
*           1. 两者都没有环
*           2. 一个有环，一个无环，不可能相交
*           3. 两个都有环
* */

public class FindFirstIntersectNode {
    public static class Node{
        public int value;
        public Node next;

        public Node(int data){
            this.value=data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2){
        if(head1==null || head2==null){
            return null;
        }
        Node loop1=getLoopNode(head1);
        Node loop2=getLoopNode(head2);
        if(loop1==null && loop2==null){//如果两个链表都无环
            return noLoop(head1,head2);
        }
        if(loop1!=null && loop2!=null){
            return bothLoop(head1,loop1,head2,loop2);
        }
        return null;
    }

    //如果有环，获得刚入环的节点loop
    public static Node getLoopNode(Node head) {
        if(head==null || head.next==null || head.next.next==null){
            return null;
        }
        Node n1=head.next;//n1表示慢指针，一次走一步
        Node n2=head.next.next; //n2表示快指针，一次走两步
        while(n1!=n2){//如果有环，n1和n2必然会相交，这是数学上可以证明的
            if(n2.next==null||n2.next.next==null){
                return null;
            }
            n2=n2.next.next;
            n1=n1.next;
        }
        //下面来获得相交的节点,快指针n2回到头节点，且一次走一步，n1和n2必然会相交，且相交处就是loop处，证明较难，省略。
        n2=head;
        while (n1!=n2){
            n1=n1.next;
            n2=n2.next;
        }
        return n1;
    }

    public static Node noLoop(Node head1, Node head2) {
        if(head1==null || head2==null){
            return null;
        }
        Node cur1=head1;
        Node cur2=head2;
        int n=0;
        //其实下面可以用两个长度分别来表示链表的长度的，更好理解点，这里用一个n全部表示了
        while (cur1.next != null){
            n++;
            cur1=cur1.next;
        }
        while (cur2.next !=null){
            n--;
            cur2=cur2.next;
        }
        if(cur1!=cur2){
            return null;//两个链表的结尾肯定是一个点，如果不是一个点，根本不可能相交
        }
        cur1 = n>0 ? head1 : head2; //如果n>0说明head1链表更长，用cur1来表示长的链表
        cur2 = cur1 == head1 ? head2 : head1;
        n=Math.abs(n);
        while (n!=0){
            n--;
            cur1=cur1.next;//n就是两个链表的长度差值，让长链先走n步
        }
        while (cur1!=cur2){//走到相等处即是相交的地方
            cur1=cur1.next;
            cur2=cur2.next;
        }
        return cur2;
    }

    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1=null;
        Node cur2=null;
        if(loop1==loop2){//同没有环的相交情况，要计算出两个的长度差值
            cur1=head1;
            cur2=head2;
            int n=0;
            while (cur1!=loop1){
                n++;
                cur1=cur1.next;
            }
            while (cur2!=loop2){
                n--;
                cur2=cur2.next;
            }
            cur1=n>0?head1:head2;
            cur2=cur1==head1?head2:head1;
            n=Math.abs(n);
            while (n!=0){
                n--;
                cur1=cur1.next;
            }
            while(cur1!=cur2){
                cur1=cur1.next;
                cur2=cur2.next;
            }
            return cur1;
        }else{
            cur1=loop1.next;
            while (cur1!=loop1){//在圈里转，看能否遇到loop2
                if(cur1==loop2){
                    return loop1;//返回loop1，loop2都可以
                }
                cur1=cur1.next;
            }
            return null;
        }
    }
    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }






}
