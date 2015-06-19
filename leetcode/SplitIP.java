import java.util.*;
public class SplitIP{

	public static final int N = 4;
	public static List<List<String>> splitString(String s){
	int len = s.length();
	List<List<String>> result = new ArrayList<List<String>>();
	List<String> path = new ArrayList<String>();
	splitString(s, 0, 1, 1, result, path);
	return result; 
	}

	public static void splitString(String s, int prev, int start, int parts, List<List<String>> result, List<String> path){
	
	if(start == s.length() && parts < N) return;
	if(parts == N){
		String num = s.substring(prev);
		if(Long.parseLong(num) > 255) return;
		else path.add(s.substring(prev));
		result.add(new ArrayList<String>(path));
		path.remove(path.size()-1);
		return;
	}
	
	splitString(s, prev, start+1, parts, result, path);
	String num = s.substring(prev, start);
	if(Long.parseLong(num) > 255) return;
	else path.add(num);
	splitString(s, start, start+1, parts+1, result, path);
	path.remove(path.size()-1);
	}

	public static void main(String[] args){
	List<List<String>> result = splitString(args[0]);
	for(List<String> slist : result){
		for(String ss : slist){
		System.out.print(ss + " ");
		}
		System.out.println();
	}
	}


}
