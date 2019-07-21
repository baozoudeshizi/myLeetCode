package novice.greedy;

/*
*题目描述：一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
* 给你每一个项目开始的时间和结束的时间(给你一个数 组，里面 是一个个具体的项目)，你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
* 返回这个最多的宣讲场次。
*
* 思路：确定一个会议开始的最早时间，然后在这个会议结束时间内的所有会议都删除掉，再安排下一个最近的会议。
*
* */

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

        public static  class ProgramComparator implements Comparator<Program>{
            @Override
            public int compare(Program o1, Program o2){
                return o1.end-o2.end;
            }
        }

        public static int bestArrange(Program[] programs, int start){
            Arrays.sort(programs,new ProgramComparator());//按照结束时间从前往后排序
            int result=0;//用来记录安排了几个会议
            for(int i=0; i<programs.length;i++){
                if(start<=programs[i].start){//即上一场会议结束时间小于下一场会议开始
                    result++;
                    start=programs[i].end;
                }
            }
            return result;
        }

        public static void main(String[] args){

        }

}
