package novice.recursion;

public class Print_All_Subsquences {
    public static void printAllSubsquence(String str, int i,String result){
        char[] chs=str.toCharArray();
        if(i==str.length())
        {
            System.out.println(result);
            return;
        }
        printAllSubsquence(str,i+1,result);//chs[i+1]的值不要
        printAllSubsquence(str,i+1,result+String.valueOf(chs[i]));//chs[i+1]的值加上

    }

    public static void main(String[] args){
        String test="abc";
        printAllSubsquence(test,1,"");
    }



}
