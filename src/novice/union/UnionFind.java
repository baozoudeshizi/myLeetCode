package novice.union;

//并查集的结构

import java.util.HashMap;
import java.util.List;

/**
 *
 * 并查集结构：用法很难，结构简单
 * 应用场合：非常快的检查两个元素是否属于一个集合 ;将两个元素各自所在的集合合并
 * 并查集是如何设计的？
 * 代表节点（自己指向自己）用来代表集合。短的链挂在链长的下面。
 * 优化：将“长链”变成扁平，都指向代表节点。
 */
public class UnionFind {

    public static class Node {
        // whatever you like 这个节点可以是任何类型
    }

    public static class UnionFindSet {
        public HashMap<Node, Node> fatherMap;//node分别是child和father
        public HashMap<Node, Integer> sizeMap;//Interger是Node所在集合的节点数
        //public UnionFindSet() {
        //	fatherMap = new HashMap<Node, Node>();
        //	sizeMap = new HashMap<Node, Integer>();
        //}
        //可以将上面注释部分改成如下，表示只接受初始化时候传过来的List,初始化两个表则改到makeSet函数中，同时将makeSet函数改为private
        public UnionFindSet(List<Node> nodes){
            makeSets(nodes);
        }

        private void makeSets(List<Node> nodes) {
            fatherMap =  new HashMap<Node, Node>();
            sizeMap =  new HashMap<Node, Integer>();
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);//初始情况，每一个node自己形成一个集合，该node就是该集合的代表节点
                sizeMap.put(node, 1);
            }
        }

        //找头，也就是找代表节点，用递归方式
        private Node findHead(Node node) {
            Node father = fatherMap.get(node);
            if (father != node) {
                father = findHead(father);
            }
            fatherMap.put(node, father);//将所有node都指向代表节点，并不仅仅是代表节点，因为这是在递归代码内
            return father;
        }

        //功能一，查找两个元素所在集合是否相同
        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }
        //功能二：将a，b两个元素所在的集合合并
        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if (aHead != bHead) {
                int aSetSize= sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if (aSetSize <= bSetSize) {//短的挂长的下面
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                }
            }
        }

    }

    public static void main(String[] args) {
    }

}
