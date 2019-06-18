package novice.Sort;
import java.util.Arrays;


/**
 * 堆排序的基本思想是：
 * 将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就为最大值。
 * 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了
 * 时间复杂度为O(N*logN)，空间复杂度为O(1);如果只是建立堆则是O(N)
 *
 * 主要过程：构建初始堆+交换堆顶元素和末尾元素并重建堆两部分组成
 *
 *
 * 堆排序，首先应该明确的是，堆可以看成一个完全二叉树，但我们存储的其实是数组，而非树。那么就要了解如何把树转换成数组，其中存在这样的关系：
 * 左孩子：2*i+1; 右孩子：2*i+2; 父节点是:(i-1)/2
 * 大根堆：所有的数都是父节点最大； 小根堆：所有的树都是父节点最小
 *
 * 以大根堆为例，如何从无到有建立一个大根堆呢？
 * 其实就是按照数组坐标的计算公式查找根，并与之相比，如果比根大，就进行交换。
 *
 * 细节可以再看这个博客：https://www.cnblogs.com/chengxiao/p/6129630.html
 */
public class HeapSort {
    public static void heapSort(int[] arr){
        if(arr==null || arr.length<2){
            return;
        }

        for(int i=0; i<arr.length; i++){
            heapInsert(arr,i);//调整数组变成大根堆
        }

        int size=arr.length;
        swap(arr, 0 , --size);//这里就是找到最大的，最大的不需要调整了，堆的范围变成size-1
        while(size>0){
            heapify(arr,0,size);
            swap(arr,0,--size);
        }
    }

    //调整数组成大根堆，其实就是不断比较新插入的节点与其父节点的值，若大于父节点，则向上调整，同时改变其index值；并一直向上找去
    public static void heapInsert(int[] arr, int index){
        while (arr[index]>arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index=(index-1)/2;//来到父位置，继续比较
        }
    }


    //经过构造成大顶堆后，只能得到顶点是最大值，最大值与最末端的值交换后，需要再次调整堆的结构
    //这个其实是个不断往下沉的过程
    public static void heapify(int[] arr, int index, int size){
        int left=index*2;
        while (left<size){
            int largest=(left+1<size && arr[left+1]>arr[left])?left+1:left;//先比较左右孩子谁大，确保右孩子left+1不越界
            largest=arr[largest]>arr[index]?largest:index;//拿左右孩子大的与父节点比较
            if(largest==index){
                break;
            }
            swap(arr,largest,index);//largest!=index
            index=largest;
            left=index*2+1;
        }
    }

    public static void swap(int[] arr, int i, int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
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
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }


}
