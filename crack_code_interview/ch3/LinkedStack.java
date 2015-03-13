import java.util.NoSuchElementException;

public class LinkedStack<Item>{
    private Node first;
    private int N;

    private class Node{
        Item item;
        Node next;
    }

    public LinkedStack(){
        first = null;
        N = 0;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return N;
    }

    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public Item peek(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Node x = first; x != null; x = x.next){
            s.append(x.item + " ");
        }
        return s.toString();
    }

    public static void main(String[] args){
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        for(int i = 0; i < 10; i++)
            stack.push(i);
        System.out.println(stack);
        for(int i = 0; i < 5; i++)
            stack.pop();
        System.out.println(stack);
        for(int i = 0; i < 5; i++)
            stack.pop();
    }
}
