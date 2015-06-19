import java.util.*;
public class SplitString{

	//public static final int N = 4;
	
	public static List<List<String>> splitString(String s){
	int len = s.length();
	List<List<String>> result = new ArrayList<List<String>>();
	List<String> path = new ArrayList<String>();
	splitString(s, 0, 1, 1, result, path);
	return result; 
	}

	public static void splitString(String s, int prev, int start, int parts, List<List<String>> result, List<String> path){
	
	//if(start == s.length() && parts < N) return;
	/*if(parts == N){
		String num = s.substring(prev);
		if(Long.parseLong(num) > 255) return;
		else path.add(s.substring(prev));
		result.add(new ArrayList<String>(path));
		path.remove(path.size()-1);
		return;
	}*/
	if(start == s.length()){
	path.add(s.substring(prev, start));
        result.add(new ArrayList<String>(path));
        path.remove(path.size()-1);
	return;
	}
	
	splitString(s, prev, start+1, parts, result, path);
	path.add(s.substring(prev,start));
	splitString(s, start, start+1, parts+1, result, path);
	path.remove(path.size()-1);
	}

	public static List<List<String>> splitString2(String s){
		List<List<String>> result = new ArrayList<List<String>>();
		List<String> path = new ArrayList<String>();
		splitString2(s, 1, result, path);
		return result;
	}

	private static int minParts = 0;
	public static void splitString2(String s, int parts, List<List<String>> result, List<String> path){
		if(parts >= minParts) return;
		int len = s.length();
		if(isPalindrome(s, 0 , s.length()-1)){
		path.add(s);
		result.add(new ArrayList<String>(path));
		boolean allPalindrome = true;
		for(String ps : path){
			if(!isPalindrome(ps, 0, ps.length()-1)) allPalindrome = false;
			break;
		}
		if(allPalindrome) minParts = Math.min(minParts, parts);
		path.remove(path.size()-1);
		}

		
		for(int i = 1; i <= len-1; i++){
			if(isPalindrome(s, 0, i-1)){
			path.add(s.substring(0,i));
			splitString2(s.substring(i), parts+1, result, path);
			path.remove(path.size()-1);
			}
		}
	}

public static  boolean isPalindrome(String s, int start, int end){
        while(start < end){
            char cs = s.charAt(start);
            char ce = s.charAt(end);
            if(cs != ce) return false;
            start++;
            end--;
        }
        return true;
    }
	public static void main(String[] args){
	minParts = args[0].length()+1;
	List<List<String>> result = splitString2(args[0]);
	for(List<String> slist : result){
		for(String ss : slist){
		System.out.print(ss + " ");
		}
		System.out.println();
	}
	}


}
