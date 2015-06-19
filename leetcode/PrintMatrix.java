public class PrintMatrix{
    public static void main(String[] args){
        int num = Integer.parseInt(args[0]);
        int mid = num - 1;
        int size = num * 2 - 1;
        int[][] matrix = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                int dx = Math.abs(j-mid);
                int dy = Math.abs(i-mid);
                int maxd = Math.max(dx, dy);
                matrix[i][j] = maxd + 1;
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

}
