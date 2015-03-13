public class Solution_2_2{
    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i = 1; i < 10; i++){
            list.addLast(i);
        }
        System.out.println("list size = " + list.size());
        System.out.println(list);
    }

}
