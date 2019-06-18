package novice.Sort;

public class sort {
    //冒泡排序
    //时间复杂度O(N^2)，额外空间复杂度O(1)
    //思路：相邻元素两两比较，大的往后放，第一次完毕，最大值出现在了最大索引处
    public static void bubbleSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int end=arr.length-1; end>0; end--){//从右往左
            for(int i=0;i<end;i++){
                if(arr[i]>arr[i+1]){
                    swap(arr,i,i+1);
                }
            }
        }
    }

    //选择排序
    //时间复杂度O(N^2)，额外空间复杂度O(1)
    //思路：index从0开始往右，从index+1到最后，寻找最小的值的坐标，跟index交换
    public static void selectionSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int i=0; i<=arr.length-1; i++){//当前位置i，和后面最小的值交换位置
            int minIndex=i;
            for(int j=i+1; j<arr.length;j++){
                minIndex=arr[j]<arr[minIndex]?j:minIndex;
            }
            swap(arr,i,minIndex);
        }
    }

    //插入排序
    //时间复杂度O(N^2)，额外空间复杂度O(1)
    //思路：想想抓牌的顺序，从0索引开始，依次和后面元素比较，小的往前放，第一次完毕，最小值出现在了最小索引处
    public static  void insertionSort(int[] arr){
        if(arr==null || arr.length<2){
            return ;
        }
        for(int i=1; i<arr.length; i++){//当前i位置的数，往前面0~i-1有序位置中插入
            for(int j=i-1; j>=0; j--){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    public static  void swap(int[] arr, int i, int j){
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }

}
