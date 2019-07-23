package jinjie;

import java.util.Stack;

public class MaxRecArea {

    public int maxRecArea(int[][] map){
        if(map.length==0||map[0].length==0||map==null){
            return 0;
        }
        int maxArea=0;
        int[] height=new int[map[0].length]; //一个辅助数组，存储每个位置的height，数组长度为map的列数
        for(int i=0; i<map.length;i++){
            for(int j=0; j<map[0].length;j++){
                height[i]=map[i][j]==0 ? 0 :height[j]+1;
            }
            //把一行当成一个直方图，进行单调栈处理结算
            maxArea=Math.max(maxRecFromBottom(height),maxArea);
        }
        return maxArea;
    }

    public int maxRecFromBottom(int[] height){
        if(height==null || height.length==0){
            return 0;
        }
        int maxArea=0;
        Stack<Integer> stack =  new Stack<>();
        for(int i=0; i<height.length; i++){
            while(!stack.isEmpty() && height[i] <= height[stack.peek()]){
                int cur=stack.pop(); // 当前结算的是cur位置
                int left=stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i-left-1)*height[cur];  //i其实就是右边界
                maxArea = Math.max(maxArea,curArea);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            int cur=stack.pop();  // 当前结算的是cur位置
            int left=stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length-left-1)*height[cur];  //i其实就是右边界
            maxArea = Math.max(maxArea,curArea);
        }
        return maxArea;
    }
}
