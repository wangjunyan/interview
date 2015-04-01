public class SplitNumber{
    public static void sum(int[] array, int kx){
        printArray(array, 0, kx);
        int k = kx;
        int l = array[kx];
        for(int m = array[k-1]; m <= l/2; m++){
            array[k] = m;
            array[k+1] = l-m;
            sum(array, k+1);
        }
    }

    public static void printArray(int[] array, int from, int to){
        for(int i = from; i <= to; i++) System.out.print(array[i] + " ");
        System.out.println();
    }

    public static void main(String[] args){

        int n = Integer.parseInt(args[0]);
        int[] array = new int[n];
        if(n >= 2){
            for(int i = 1; i <= n/2; i++){
                array[0] = i;
                array[1] = n-i;
                sum(array, 1);
            }
        }
    }
}
