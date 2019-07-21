package novice.tree;

/**
 * 判断两个一个树是否是另外一个树的完全子树
 *
 * 思路：分成两步
 * 1. 将t1,t2树按照先序遍历的方式序列化
 * 2. 使用KMP算法来进行比较
 */
public class IsCompleteSubTree {

    class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public boolean isSubtree(Node t1,Node t2){
        String t1Str = serialByPre(t1);
        String t2Str = serialByPre(t2);
        return getIndexOf(t1Str,t2Str) !=-1;
    }


    //按照先序遍历序列化
    public String serialByPre(Node head){
        if(head == null){
            return "#!";
        }
        String res=head.value+"!";
        res+=serialByPre(head.left);
        res+=serialByPre(head.right);
        return res;
    }

    //KMP部分
    public int getIndexOf(String s, String m){
        if(s==null || m==null || m.length()<1 || s.length()<m.length()){
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si=0;
        int mi=0;
        int[] next = getNextArray(ms);
        while(si<ss.length && mi<ms.length){
            if(ss[si]==ms[mi]){
                si++;
                mi++;
            }else if(next[mi]==-1){
                si++;
            }else{
                mi=next[mi];
            }
        }
        return mi == ms.length?si-mi:-1;
    }

    public int[] getNextArray(char[] ms){
        if(ms.length==1){
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0]=-1;
        next[1]=0;
        int pos=2;
        int cn=0;
        while (pos<next.length){
            if(ms[pos-1]==ms[cn]){
                next[pos++] = ++cn;
            }else {
                if(cn>0){
                    cn=next[cn];
                }else{
                    next[pos++]=0;
                }
            }
        }
        return next;
    }
}
