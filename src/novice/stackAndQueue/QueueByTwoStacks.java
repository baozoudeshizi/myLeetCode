package novice.stackAndQueue;

/**
 * 用栈实现队列
 * 思路：用连个栈，一个做为push，一个做为pop。全部倒过去即可
 */

import java.util.Stack;

public class QueueByTwoStacks {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public QueueByTwoStacks(){
        stackPush = new Stack<Integer>();
        stackPop = new Stack<Integer>();
    }

    public void push(int pushInt){
        stackPush.push(pushInt);
    }

    public int peek(){
        if(stackPush.isEmpty() && stackPop.isEmpty()){
            throw new RuntimeException("Queue is empty!");
        }else if(stackPop.isEmpty()){ //stackPop必须为空
            while (!stackPush.isEmpty()){ //stackPush必须全部呢弹入到stackPop中
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }

    public int pop(){  //和peek代码相同，只有最后一句不同
        if(stackPush.isEmpty() && stackPop.isEmpty()){
            throw new RuntimeException("Queue is empty!");
        }else if(stackPop.isEmpty()){ //stackPop必须为空
            while (!stackPush.isEmpty()){ //stackPush必须全部呢弹入到stackPop中
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }



}
