package novice.tree;

//折纸问题
// 【题目】 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时 折痕是凹下去的，
// 即折痕突起的方向指向纸条的背面。如果从纸条的下边向上方连续对折2 次，压出折痕后展开，此时有三条折痕，
// 从上到下依次是下折痕、下折痕和上折痕。给定一个输入参数N，代表纸条都从下边向上方连续对折N次，请从上到下打印所有折痕的方向。
// 例如:N=1时，打印:
//        down
//        N=2时，打印:
//        down
//        down
//        up

/**
 * 规律：第i+1次折痕过程，就是在i次产生的的每一条这和的左右两侧依次插入下折痕和上折痕
 * 所以，所有的折痕的结构是一个满二叉树，头节点为下折痕，每一棵左子树的头结点为下折痕，每一棵右子树的头结点为上折痕
 * 这道题竟然是一个二叉树问题，所以打印折痕方向的问题，就是就是中序遍历的过程
 */
public class PaperFolding {

    public static void printAllFolds(int N){
        printProcess(1,N,true);
    }

    //i表示第几层，N是固定的
    public static void printProcess(int i, int N, boolean down){
        if(i>N){
            return;
        }
        //打印左子树，左子树的头结点为下折痕
        printProcess(i + 1, N, true);
        System.out.println(down ? "down " : "up ");
        //打印右子树，右子树的头结点为上折痕
        printProcess(i + 1, N, false);

        //对比一下中序遍历的代码
//        public static void inOrderRecur(PreInPosTravelsal.Node head){
//            if(head==null){
//                return;
//            }
//            inOrderRecur(head.left);
//            System.out.print(head.value+" ");
//            inOrderRecur(head.right);
//
//        }
    }

    public static void main(String[] args) {
        int N = 4;
        printAllFolds(N);
    }

}
