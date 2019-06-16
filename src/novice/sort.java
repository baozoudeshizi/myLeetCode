package novice;

public class sort {
    //冒泡排序
    public static void bubbleSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int end=arr.length-1; end>0; end--){
            for(int i=0;i<end;i++){
                if(arr[i]>arr[i+1]){
                    swap(arr,i,i+1);
                }
            }
        }
    }

    //选择排序
    public static void selectionSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int i=0; i<=arr.length-1; i++){
            int minIndex=i;
            for(int j=i+1; j<arr.length;j++){
                minIndex=arr[j]<arr[minIndex]?j:minIndex;
            }
            swap(arr,i,minIndex);
        }
    }

    //插入排序
    public static  void insertionSort(int[] arr){
        if(arr==null || arr.length<2){
            return ;
        }
        for(int i=1; i<arr.length; i++){
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
