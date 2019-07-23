package novice.linkedList;

public class ReversePartList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node reversePart(Node head, int from, int to) {
        int len = 0;
        Node node1 = head;
        Node fPre = null; //from的前一个节点
        Node tPos = null; //to的后一个节点
        while (node1 != null) {
            len++;
            fPre = len == from - 1 ? node1 : fPre; //如果在len++的过程中，找到了from前一个，那就将pre定为此时累加到的这个节点
            tPos = len == to + 1 ? node1 : tPos;   //同理，如果在len++过程中找到了to后面的一个，就赋值为当前点，否则均为null
            node1 = node1.next;
        }
        if (from > to || from < 1 || to > len) {
            return head;
        }
        node1 = fPre == null ? head : fPre.next;  //看是否需要换头，如果pre==null的时候，头结点要换了，就是从原头开始换
        Node node2 = node1.next;
        node1.next = tPos;  //将原来的头连至to+1
        Node next = null;
        while (node2 != tPos) {  //下一位依次指向前
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }
        if (fPre != null) {  //如果pre存在的时候，接到pre（from-1）后面
            fPre.next = node1;
            return head;
        }
        return node1; //否则不用拼接，直接是倒转之后的新头结点
    }
}
