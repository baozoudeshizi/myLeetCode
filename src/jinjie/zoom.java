package jinjie;

public class zoom {

//    public static void main(String args[]){
//
//        String str="abc000dda00af000";
//        int m=2;
//
//        int length=str.length();
//
//        StringBuffer sb = new StringBuffer(str);
//        char[] chs=str.toCharArray();
//        for(int i=0; i<length-m;i++){
////            while(count<m){
////                if(chs[i]=='0'){
////                    count++;
////                    i++;
////                }
////            }
////            if(chs[i]=='0'){
////                i++;
////            }else{
////
////            }
//            int count=0;
//            while(chs[i]=='0'){
//                count++;
//                i++;
//            }
//            if(count==m){
//                for(int j=i;j<i+m;j++){
//                    chs[j-m]=chs[j];
//                }
//            }
//        }
//        System.out.println(chs);
//    }

    public static void main(String[] args) {
        String str1 = "abc000def00asdkfjl00";
        String str2 = "00";
        zoom d = new zoom();
        Object[] result = d.deleteSubString(str1, str2);
        System.out.println("删除字串后："+result[0]);
    }


    public Object[] deleteSubString(String str1,String str2) {
        StringBuffer sb = new StringBuffer(str1);
        int delCount = 0;
        Object[] obj = new Object[2];
        int p=0;

        while (true) {
            int index = sb.indexOf(str2,p);
            int index2 = sb.indexOf(str2+1,p);
            p=index;
            if(index == -1) {
                break;
            }
            if(index==index2){
                p+=3;
            }
                sb.delete(index, index+str2.length());
                delCount++;


        }
        if(delCount!=0) {
            obj[0] = sb.toString();
            obj[1] = delCount;
        }else {
            //不存在返回-1
            obj[0] = -1;
            obj[1] = -1;
        }

        return obj;
    }



}
