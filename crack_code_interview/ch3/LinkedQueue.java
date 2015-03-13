import java.util.NoSuchElementException;

public class LinkedQueue<Item>{
    private int N;
    private Node first;
    private Node last;

    private class Node{
        private Item item;
        private Node next;
    }

    public LinkedQueue(){
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return N;
    }

    public Item peek(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    public void enqueue(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldLast.next = last;
        N++;
    }

    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;
        return item;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Node x = first; x != null; x = x.next){
            s.append(x.item + " ");
        }
        return s.toString();
    }

    public static void main(String[] args){
        LinkedQueue<Integer> queue = new LinkedQueue<Integer>();
        for(int i = 0; i < 10; i++)
            queue.enqueue(i);
        System.out.println(queue);
        for(int i = 0; i < 5; i++)
            queue.dequeue();
        System.out.println(queue);
        for(int i = 0; i < 5; i++)
            queue.dequeue();
    }
}
