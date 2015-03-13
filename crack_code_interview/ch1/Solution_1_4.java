import java.util.Arrays;

public class Solution_1_4{


    public static boolean isAnagrams(String s1, String s2){
        char[] t1 = s1.toCharArray();
        char[] t2 = s2.toCharArray();
        Arrays.sort(t1);
        Arrays.sort(t2);
        String r1 = new String(t1);
        String r2 = new String(t2);
        System.out.println(s1 + " -> " + r1);
        System.out.println(s2 + " -> " + r2);
        return r1.equals(r2);
    }


    public static void main(String[] args){
        String s1 = args[0];
        String s2 = args[1];
        System.out.println(isAnagrams(s1, s2));
    }
}
