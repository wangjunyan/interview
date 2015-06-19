import java.util.*;

public class Permutation{
	public static List<List<Integer>> permute(int[] num){
		int len = num.length;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> item = new ArrayList<Integer>();
		boolean[] marked = new boolean[len];
		permute(num, marked, item, result);
		return result;
	}

	public static void permute(int[] num, boolean[] marked, List<Integer> item, List<List<Integer>> result){
		boolean allMarked = true;
		for(int i = 0; i < marked.length; i++){
			if(!marked[i]){
				allMarked = false;
				break;
			}
		}
		if(allMarked){
			//List<Integer> newItem = new ArrayList<Integer>(item);
			result.add(new ArrayList<Integer>(item));
			return;
		}

		for(int i = 0; i < marked.length; i++){
			if(i > 0 && !marked[i] && !marked[i-1] && num[i] == num[i-1]) continue;
			if(!marked[i]){
				marked[i] = true;
				item.add(num[i]);
				permute(num, marked, item, result);
				item.remove(item.size()-1);
				marked[i] = false;
			}
		}
	}

	public static void main(String[] args){
		int[] num = {1, 1, 1};
		Arrays.sort(num);
		List<List<Integer>> result = permute(num);
		for(List<Integer> list : result){
			for(Integer val : list){
				System.out.print(val + " ");
			}
			System.out.println();
		}
	}

}
