import java.util.*;

public class Subsets{
    public static List<List<Integer>> subsets(int[] num){
        //int len = num.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        subsets(num, 0, path, result);
        //printListList(result);
        return result;
    }   

    public static void subsets(int[] num, int step, List<Integer> path, List<List<Integer>> result){
        if(step == num.length){
            result.add(new ArrayList<Integer>(path));
            //printList(path);
            return;
        }
        subsets(num, step+1, path, result);
        path.add(num[step]);
        subsets(num, step+1, path, result);
        int pathSize = path.size();
        path.remove(pathSize-1);
    }

    public static List<List<Integer>> subsetsWithDup(int[] num){
        int len = num.length;
        int[] newNum = new int[len];
        int[] newCount = new int[len];
        int currValue = num[0];
        newNum[0] = num[0];
        newCount[0] = 1;
        int newSize = 1;
        for(int i = 1; i < len; i++){
            if(num[i] == currValue){
                newCount[newSize-1] += 1;
            }else{
                currValue = num[i];
                newSize++;
                newNum[newSize-1] = currValue;
                newCount[newSize-1] = 1;
            }
        }

        //for(int i = 0; i < newSize; i++) System.out.print(newNum[i] + " ");
        //System.out.println();
        //for(int i = 0; i < newSize; i++) System.out.print(newCount[i] + " ");
        //System.out.println();

        List<List<Integer>> result = new ArrayList<List<Integer>>(); 
        List<Integer> path = new ArrayList<Integer>();
        subsetsWithDup(newNum, newCount, newSize, 0, path, result);
        return result;
    }

    public static void subsetsWithDup(int[] newNum, int[] newCount, int newSize, int step, List<Integer> path, List<List<Integer>> result){
        if(step == newSize){
            result.add(new ArrayList<Integer>(path));
            return;
        }

        subsetsWithDup(newNum, newCount, newSize, step+1, path, result);
        int i = 0;
        int j = 0;
        for(i = 0; i < newCount[step]; i++){
            for(j = 0; j <= i; j++){
                path.add(newNum[step]);
            }
            subsetsWithDup(newNum, newCount, newSize, step+1, path, result);
            for(j = 0; j <= i; j++){
                int pathSize = path.size();
                path.remove(pathSize-1);
            }
        }
    }

    public static void printListList(List<List<Integer>> list){
        for(List<Integer> l : list){
            for(Integer i : l){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void printList(List<Integer> list){
        for(Integer i : list) System.out.print(i + " ");
        System.out.println();
    }

    public static List<String> splitString(String s){
        int len = s.length();
        List<String> result = new ArrayList<String>();
        //String[] path = new String[len];
        //for(int i = 1; i <= len; i++){
        List<String> path = new ArrayList<String>();
        splitString(s, 0, 1, result, path);
        //}
        return result;
    }

    public static void splitString(String s, int prev, int start, List<String> result, List<String> path){
        //if(step == s.length()-1){
        //    StringBuilder sb = new StringBuilder();
        //    for(String ss : path) sb.append(ss + " ");
        //    result.add(sb.toString());
        //}

        //splitString(s, step+1, result, path);
        //path.add(s.substring(0, step));
        //splitString(s.subString(step), step+1, result, path);
        if(start == s.length()){
            path.add(s.substring(prev, start));
            StringBuilder sb = new StringBuilder();
            for(String ss : path) sb.append(ss + " ");
            result.add(sb.toString());
            path.remove(path.size()-1);
            return;
        }

        splitString(s, prev, start+1, result, path);

        path.add(s.substring(prev, start));
        splitString(s, start, start+1, result, path);
        path.remove(path.size()-1);
    }



    public static void main(String[] args){

        int[] num1 = {1, 2, 3, 4};
        List<List<Integer>> result1 = subsets(num1);
        printListList(result1);
        System.out.println("---------------");
        int[] num2 = {1, 2, 2};
        List<List<Integer>> result2 = subsetsWithDup(num2);
        printListList(result2);

        String s = args[0];
        List<String> result3 = splitString(s);
        System.out.println("------");
        for(String ss : result3) System.out.println(ss);
    }

}
