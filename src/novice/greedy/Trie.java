package novice.greedy;

class TrieNode{
    public int path; //表示有多少单词经过这个节点
    public int end; // 表示有多少单词以这个字符串结尾
    public TrieNode[] map;//key表示该路径上的字符，value表示字符路径指向的节点

    public TrieNode(){
        path=0;
        end=0;
        map = new TrieNode[26];//假设只有26个字母
    }

}

public class Trie {
    private TrieNode root;

    public Trie(){
        root=new TrieNode();
    }

    //插入
    public void insert(String word){
        if(word==null){
            return;
        }
        char[] chs=word.toCharArray();
        TrieNode node=root;//当前节点为根节点
        node.path++;
        int index=0;
        for(int i=0;i<chs.length;i++){
            index=chs[i]-'a';
            if(node.map[index]==null){
                node.map[index]=new TrieNode();//如果之前没有这个节点的话，就新建
            }
            node=node.map[index];
            node.path++;
        }
        node.end++;
    }
    //寻找是否有字符串加入
    public boolean search(String word){
        if(word==null){
            return false;
        }
        char[] chs=word.toCharArray();
        TrieNode node=root;
        int index=0;
        for(int i=0; i<chs.length; i++){
            index=chs[i]-'a';
            if(node.map[index]==null){
                return false;
            }
            node=node.map[index];
        }
        return  node.end!=0;
    }


    public int prefixNumber(String pre){
        if(pre==null){
            return 0;
        }
        char[] chs=pre.toCharArray();
        TrieNode node=root;
        int index=0;
        for(int i=0; i<chs.length; i++){
            index=chs[i]-'a';
            if(node.map[index]==null){
                return 0;
            }
            node=node.map[index];
        }
        return node.path;
    }


    //删除,先检查是否存在，如果存在则遍历该word。把遍历过的节点的path值依次-1，如果为0，则后面节点都不要了
    public void delete(String word){
        if(search(word)){
            char[] chs=word.toCharArray();
            TrieNode node=root;
            node.path++;
            int index=0;
            for(int i=0; i<chs.length;i++){
                index=chs[i]-'a';
                if(node.map[index].path--==1){
                    node.map[index]=null;
                    return;
                }
                node=node.map[index];
            }
            node.end--;
        }
    }



    public static void main(String[] args) {
        Trie trie = new Trie();
        //System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        //System.out.println(trie.search("zuo"));
        //trie.delete("zuo");
        //System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        trie.insert("zuo");
        //trie.delete("zuo");
        //System.out.println(trie.search("zuo"));
       // trie.delete("zuo");
        //System.out.println(trie.search("zuo"));
        trie.insert("zuoa");
        trie.insert("zuoac");
        trie.insert("zuoab");
        trie.insert("zuoad");
        //trie.delete("zuoa");
        //System.out.println(trie.search("zuoa"));
        //System.out.println(trie.prefixNumber("zuo"));

    }
}
