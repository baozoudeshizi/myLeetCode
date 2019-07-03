package novice.sort;


/**
 * 经典快排：选择最后一个最为划分的标准，分成大于和小于两个部分，然后大于和小于的部分再分别取递归
 * 随机快排：当使用最后一个最为划分标准的时候，就跟原始的数据情况相关，有可能会出现比较差的结果，所以用数组当中的值与最后一个值交换，
 * 长期下来的期望就是O(N*logN)，而因为要新建数组保存等于部分的边界，所以额外空间为O(logN)
 *
 * 分析：其实快排是1以partition作为核心，而荷兰国国旗可以改善这个partition问题
 */
public class QuickSort {
    public  static  void quickSort(int[] arr){
        if(arr==null || arr.length<2){
            return;
        }
        quickSort(arr,0,arr.length-1);
    }

    public static void quickSort(int[] arr, int l, int r){
        if(l<r){
            swap(arr,l+(int)(Math.random()*(r-l+1)),r);
            int[] p=partition(arr,l,r);
            quickSort(arr,l,p[0]-1);
            quickSort(arr,p[1]+1,r);
        }
    }

    //默认最后一个数最为划分，返回等于部分的范围
    public static int[] partition(int[] arr, int l, int r){
        int less=l-1;
        int more=r;
        int cur=l;
        while (cur<more){
            if(arr[cur]<arr[r]){
                swap(arr,++less,cur++);
            }else if(arr[cur]>arr[r]){
                swap(arr,--more,l);
            }else{
                l++;
            }
        }
        swap(arr,more,r);//让最后一个位置和more的第一个位置交换,因为一开始最后一个位置是不参与划分的，最后要归为到等于的部分，也就是more的前一个位置
        return  new int[]{less+1, more};
    }

    //经典快排的partition
    public static int partition1(int[] arr,int l,int r) {
        //返回part,使arr[l+1,..j]<v,arr[j+1,...i]>v
        int pivot = arr[l];
        while(l<r) {
            while(l<r && arr[r]>=pivot) {
                r--;
            }
            swap(arr,l,r);
            while(l<r && arr[l]<=pivot) {
                l++;
            }
            swap(arr,l,r);
        }
        return l;
    }


    public static void swap(int[] arr, int i, int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }


}
