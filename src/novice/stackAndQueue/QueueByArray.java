package novice.stackAndQueue;

import org.omg.CORBA.INTERNAL;

/**
 * 用数组实现一个队列
 * 这里需要注意的是，队头队尾其实都是往右走，所以都是增大
 */

public  class QueueByArray {
    Integer[] arr;
    Integer begin;
    Integer end;
    Integer size;
    public QueueByArray(int initSize){
        if(initSize<0){
            throw new IllegalArgumentException("The init size is less than 0.");
        }
        arr=new Integer[initSize];
        begin=0;
        end=0;
        size=0;
    }

    public Integer peek(){
        if(size==0){
            return null;
        }
        return arr[begin];
    }

    public void push(int obj){
        if(size==arr.length){
            throw new ArrayIndexOutOfBoundsException("The queue is full.");
        }
        size++;
        //下面两行代码不这么写arr[end++]=obj;因为要考虑新加入的obj可能到达了数组末尾。所以end要单独拿出来处理
        arr[end]=obj;
        end=end==arr.length-1?0:end+1;
    }

    public Integer poll(){
        if(size==0){
            throw new ArrayIndexOutOfBoundsException("The queue is empty.");
        }
        size--;
        int tmp=begin;
        begin = begin == arr.length-1 ? 0 : begin+1;
        return arr[tmp];
    }
}
