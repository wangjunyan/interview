import java.util.*;

public class LinkedListTest{
    public LinkedListTest(){}

    public static void main(String[] args) {
        List list = new List();
        list.appendToTail(1);
        list.appendToTail(1);
        list.appendToTail(3);
        list.appendToTail(3);
        list.printList();
        deleteDups(list);
        System.out.println("---------");
        List l1 = new List();
        List l2 = new List();
        l1.appendToTail(2);
        l1.appendToTail(1);
        l2.appendToTail(1);
        listAdd(l1, l2);
        l2.deleteAll();
        l2.appendToTail(9);
        listAdd(l1, l2);
        l2.deleteAll();
        l2.appendToTail(0);
        l2.appendToTail(1);
        listAdd(l1, l2);
        l2.deleteAll();
        l2.appendToTail(9);
        l2.appendToTail(1);
        listAdd(l1, l2);
        l2.deleteAll();
        l2.appendToTail(9);
        l2.appendToTail(9);
        listAdd(l1, l2);
        listAdd(list, l2);
    }

    public static void listAdd(List l1, List l2) {
        if(l1.head == null && l2.head == null) return;
        l1.printList();
        System.out.println("+");
        l2.printList();
        System.out.println("=");
        List r = new List();
        int d1, d2, v, c;
        Node n1 = l1.head;
        Node n2 = l2.head;
        c = 0;
        while (n1 != null || n2 != null) {
            if (n1 != null) {
                d1 = n1.data;
            } else {
                d1 = 0;
            }

            if (n2 != null) {
                d2 = n2.data;
            } else {
                d2 = 0;
            }

            v = d1 + d2 + c;
            //c = 0;
            if (d1 + d2 + c >= 10){
                v = d1 + d2 + c - 10;
                c = 1;
            } else {
                c = 0;
            }

            r.appendToTail(v);

            if (n1 != null) n1 = n1.next;
            if (n2 != null) n2 = n2.next;
        }
        if ( c == 1) r.appendToTail(c);
        r.printList();
        System.out.println("------");
    }


    public static void deleteDups(List list){
        if (list == null || list.head == null || list.head.next == null) return;
        System.out.println("----------");
        list.printList();
        Hashtable t = new Hashtable();
        t.put(list.head.data, true);
        Node p = list.head;
        Node n = list.head.next;
        while (n != null) {
            if(t.containsKey(n.data)){
                p.next = n.next;
            } else {
                t.put(n.data, true);
                p = n;
            }
            n = n.next;
        }
        list.printList();
    }
}


class Node {
    Node next = null;
    int data;
    public Node(int data) {
        this.data = data;
    }
}

class List {

    public Node head = null;

    public void deleteAll() {
        head = null;
    }

    public void appendToTail(int d) {
        Node end = new Node(d);
        Node n = head;
        if (head == null) {
            head = end;
            return;
        }
        while(n.next != null) {
            n = n.next;
        }
        n.next = end;
        //return head;
    }

    public void deleteNode(int d) {
        Node n = head;
        if(head == null) return;
        if(n.data == d)  head = n.next;
        while (n.next != null){
            if(n.next.data == d) {
                n.next = n.next.next;
                //return head;
                break;
            }
            n = n.next;
        }
        //return head;
    }

    public void printList(){
        if(head == null) return;
        Node n = head;
        while(n != null) {
            System.out.println(n.data);
            n = n.next;
        }
    }
}
