package jinjie;

//单调栈的应用
//题目描述：给定一个不含有重复值的数组arr，找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置。返回所有位置相应的消息。


import sun.rmi.server.InactiveGroupException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GetNearLessNoRepeat {

    public int[][] getNearLessNoRepeat(int[] arr){
        int[][] res=new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<arr.length; i++){
            //当栈非空，且当前栈顶比压入的值还大，那么接下来要做弹出操作，即可获得左右两边的位置，存在res中
            while (!stack.isEmpty() && arr[stack.peek()]>arr[i]){
                int popIndex=stack.pop();
                int leftLessIndex=stack.isEmpty()? -1 : stack.peek();
                res[popIndex][0]=leftLessIndex;
                res[popIndex][1]=i;
            }
            stack.push(i); //如果空或者当前栈顶的值比要压栈的值还要大，那么则顺利压栈
        }

        //最后要全部出栈
        while(!stack.isEmpty()){
            int popIndex = stack.pop();
            int leftLessIndex=stack.isEmpty()? -1 :stack.peek();
            res[popIndex][0]=leftLessIndex;
            res[popIndex][1]=-1;
        }
        return res;
    }
}


class GetNearLess {

    public int[][] getNearLess(int[] arr){
        int[][] res=new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>(); //这个地方要改成Stack<List<Integer>>
        for(int i=0; i<arr.length; i++){
            //当栈非空，且当前栈顶比压入的值还大，那么接下来要做弹出操作，即可获得左右两边的位置，存在res中
            while (!stack.isEmpty() && arr[stack.peek().get(0)]>arr[i]){
                List<Integer> popIndex=stack.pop();
                //取下面位置中最晚加入的，要考虑是否有相同值的链表
                int leftLessIndex=stack.isEmpty()? -1 : stack.peek().get(stack.peek().size()-1);
                for(Integer index:popIndex){
                    res[index][0]=leftLessIndex;
                    res[index][1]=i;
                }

            }
            if(!stack.isEmpty() && arr[stack.peek().get(0)]==arr[i]){
                //遇到相同的值，则加入当前值的链表当中，但要Integer处理
                stack.peek().add(Integer.valueOf(i));
            }else{ //如果空或者当前栈顶的值比要压栈的值还要大，那么则顺利压栈
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        //最后要全部出栈
        while(!stack.isEmpty()){
            List<Integer> popIndex=stack.pop();
            //取下面位置中最晚加入的，要考虑是否有相同值的链表
            int leftLessIndex=stack.isEmpty()? -1 : stack.peek().get(stack.peek().size()-1);
            for(Integer index:popIndex) {
                res[index][0] = leftLessIndex;
                res[index][1] = -1;
            }
        }
        return res;
    }
}