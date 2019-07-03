package novice.union;

//并查集的结构
/**
 * 给定一个没有重复值的整型数组arr，初始时认为arr中每一个数各自都是一个单独的集合。请设计UnionFind结构，提供以下两个操作
 * 1. boolean isSameSet(int a, int b) 判断a，b这两个数是否属于同一个集合
 * 2. void union(int a, int b) 把a所在的集合与b所在的集合合并在一起，原本两个集合各自的元素以后都算作同一个集合
 * 
 * 单次调用isSameSet或union方法的平均时间复杂度为O(1）
 */

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
        public HashMap<Node, Integer> rankMap;//Interger是Node所在集合的节点个数，标记为秩
        //public UnionFindSet() {
        //	fatherMap = new HashMap<Node, Node>();
        //	rankMap = new HashMap<Node, Integer>();
        //}
        //可以将上面注释部分改成如下，表示只接受初始化时候传过来的List,初始化两个表则改到makeSet函数中，同时将makeSet函数改为private
        public UnionFindSet(List<Node> nodes){
            makeSets(nodes);
        }

        private void makeSets(List<Node> nodes) {
            fatherMap =  new HashMap<Node, Node>();
            rankMap =  new HashMap<Node, Integer>();
            fatherMap.clear();
            rankMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);//初始情况，每一个node自己形成一个集合，该node就是该集合的代表节点
                rankMap.put(node, 1);
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
                int aSetSize= rankMap.get(aHead);
                int bSetSize = rankMap.get(bHead);
                if (aSetSize <= bSetSize) {//短的挂长的下面
                    fatherMap.put(aHead, bHead);
                    rankMap.put(bHead, aSetSize + bSetSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    rankMap.put(aHead, aSetSize + bSetSize);
                }
            }
        }

    }

    public static void main(String[] args) {
    }

}
