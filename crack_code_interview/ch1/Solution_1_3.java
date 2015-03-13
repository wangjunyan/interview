public class Solution_1_3{

    public static void removeDuplicates(char[] ca){
        if(ca == null) return;
        int len = ca.length;
        if(len < 2) return;

        int tail = 1;
        for(int i = 1; i < ca.length; i++){
            int j = 0;
            for(j = 0; j < tail; j++){
                if(ca[i] == ca[j]){
                    break;
                }
            }
            //ca[tail] = ca[i];
            if(j == tail){
                ca[tail] = ca[i];
                tail++;
            }
        }

        System.out.println("new length = "  + tail);
        //ca[tail] = 0;

        System.out.println("after remove duplicates: " + new String(ca, 0, tail));
    }


    public static void removeDuplicates2(char[] ca){
        if(ca == null) return;
        int len = ca.length;
        if(len < 2) return;
        int tail = 0;
        boolean[] char_set = new boolean[256];
        for(int i = 0; i < len; i++){
            int c = ca[i];
            if(char_set[c]){
                continue;
            }else{
                char_set[c] = true;
                ca[tail] = ca[i];
                tail++;
            }
        }

        System.out.println("after remove duplicates: " + new String(ca, 0, tail));

    }

    public static void main(String[] args){
        String s = args[0];
        //System.out.println("string length = " + s.length());
        //System.out.println("char array size = " + s.toCharArray().length);
        removeDuplicates2(s.toCharArray());
    }
}
