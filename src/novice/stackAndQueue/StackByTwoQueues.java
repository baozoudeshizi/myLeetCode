package novice.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用两个队列实现栈
 * 其基本思想是用两个队列，一个做为辅助help
 */

public class StackByTwoQueues {
    private Queue<Integer> queue;
    private Queue<Integer> help;

    public StackByTwoQueues(){
        queue = new LinkedList<Integer>();
        help =  new LinkedList<Integer>();//双向链表，可以当做堆，栈进行操作
    }

    public void push(int pushInt){
        queue.add(pushInt);
    }

    public int peek(){
        if(queue.isEmpty()){
            throw new RuntimeException("Stack is Empty.");
        }
        //需要将queue中的弹出到只剩最后一个，弹出的放入help中
        while(queue.size()!=1){
            help.add(queue.poll());
        }
        int res = queue.poll();
        help.add(res);
        swap();
        return res;
    }

    public int pop(){
        if(queue.isEmpty()){
            throw new RuntimeException("Stack is Empth.");
        }
        while (queue.size()!=1){
            help.add(queue.poll());
        }
        int res=queue.poll();
        swap();
        return res;
    }

    public void swap(){
        Queue<Integer> tmp=help;
        help=queue;
        queue=tmp;
    }



}
