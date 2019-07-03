package novice.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * 随时找到数据流的中位数
 *
 * 【题目】 有一个源源不断地吐出整数的数据流，假设你有足够的空间来 保存吐出的数。
 * 请设计一个名叫MedianHolder的结构， MedianHolder可以随时取得之前吐出所有数的中位数。
 *
 * 【要求】 1.如果MedianHolder已经保存了吐出的N个数，那么任意时刻 将一个新数加入到MedianHolder的过程，
 * 其时间复杂度是 O(logN)。
 * 2.取得已经吐出的N个数整体的中位数的过程，时间复杂度为 O(1)。
 *
 * 解题思路：分别设计一个大根堆，一个小根堆。大根堆中保存较小的一半数，小根堆中保留较大的一半数。
 * 另外要有一个调节两边数量的方法，保持两边值的数量的差值不超过1
 * 时间复杂度为O(logN)想到用堆来调整，因为不管是大根堆还是小根堆，往其中加入一个新的数，以及调整堆的代价都是O(logN)
 */

public class MedianQuick {

    public static class MedianHolder{
        //优先队列的本质就是堆，可以自动调整堆的结构，但是我们需要建立比较器来实现大根堆还是小根堆
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new MaxHeapComparator());
        private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(new MinHeapComparator());

        private  void modifyTwoHeapSize(){
            if(this.maxHeap.size() == this.minHeap.size() +2 ){
                this.minHeap.add(this.maxHeap.poll());
            }
            if(this.minHeap.size() == this.maxHeap.size()+2){
                this.maxHeap.add(this.minHeap.poll());
            }
        }

        //加入数据要考虑当前值和最大堆的堆顶进行比较
        public void addNumber(int num){
            if(this.maxHeap.isEmpty()){
                this.maxHeap.add(num); //初始情况，最大堆里面没有值
                return;
            }
            if(this.maxHeap.peek()>=num){
                this.maxHeap.add(num);
            }else{
                if(this.minHeap.isEmpty()){
                    minHeap.add(num);
                    return;
                }
                if (this.minHeap.peek()>num){ //这个地方是否需要做这个判断，是否可以直接加入minHeap中，因为最后都要调整的
                    this.maxHeap.add(num);
                }else{
                    this.minHeap.add(num);
                }
            }
            modifyTwoHeapSize();
        }

        public Integer getMedian(){
            int maxHeapSize = this.maxHeap.size();
            int minHeapSize = this.minHeap.size();
            if(maxHeapSize+minHeapSize==0){
                return null;
            }
            Integer maxHeapHead=this.maxHeap.peek();
            Integer minHeapHead=this.minHeap.peek();
            if(((maxHeapSize+minHeapSize)&1)==0){ //偶数
                return (maxHeapHead+minHeapHead)/2; //当为偶数的时候，中位数是中间两数的平均值
            }else
            {
                return maxHeapSize>minHeapSize ? maxHeapHead : minHeapHead; //奇数情况下
            }
        }

    }

    //生成大根堆的比较器
    public static class MaxHeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    }

    //生成小根堆的比较器
    public static class MinHeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    }

    // for test
    public static int[] getRandomArray(int maxLen, int maxValue) {
        int[] res = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue);
        }
        return res;
    }

    // for test, this method is ineffective but absolutely right
    public static int getMedianOfArray(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        int mid = (newArr.length - 1) / 2;
        if ((newArr.length & 1) == 0) {
            return (newArr[mid] + newArr[mid + 1]) / 2;
        } else {
            return newArr[mid];
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        boolean err = false;
        int testTimes = 200000;
        for (int i = 0; i != testTimes; i++) {
            int len = 30;
            int maxValue = 1000;
            int[] arr = getRandomArray(len, maxValue);
            MedianHolder medianHold = new MedianHolder();
            for (int j = 0; j != arr.length; j++) {
                medianHold.addNumber(arr[j]);
            }
            if (medianHold.getMedian() != getMedianOfArray(arr)) {
                err = true;
                printArray(arr);
                break;
            }
        }
        System.out.println(err ? "Oops..what a fuck!" : "today is a beautiful day^_^");

    }
}
