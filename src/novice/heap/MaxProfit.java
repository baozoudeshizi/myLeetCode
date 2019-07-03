package novice.heap;

/*
* 题目描述：
* 给定两个整数W和K，W代表你拥有的初始资金，K代表你最多可以做K个项目。
* 再给定两个长度N的正整数组costs[]和profits[],代表一共有N个项目，
* costs[i]表示i号项目的花费 profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
* 输入: 参数1，正数数组costs 参数2，正数数组profits 参数3， 正数k 参数4，正数m
* k表示你不能并行、只能串行的最多做k个项目，m表示你初始的资金
* 说明:你每做完一个项目，马上获得的收益，可以支持你去做下一个项目，必须要大于启动资金才能做这个项目。
* 输出: 你最后获得的最大钱数。
*
* 思路：建立一个最大堆和一个最小堆，分别用来放收益和代价。利用优先队列+比较器实现
*
* 1.生成小根堆costMinHeap，把所有项目放进堆中，依据cost排序，花费最小的放在堆顶
* 2.生成大根堆profitMaxHeap，遍历从小根堆costMinHeap中弹出cost<W的项目，并放入大根堆中，直到costMinHeap为空或者剩下项目的花费都大于W
*   弹出过程才能停止
*
*/

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxProfit {
    public static class Program{
        public int p; //收益
        public int c; //花费

        public Program(int p, int c){
            this.p=p;
            this.c=c;
        }
    }

    public static class MinCostComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.c-o2.c;
        }
    }

    public static class MaxProfitComparator implements  Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o2.p-o1.p;
        }
    }

    public static int findMaximizedCaptal(int k, int W, int[] Profits, int[] Capital){
        Program[] Programs= new Program[Profits.length];
        for(int i=0;i<Profits.length;i++){
            Programs[i]=new Program(Profits[i],Capital[i]);
        }

        //小根堆，花费最小的在顶
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        //大根堆，利润最大的在顶，
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for(int i=0; i<Programs.length; i++){
            minCostQ.add(Programs[i]);
        }
        //依次做K个项目
        for(int i=0;i<k;i++){
            //当前资金为W，在costMinHeap中花费小于W的项目，都可以考虑
            while(!minCostQ.isEmpty()&&minCostQ.peek().c<=W){
                //把可以考虑的项目都放进利润大根堆中
                maxProfitQ.add(minCostQ.poll());
            }
            //如果利润大根堆为空，说明可以考虑的项目为空，直接范围W
            if(maxProfitQ.isEmpty()){
                return W;
            }
            W+=maxProfitQ.poll().p;
        }
        return W;
    }


}
