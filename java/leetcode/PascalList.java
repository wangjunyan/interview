import java.util.List;
import java.util.ArrayList;

public class PascalList {
	public static void main(String[] args) throws InterruptedException {
		List<Integer> pl0 = Solution.getRow(0);
		System.out.println(pl0);
		List<Integer> pl1 = Solution.getRow(1);
		System.out.println(pl1);
		List<Integer> pl3 = Solution.getRow(3);
		System.out.println(pl3);
	}
}


class Solution {
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> pascalList = new ArrayList<Integer>();
	pascalList.add(1);
        for(int i = 0; i < rowIndex; i++){
            pascalList.add(1);
            for(int j = i; j > 0; j--){
                pascalList.set(j, pascalList.get(j) + pascalList.get(j-1));
            }
        }
        return pascalList;
    }
}
