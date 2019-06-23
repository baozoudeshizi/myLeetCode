package novice.Sort;

/*
题目描述：
数组小和的定义如下：例如，数组s=[1,3,5,2,4,6]
在s[0]的左边小于或等于s[0]的数的和为0
在s[1]的左边小于或等于s[1]的数的和为1
在s[2]的左边小于或等于s[2]的数的和为1+3=4
在s[3]的左边小于或等于s[3]的数的和为1
在s[4]的左边小于或等于s[4]的数的和为1+3+2=6
在s[5]的左边小于或等于s[5]的数的和为1+3+5+2+4=15
所以s的小和为0+1+4+1+6+15=27
给定一个数组s，实现函数返回s的小和。
 */

/**
 * 思路：这题其实可以看做merge排序的变形，在对有序子数组进行merge的同时，累加数组小和，时间复杂度O(nlogn)
 */
public class MergeSort_SamllSum {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return mergeSort(arr, 0, arr.length - 1);
    }

    public static int mergeSort(int[] arr, int l, int r) {
        if (l == r) {// 当待排序数组长度为1时，递归开始回溯，进行merge操作
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return mergeSort(arr, l, mid) + mergeSort(arr, mid + 1, r) + merge(arr, l, mid, r);
    }


    /**
     * 合并两个已排好序的数组s[left...mid]和s[mid+1...right]
     * @param arr
     * @param l
     * @param m
     * @param r
     * @return 返回合并过程中累加的数组小和
     */
    public static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1]; //辅助空间O(N)
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int res = 0;
        // 当前一个数组元素小于或等于后一个数组元素时，累加小和.
        //这里注意，最开始计算的时候，数组是递归到最小的情况，即1，3
        while (p1 <= m && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
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
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
//        int testTime = 500000;
//        int maxSize = 100;
//        int maxValue = 100;
//        boolean succeed = true;
//        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr2 = copyArray(arr1);
//            if (smallSum(arr1) != comparator(arr2)) {
//                succeed = false;
//                printArray(arr1);
//                printArray(arr2);
//                break;
//            }
//        }
//        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
        int[] arr={3,1,5,2,4,6};
        System.out.println(smallSum(arr));
    }

}

