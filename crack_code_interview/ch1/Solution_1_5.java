public class Solution_1_5{

    public static String replaceSpace(String s){
        int len = s.length();
        int num = 0;
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == ' ') num++;
        }
        int newLen = len+2*num;
        char[] newS = new char[newLen];
        int pos = 0;
        for(int i = 0; i < len; i++){
            char c = s.charAt(i);
            if(c != ' '){
                newS[pos] = c;
                pos++;
            }else{
                newS[pos] = '%';
                newS[pos+1] = '2';
                newS[pos+2] = '0';
                pos += 3;
            }
        }

        return new String(newS);
    }

    public static void main(String[] args){
        //String s = args[0];
        String s1 = " ab cd ";
        String s2 = "abcd";
        String s3 = " ab cd";

        System.out.println(replaceSpace(s1));
        System.out.println(replaceSpace(s2));
        System.out.println(replaceSpace(s3));
    }
}
