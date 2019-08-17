package Sword;

public class DuplicationNumsInArray {

    public static boolean duplicate(int numbers[],int length,int [] duplication) {
        for(int i=0;i<length;i++)
        {
            while(numbers[i]!=i){
                if(numbers[i]==numbers[numbers[i]])
                {
                    duplication[0]=numbers[i];
                    return true;
                }

                swap(numbers,i,numbers[i]);
            }
        }
        return false;
    }

    public static void swap(int[] arr, int i, int j){
        int num=arr[i];
        arr[i] = arr[j];
        arr[j]  = num;
    }

    public static void main(String[] args){
        int[] arr={2,1,3,4,0};
        int[] duplication={-1};
        duplicate(arr,arr.length,duplication);
    }

    public boolean Find(int target, int [][] array) {
        int row=array.length;
        int i=0;
        int col=array[0].length;
        int j=col-1;
        int cur = array[i][j];
        while(i<row && j>=0){
            if(cur==target)
                return true;
            else if(cur<target){
                i++;
            }else{
                j--;
            }
        }
        return false;


    }
}
