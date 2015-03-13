public class Solution_9_3{

    public static int calcShift(int[] a, int size){
        int lo = 0;
        int hi = size - 1;
        int first = a[0];
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(first < a[mid]){
                if(first > a[mid+1])
                    return mid+1;
                else
                    lo = mid + 1;
            }else if(first > a[mid]){
                if(first <= a[mid-1])
                    return mid;
                else
                    hi = mid - 1;
            }
        }
        return 0;
    }

    public static int rank(int key, int[] a, int size, int shift){
        int lo = shift;
        int hi = size - 1 + shift;
        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(key < a[mid%size])
                hi = mid - 1;
            else if(key > a[mid%size])
                lo = mid + 1;
            else return mid%size;
        }
        return -1;
    }

    public static int search(int[] a, int l, int u, int x){
        while(l <= u){
            int m = (l + u)/2;
            if(x == a[m]){
                return m;
            }else if(a[l] <= a[m]){
                if(x > a[m])
                    l = m+1;
                else if(x >= a[l])
                    u = m-1;
                else 
                    l = m+1;
            }else if (x < a[m]){
                u = m-1;
            }else if (x <= a[u]){
                l = m+1;
            }else{
                u = m - 1;
            }

        }
        return -1;
    }

    public static void main(String[] args){
        int size = Integer.parseInt(args[0]);
        int shift = Integer.parseInt(args[1]);
        int key = Integer.parseInt(args[2]);
        int[] a = new int[size];
        for(int i = 0; i < size; i++){
            a[(i+shift)%size] = i+1;
        }

        for(int i = 0; i < size; i++)
            a[i] = 2;
        a[7] = 3;

        String s = "";
        String r = "";
        for(int i = 0; i < size; i++){
            s += (i + "  ");
            r += (a[i] + "  ");
        }
        System.out.println(s);
        System.out.println(r);
        
        //int calc_shift = calcShift(a, size);
        //System.out.println("the shift is " + calc_shift);
        //int index = rank(key, a, size, calc_shift);
        //System.out.println("the index of " + key + " is " + index);
        System.out.println("the index of " + key + " is " + search(a, 0, size-1, key));
    }
}
