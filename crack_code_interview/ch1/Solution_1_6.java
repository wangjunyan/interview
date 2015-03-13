public class Solution_1_6{

    public static void printMatrix(int[][] m, int n){
        for(int i = 0; i < n; i++){
            String s = "";
            for(int j = 0; j < n; j++){
                s += (m[i][j] + " ");
            }
            System.out.println(s);
        }
    }

    public static void rotateMatrix(int[][] m, int n){
        for(int i = 0; i < (n/2+n%2); i++){
            System.out.println("process circle " + i);
            int tn = n - i*2;
            int maxj = i + tn -1;
            System.out.println("the moving element amount: " + tn); 
            String s = "";
            for(int j = i; j < maxj; j++){
                s += (j + " ");
                int t = m[i][j];
                m[i][j] = m[maxj-(j-i)][i];
                m[maxj-(j-i)][i] = m[maxj][maxj-(j-i)];
                m[maxj][maxj-(j-i)] = m[j][maxj];
                m[j][maxj] = t;
            }
            System.out.println("process j: " + s);
        }
        printMatrix(m,n);
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int[][] m = new int[n][n];
        for(int i = 0; i < n ; i++)
            for(int j = 0; j < n; j++)
                m[i][j] = (i+1)*10+j+1;
        printMatrix(m, n);
        System.out.println("-----------------------");
        rotateMatrix(m, n);
    }
}
