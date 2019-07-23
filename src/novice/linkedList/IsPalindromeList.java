package novice.linkedList;

import java.util.Stack;

/**
 * 判断一个链表是否为回文结构
 * 【题目】 给定一个链表的头节点head，请判断该链表是否为回 文结构。
 * 例如: 1->2->1，返回true。 1->2->2->1，返回true。 15->6->15，返回true。 1->2->3，返回false。
 * 进阶: 如果链表长度为N，时间复杂度达到O(N)，额外空间复杂 度达到O(1)。
 */

public class IsPalindromeList {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }

    //第一种方法：需要一个n的空间
    //将链表全部压栈，然后弹出来与原链表相比较
    public static boolean isPalindrome1(Node head){
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        while (head!=null){
            if(head.value!=stack.pop().value){
                return  false;
            }
            head=head.next;
        }
        return true;
    }

    //需要额外的n/2空间
    public static boolean isPalindrome2(Node head){
        if (head == null || head.next == null) {
            return  true;
        }
        Node right = head.next;
        Node cur = head;
        while (cur.next != null && cur.next.next != null){
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> stack =  new Stack<Node>();
        while(right!=null){
            stack.push(right);//此时right已经走到中间部分，后半部分压栈
            right=right.next;
        }
        while (!stack.isEmpty()){
            if(head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //需要O(1)的空间
    public static boolean isPalindrome3(Node head){
        if(head==null || head.next ==null){
            return true;
        }
        Node n1=head;
        Node n2=head;
        while (n2.next!=null && n2.next.next!=null){ //find min node
            n1=n1.next; //n1->min
            n2=n2.next.next;  //n2->end
        }
        n2 = n1.next;//n2->right part first node
        n1.next=null; //min.next=null
        Node n3 = null;
        while (n2!=null){ //将右半部分反转，通过改变指针
            n3=n2.next; // n3表示n2右边的节点
            n2.next=n1; //将n2指向n1
            n1 = n2; //n1 右移
            n2 = n3; //n2 右移
        }
        n3=n1; //n3保存左半部分最后一个节点
        n2=head; // n1保存左半部分第一个节点
        boolean res=true;
        while (n1!=null &&n2!=null){  // check palindrome
            if(n1.value != n2.value){
                res=false;
                break;
            }
            n1=n1.next;//从开始到中间移动
            n2=n2.next;//从末尾到中间移动
        }
        //检查过后要将原来的链表恢复
        n1=n3.next;
        n3.next=null;
        while (n1!=null){ //反转右半部分的链表
            n2=n1.next;
            n1.next=n3;
            n3=n1;
            n1=n2;
        }
        return res;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }
}
