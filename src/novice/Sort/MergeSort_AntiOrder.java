package novice.Sort;

/**
 题目描述：逆序对的个数
 有一组数，对于其中任意两个数组，若前面一个大于后面一个数字，则这两个数字组成一个逆序对。请设计一个高效的算法，计算给定数组中的逆序对个数。

 给定一个int数组A和它的大小n，请返回A中的逆序对个数。保证n小于等于5000。
 */

/**
 * 思路：本题其实也是逆序排序的变形，只是merge时逆序对的累加条件和算法有所不同
 */
public class MergeSort_AntiOrder {
    public int mergeSort(int[] arr, int l, int r){
        if(l==r){   //当待排序数组长度为1时，递归开始回溯，进行merge操作
            return 0;
        }
        int mid=l+(r-l)/2;
        return mergeSort(arr,l,mid)+mergeSort(arr,mid+1,r)+merge(arr,l,mid,r);
    }

    public int merge(int[] arr, int left, int mid, int right){
        int[] help=new int[right-left+1]; //辅助空间O(N)
        int i=left;
        int j=mid+1;
        int index=0;
        int inverseNum=0;
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                help[index++]=arr[i++];
            }else{//此时存在逆序对
                inverseNum += (mid-i+1);//因为此时边要合并的数组已经是各自有序的了，即s[i] > s[j] 推出 s[i]...s[mid] > s[j]
                help[index++]=arr[j++];
            }
        }
        while (i<=mid){ //当j到right
            help[index++]=arr[i++];
        }
        while (j<right){ //当i到mid
            help[index]=arr[j+1];
        }
        for(int k=0;k<help.length;k++){
            arr[left++]=help[k];
        }
        return inverseNum;
    }

}
