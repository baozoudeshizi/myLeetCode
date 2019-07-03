package novice.sort;

import java.util.Arrays;

/**
 * 题目描述：给定一个数组，求如果排序之后，相邻两数的最大差值，要求时间复杂度O(N)，且不能用非基于比较的排序。
 *
 * 思想：这道题其实是用桶的思维来解决
 * 假设数组有N个值，那个设计N+1个桶。将数组最大值-最小值，然后将数组等分
 * 设置布尔值看是否为空桶，并分别获得每个桶的最大值和最小值
 * 如果为空桶，则获得前后非空桶的最大最小值；如果为非空桶，则返回当前桶内的最大最小值；并且如果当前桶如果之前没有值，则要设置hasNum为true
 *
 *
 * 思考一下，为什么最大值不会出现在某个桶中？
 * 因为桶数跟数组的长度相同，所以每个桶如果放一个数，那么最大差值肯定是前后两个桶之间的差值；
 * 如果有的是空桶，那空桶前后的差值肯定是大于单独一个桶内。
 * 但是最大差值不一定一定出现在空桶前后，譬如一个桶的空间是10，则空桶前后最小的差值有可能是11，而相邻的非空桶，最大差值可能是19
 */

public class BucketSort_MaxGap {
    public static int maxGap(int[] nums){
        if(nums==null || nums.length<2){
            return 0;
        }
        int len=nums.length;
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;

        for(int i=0; i<len; i++){//获得整个数组的最大最小值
            min=Math.min(min,nums[i]);
            max=Math.max(max,nums[i]);
        }
        if(min==max){//整个数组的值都相等
            return 0;
        }

        boolean[] hasNum = new boolean[len+1];//默认是false
        int[] maxs=new int[len+1];
        int[] mins=new int[len+1];
        int bid=0;//桶的序号id
        for(int i=0;i<len;i++){//获得所有桶的最大最小值
            bid=bucket(nums[i],len,min,max);
            mins[bid]=hasNum[bid] ? Math.min(mins[bid],nums[i]) : nums[i];//第一次时hasNum为false，mins[bid]=nums[i]
            maxs[bid]=hasNum[bid] ? Math.max(maxs[bid],nums[i]) : nums[i];
            hasNum[bid] = true;
        }

        int res=0;
        int lastMax=maxs[0];//前一个桶的最大值，初值为第一个桶的最大值
        for(int i=1; i<=len;i++){
            if(hasNum[i]){
                res=Math.max(res,mins[i]-lastMax);//下一个桶的最小值-前一个桶的最大值
                lastMax=maxs[i];
            }
        }
        return res;
    }

    //确定将num值放入哪个桶，返回桶的位置
    public static int bucket(long num, long len, long min, long max){
        return (int)((num-min)*len/(max-min));
    }


    // for test
    public static int comparator(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (maxGap(arr1) != comparator(arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
