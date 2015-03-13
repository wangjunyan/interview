public class Solution_1_1{

    public static boolean isUniqueChars(String s){
        boolean[] char_set = new boolean[256];
        for(int i = 0; i < s.length(); i++){
            int c = s.charAt(i);
            if(char_set[c]){
                return false;
            }else{
                char_set[c] = true;
            }
        }
        return true;
    }

    public static boolean isUniqueChars2(String s){
        int char_set = 0;
        for(int i = 0; i < s.length(); i++){
            int c = s.charAt(i);
            int bit = c - 'a';
            //System.out.println("debug: " + "bit=" + bit);
            if((char_set & (1 << bit)) != 0){
                return false;
            }else{
                char_set |= (1 << bit);
                //System.out.println("debug: " + "char_set=" + char_set);
            }
        }
        return true;
    }

    public static void main(String[] args){
        String s = args[0];

        System.out.println(isUniqueChars2(s));
    }
}
