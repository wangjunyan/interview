import java.util.*;

public class StackQueueTest{
    public StackQueueTest(){}

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        System.out.println("--------");
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println("--------");
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println("--------");
        ThreeStack s3 = new ThreeStack(5);
        s3.push(0, 11);
        s3.push(0, 12);
        s3.push(2, 31);
        s3.push(2, 32);
        System.out.println(s3.pop(0));
        System.out.println(s3.pop(0));
        System.out.println(s3.pop(2));
        System.out.println(s3.pop(2));
        System.out.println("--------");
        StackWithMin sm = new StackWithMin();
        sm.push(4);
        System.out.println(sm.min());
        sm.push(5);
        System.out.println(sm.min());
        sm.push(1);
        System.out.println(sm.min());
        System.out.println("--------");
        StackWithMin2 sm2 = new StackWithMin2();
        sm2.push(4);
        System.out.println(sm2.min());
        sm2.push(4);
        System.out.println(sm2.min());
        sm2.push(5);
        System.out.println(sm2.min());
        sm2.push(1);
        System.out.println(sm2.min());
        sm2.pop();
        System.out.println(sm2.min());
        sm2.pop();
        System.out.println(sm2.min());
        sm2.pop();
        System.out.println(sm2.min());
        System.out.println("--------");
        SetOfStacks ss = new SetOfStacks(4);
        int num = 15;
        for(int i = 0; i < num; i++) {
            //ss.push(i+1);
            System.out.println("insert item " + i);
            ss.push(i+1);
        }
        for(int i = num-1; i >= 0; i--){
            //ss.pop();
            System.out.println("remove item " + i);
            ss.pop();
        }

        TwoStacksQueue<Integer> tsq = new TwoStacksQueue<Integer>();
        tsq.add(1);
        tsq.add(2);
        tsq.add(3);
        System.out.println(tsq);
        tsq.remove();
        tsq.remove();
        System.out.println(tsq);
        tsq.add(4);
        tsq.add(5);
        System.out.println(tsq);
        tsq.remove();
        System.out.println(tsq);
        tsq.remove();
        System.out.println(tsq);

        System.out.println("======================");
        Stack<Integer> si = new Stack<Integer>();
        si.push(3);
        si.push(1);
        si.push(2);
        si.push(5);
        si.push(4);
        stackSort(si);
        
        System.out.println("======================");
        TwoQueuesStack<Integer> tqs = new TwoQueuesStack<Integer>();
        for(int i = 0; i < 10; i++) tqs.push(new Integer(i));
        for(int i = 0; i < 10; i++) System.out.println(tqs.pop());
    }
    
    public static void stackSort(Stack<Integer> s){
        System.out.println(s + " | size: " + s.size());
        Stack<Integer> t = new Stack<Integer>();
        while(!s.isEmpty()) {
			int sv = s.pop().intValue();
			while(!t.isEmpty() && sv > t.peek().intValue()){
				s.push(t.pop());
			}
			t.push(new Integer(sv));
		}
        System.out.println(t);
    }
}

class TwoQueuesStack<T> {
    Queue<T> q1;
    Queue<T> q2;

    public TwoQueuesStack() {
        q1 = new LinkedList<T>();
        q2 = new LinkedList<T>();
    }

    public void push(T v) {
        if(!q1.isEmpty()){
            q2.add(v);
            while(!q1.isEmpty()){
                q2.add(q1.remove());
            }
        } else if(!q2.isEmpty()){
            q1.add(v);
            while(!q2.isEmpty()){
                q1.add(q2.remove());
            }
        } else {
            q1.add(v);
        }
    }

    public T pop() {
        if(!q1.isEmpty()){
            return q1.remove();
        } else if (!q2.isEmpty()) {
            return q2.remove();
        } else {
            return null;
        }
        
    }
}

class TwoStacksQueue<T>{
    Stack<T> s1;
    Stack<T> s2;

    public TwoStacksQueue() {
        s1 = new Stack<T>();
        s2 = new Stack<T>();
    }

