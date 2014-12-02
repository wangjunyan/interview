public class ArrayStringTest {
    public ArrayStringTest() {
    }

    public static void main(String[] args) {
        String s1 = "abcdefghijk";
        String s2 = "abcdefghija";
        System.out.println(s1 + " " + isUniqueChars2(s1));
        System.out.println(s2 + " " + isUniqueChars2(s2));
        System.out.println(s1 + " " + isUniqueChars(s1));
        System.out.println(s2 + " " + isUniqueChars(s2));
        System.out.println("-------------------");
        String s3 = "abcd";
        removeDuplicates(s3.toCharArray());
        s3 = "aaaaaaaaa";
        removeDuplicates(s3.toCharArray());
        s3 = "aaaabbbb";
        removeDuplicates(s3.toCharArray());
        s3 = "a";
        removeDuplicates(s3.toCharArray());
        s3 = "abababab";
        removeDuplicates(s3.toCharArray());
    }

    public static boolean isUniqueChars2(String s) {
        boolean[] char_set = new boolean[256];
        for(int i = 0; i < s.length(); i++)
        {
            int val = s.charAt(i);
            if (char_set[val]) return false;
            char_set[val] = true;
        }
        return true;
    }

    public static boolean isUniqueChars(String s) {
        int result = 0;
        for(int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'a';
            if ((result & (1 << val)) > 0) return false;
            result |= (1 << val);
        }
        return true;
    }

    public static void removeDuplicates(char[] str) {
        if (str == null) return;
        System.out.println(str);
        int len = str.length;
        if (len < 2) return;
        System.out.println("string lenght = " + len);
        int tail = 1;
        int i, j;
        for(i = 1; i < len; i++) {
            for(j = 0; j < tail; j++) {
                if(str[i] == str[j]) {
                    break;
                }
            }
            //tail++;
            if (j == tail) {
                if (i != tail) {
                    //tail++;
                    str[tail] = str[i];
                }
                tail++;
            }
        }
        //str[tail] = 0;
        //System.out.println(str);
        for(i = 0; i < tail; i++) System.out.println(str[i]);
        System.out.println("---");
    }
}
