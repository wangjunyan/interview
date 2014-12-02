import java.util.*;

public class HanoiTower{

    public HanoiTower() {

    }
    
    public static void main(String[] args) {
        HanoiStack hs1 = new HanoiStack(1);
        HanoiStack hs2 = new HanoiStack(2);
        HanoiStack hs3 = new HanoiStack(3);
        //System.out.println(args[0]);
        int n = Integer.parseInt(args[0]);
        for(int i = n; i > 0; i--) hs1.push(i);
        //hs1.printStack();
        // System.out.println(hs1.pop());
        System.out.println("======");
        movePlate(n, hs1, hs2, hs3);
        System.out.println("======");
        hs1.printStack();
        System.out.println("------");
        hs2.printStack();
        System.out.println("------");
        hs3.printStack();
    }

    public static void movePlate(int n, HanoiStack hs1, HanoiStack hs2, HanoiStack hs3) {
        if (n == 1) {
            hs3.push(hs1.pop().intValue());
        } else {
            movePlate(n-1, hs1, hs3, hs2);
            hs3.push(hs1.pop().intValue());
            movePlate(n-1, hs2, hs1, hs3);
        }
    }



}


class HanoiStack extends Stack<Integer> {
    public int index;
    public HanoiStack(int index) {
        this.index = index;
    }

    public Integer push(int v) {
        if(!isEmpty() && v > peek().intValue()) {
            System.out.println("Couldn't put bigger plate up to smaller plate!");
            return null;
        }
        System.out.println("stack num: " + index + " | push item: " + v);
        return super.push(new Integer(v));
    }

    public Integer pop(){
        Integer value = super.pop();
        System.out.println("stack num: " + index + " | pop item: " + value);
        return value;
    }

    public void printStack() {
        while (!isEmpty()) {
            System.out.println(pop());
        }
    }

}
