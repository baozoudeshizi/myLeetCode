package novice.greedy;

/**
 * 题目描述：一块金条切成两半，是需要花费和长度数值一样的铜板的。比如 长度为20的 金条，不管切成长度多大的两半，都要花费20个铜 板。一群人想整分整块金条，
 * 怎么分最省铜板? 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为 10+20+30=60. 金条要分成10,20,30三个部分。
 * 如果， 先把长 度60的金条分成10和50，花费60 再把长度50的金条分成20和30， 花费50 一共花费110铜板。但是如果先把长度60的金条分成30和30，花费60，
 * 再把长度30 金条分成10和20，花费30 一共花费90铜板。 输入一个数组，返回分割的最小代价。
 *
 * 这道题其实就是一个哈夫曼编码问题，这里用优先队列来解决的。优先队列的最大特点就是有序，至于是顺序还是逆序，我们可以通过实现比较器来实现。
 * 至于优先队列如何实现的？需要继续查询资料
 */
import java.util.Comparator;
import java.util.PriorityQueue;

public class LessMoney {
    public static int lessMoeny(int[] arr){
        PriorityQueue<Integer> pQ=new PriorityQueue<>();
        for(int i=0;i<arr.length;i++){
            pQ.add(arr[i]);
        }
        int sum=0;
        int cur=0;
        while(pQ.size()>1){
            cur=pQ.poll()+pQ.poll();
            sum+=cur;
            pQ.add(cur);
        }
        return sum;
    }

    public static class MinheapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2){
            return o1-o2;
        }
    }

    public static class MaxheapComparator implements  Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    }


    public static void main(String[] args){
        int[] arr={6,2,8,4};
        System.out.println(lessMoeny(arr));

        int[] arrForHeap={3,5,2,7,0,1,6,4};

        //最小堆，默认情况
        PriorityQueue<Integer> minQ1 = new PriorityQueue<>();
        for(int i=0; i<arrForHeap.length; i++){
            minQ1.add(arrForHeap[i]);
        }
        while(!minQ1.isEmpty()){
            System.out.print(minQ1.poll()+" ");
        }
        System.out.println();


        //最小堆，用比较器
        PriorityQueue<Integer> minQ2 = new PriorityQueue<>(new MinheapComparator());
        for(int i=0; i<arrForHeap.length;i++){
            minQ2.add(arrForHeap[i]);
        }
        while(!minQ2.isEmpty()){
            System.out.print(minQ2.poll()+" ");
        }
        System.out.println();

        //最大堆，用比较器
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(new MaxheapComparator());
        for(int i=0; i<arrForHeap.length;i++){
            maxQ.add(arrForHeap[i]);
        }
        while(!maxQ.isEmpty()){
            System.out.print(maxQ.poll()+" ");
        }
        System.out.println();
    }
}





