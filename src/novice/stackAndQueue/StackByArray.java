package novice.stackAndQueue;

import sun.rmi.server.InactiveGroupException;

/**
 * 用固定数组实现栈
 */
public class StackByArray {
    private Integer[] arr;
    private Integer index;//为什么不用int

    public StackByArray(int initSize){
        if(initSize<0){
            throw new IllegalArgumentException("The init index is less than 0.");
        }
        arr=new Integer[initSize];//定义一个数组
        index=0;
    }

    //返回栈顶
    public Integer peek(){
        if(arr.length==0){
            return null;
        }
        return arr[index-1];
    }

    //压栈
    public void push(int obj){
        if(index==arr.length){
            throw new ArrayIndexOutOfBoundsException("The stack if full");
        }
        arr[index++]=obj;
    }

    //从栈中弹出
    public Integer pop(){
        if(index==0){
            throw new ArrayIndexOutOfBoundsException("The stack is empty");
        }
        return arr[--index];
    }

}

