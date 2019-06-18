package novice.Sort;

/**
 * 时间复杂度O(N*logN)，额外空间复杂度O(N)
 * 原理：将数组一分为二，用递归的思想，左边mergeSort，右边mergerSort，最后整体进行merge。
 * 需要写的代码就是merge的部分，其他用递归即可
 */
public class mergeSort {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);//防止数组越界，右移1，即时除以2
        mergeSort(arr, l, mid);//左边递归
        mergeSort(arr, mid + 1, r);//右边递归
        merge(arr, l, mid, r);//整体merge
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1]; //建立一个外部数组，利用外排
        int i = 0;
        int p1 = l; //左边数组的索引
        int p2 = m + 1; //右边数组的索引
        while (p1 <= m && p2 <= r) { //各自的索引没到达其数组右边界
            // 数组中的值谁小就移动谁的索引
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) { // 当p2跑到结尾，将左数组中剩余的值全部加入help
            help[i++] = arr[p1++];
        }
        while (p2 <= r) { // 当p1跑到结尾，将左数组中剩余的值全部加入help
            help[i++] = arr[p2++];
        }
        // 因为返回的是原来的arr，所以将help中的值都转入arr
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    public static void main(String[] args) {
        int[] arr={1,3,4,2,4};
        mergeSort(arr,0,arr.length-1);
    }
}


/*
剖析递归行为和递归行为时间复杂度的估算 一个递归行为的例子
master公式的使用
        T(N) = a*T(N/b) + O(N^d)
        1) log(b,a) > d -> 复杂度为O(N^log(b,a))
        2) log(b,a) = d -> 复杂度为O(N^d * logN)
        3) log(b,a) < d -> 复杂度为O(N^d)

 */


