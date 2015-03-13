public class LinkedList<Item>{
    private int N;
    private Node first;

    private class Node{
        private Item item;
        private Node next;
    }

    public LinkedList(){
        first = null;
        N = 0;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return N;
    }

    public void addFirst(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public void addLast(Item item){
        Node last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            first = last;
        }else{
            Node x;
            for(x = first; x.next != null; x = x.next){
            }
            x.next = last;
        }
        N++;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Node x = first; x != null; x = x.next){
            s.append(x.item + " ");
        }
        return s.toString();
    }

    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i = 1; i < 10; i++){
            list.addLast(i);
        }

        System.out.println("list size = " + list.size());
        System.out.println(list);
    }
}
