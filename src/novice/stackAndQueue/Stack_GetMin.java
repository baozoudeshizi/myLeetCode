package novice.stackAndQueue;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Stack;

/**
 * 设计一个有getMin功能的栈
 * 思路：设计两个栈，一个用来保存所有数据，标记为stackData；另一个用来保存每一步的最小值，标记为stackMin
 */

public class Stack_GetMin {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public Stack_GetMin(){
        stackData = new Stack<Integer>();
        stackMin =  new Stack<Integer>();
    }

    public void push(int  newNum){
        if(this.stackMin.isEmpty()){
            stackMin.push(newNum);
        }else if(newNum < this.getMin()){//最小的值一直保存在stackMin栈中
            this.stackMin.push(newNum);
        }
        this.stackData.push(newNum);//无论大小，stackData都要压入
    }

    public int pop(){
        if(this.stackData.isEmpty()){
            throw new RuntimeException("You stack is empty.");
        }
        int value=this.stackData.pop();
        if(value==this.getMin()){
            this.stackMin.pop();
        }
        return value;
    }


    public int getMin(){
        if(this.stackMin.isEmpty()){
            throw new RuntimeException("You stack is empty.");
        }
        return this.stackMin.peek();
    }


}
