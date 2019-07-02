package novice.tree;

/**
 * 前缀树
 *介绍前缀树
 * 何为前缀树? 如何生成前缀树?
 *  例子: 一个字符串类型的数组arr1，另一个字符串类型的数组arr2。
 *
 *  为什么要设计path和end两个变量
 *
 * 字母放在边上，path，end值放在节点上
 */
public class TrieTree {

    public static class TrieNode{
        public int path; // 表示多少单词经过这个节点
        public int end; //表示多少单词以这个节点结尾
        public TrieNode[] nexts;

        public TrieNode(){//初始化
            path=0;
            end=0;
            nexts=new TrieNode[26]; //假设固定只以26个字母作为结尾
        }
    }

    //构建一棵前缀树
    public static class Trie{
        private TrieNode root;


        //初始化
        public Trie(){
            root=new TrieNode();
        }

        //加入新的字符串
        public void insert(String word){
            if(word==null){
                return;
            }

            char[] chs=word.toCharArray();
            TrieNode node=root;
            int index=0;
            for(int i=0; i<chs.length; i++){
                index=chs[i]-'a';
                if(node.nexts[index]==null){
                    node.nexts[index]=new TrieNode();
                }
                node=node.nexts[index];
                node.path++; //属于中间路径的节点
            }
            node.end++; //到达结尾
        }

        //删除操作，先检测是否存在这个字符串，其他操作与插入了类似，但有一点不同
        public void delete(String word){
            if(search(word)!=0){
                char[] chs=word.toCharArray();
                TrieNode node=root;
                int index=0;
                for(int i=0; i<chs.length; i++){
                    index=chs[i]-'a';
                    //如果一个字符串当中的某个字符的path值已经为0，
                    //那么后面的也不用看了，直接指向null，return即可
                    if(--node.nexts[index].path==0){
                        node.nexts[index]=null;
                        return;
                    }
                    node=node.nexts[index];
                }
                node.end--;
            }
        }

        //查询某个字符串是否出现，并返回次数
        public int search(String word){
            if(word==null){
                return 0;
            }

            char[] chs=word.toCharArray();
            TrieNode node=root;
            int index=0;
            for(int i=0; i<chs.length; i++){
                index=chs[i]-'a';
                if(node.nexts[index]==null){ // 任何一个字符没找到就返回0
                    return 0;
                }
                node=node.nexts[index];
            }
            return node.end; //返回这个字符串出现的次数
        }


        //查询某个字符串的前缀数量
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.path;
        }


        public static void main(String[] args) {
            Trie trie = new Trie();
            System.out.println( trie.search("zuo"));
            trie.insert("zuo");
            System.out.println(trie.search("zuo"));
            trie.delete("zuo");
            System.out.println(trie.search("zuo"));
            trie.insert("zuo");
            trie.insert("zuo");
            trie.delete("zuo");
            System.out.println(trie.search("zuo"));
            trie.delete("zuo");
            System.out.println(trie.search("zuo"));
            trie.insert("zuoa");
            trie.insert("zuoac");
            trie.insert("zuoab");
            trie.insert("zuoad");
            trie.delete("zuoa");
            System.out.println(trie.search("zuoa"));
            System.out.println(trie.prefixNumber("zuo"));

        }
    }






}