    public boolean add(T t) {
        /*
        while(!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        s1.push(t);
        while(!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        */
        s1.push(t);
        return true;
    }

    public T remove() {
        //return s1.pop();
        if (s2.isEmpty()) {
            while(!s1.isEmpty()) s2.push(s1.pop());
        }
        return s2.pop();

    }

    //public T peek() {
        //return s1.peek();
    //}


    public String toString() {
        return "s1: " + s1.toString() + " | s2: " + s2.toString();// + " | head = " + s1.peek();
    }
}

class SetOfStacks {
    ArrayList<FixedSizeStack> stacks = new ArrayList<FixedSizeStack>();
    int capacity;
    public SetOfStacks(int capacity) {
        this.capacity = capacity;
    }

    public FixedSizeStack getLastStack() {
        //System.out.println("current stacks size: " + stacks.size());
        if (stacks.size() == 0) {
            return null;
        }

        return stacks.get(stacks.size() - 1);
    }

    public void push(int v) {
        if (getLastStack() == null || getLastStack().getSize() == capacity){
            FixedSizeStack ns = new FixedSizeStack(capacity);
            System.out.println("add a new sub-stack");
            ns.push(new Integer(v));
            stacks.add(ns);
        } else {
            getLastStack().push(new Integer(v));
        }
    }

    public int pop() {
        //System.out.println("last stack size: " + getLastStack().getSize());
        Integer value = getLastStack().pop();
        if (getLastStack().getSize() == 0) {
            stacks.remove(stacks.size() - 1);
            System.out.println("remove an empty sub-stack");
        }
        return value.intValue();
    }
}

class FixedSizeStack extends Stack<Integer>{
    public int capacity;
    public int mysize = 0;
    public FixedSizeStack(int c) {
        this.capacity = c;
    }

    public Integer push(Integer v) {
        //super.push(v);
        mysize++;
        return super.push(v);
    }

    public Integer pop() {
        mysize--;
        return super.pop();
    }

    public int getSize() {
        return mysize;
    }
    
}


class StackWithMin2 extends Stack<Integer> {
    Stack<Integer> s2;

    public StackWithMin2() {
        s2 = new Stack<Integer>();
    }

    public void push(int v) {
        super.push(v);
        if (v <= min()) {
            s2.push(v);
        }
    }

    public Integer pop() {
        Integer value = super.pop();
        if(value.intValue() == min()) {
            s2.pop();
        }
        return value;
    }

    public int min() {
        if(s2.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return s2.peek();
        }

    }
}

class StackWithMin extends Stack<NodeWithMin>{
    public void push(int v) {
        NodeWithMin node = new NodeWithMin(v, Math.min(v, min()));
        super.push(node);
    }

    public int min() {
        if(isEmpty()){
            return Integer.MAX_VALUE;
        } else {
            return peek().min;
        }
    }
}

class NodeWithMin{
    public int value;
    public int min;
    public NodeWithMin(int v, int m) {
        value = v;
        min = m;
    }
}


class ThreeStack{
    private int stackSize;
    int[] buffer;// = new int[3*stackSize];
    int[] stackPointer;// = {0, 0, 0};

    public ThreeStack(int stackSize) {
        this.stackSize = stackSize;
        buffer = new int[3*stackSize];
        stackPointer = new int[] {0, 0, 0};
    }

    void push(int stackIndex, int value) {
        buffer[stackSize*stackIndex+stackPointer[stackIndex]] = value;
        stackPointer[stackIndex]++;
    }

    int pop(int stackIndex) {
        int value;
        if (!isEmpty(stackIndex)) {
            value = buffer[stackSize*stackIndex+stackPointer[stackIndex]-1];
            stackPointer[stackIndex]--;
            return value;
        }
        return 0;
    }

    int peek(int stackIndex) {
        int value;
        if (!isEmpty(stackIndex)) {
            return buffer[stackSize*stackIndex+stackPointer[stackIndex]-1];
        }
        return 0;
    }


    boolean isEmpty(int stackIndex) {
        return stackPointer[stackIndex] == stackIndex*stackSize;
    }
}



