import java.util.Random;
import java.util.Date;

public class ThreadPrintABCNew {

    
    public static void main(String[] args) throws InterruptedException {
        //Object obj1 = new Object();
        //Object obj2 = new Object();
        //Object obj3 = new Object();
        PrintWork pw1 = new PrintWork("A");
        PrintWork pw2 = new PrintWork("B");
        PrintWork pw3 = new PrintWork("C");
        pw1.setNextWork(pw2);
        pw2.setNextWork(pw3);
        pw3.setNextWork(pw1);
        pw1.setShouldWait(false);
        //Thread t1 = new Thread(new PrintWork("A", obj1, obj2), "t1");
        //Thread t2 = new Thread(new PrintWork("B", obj2, obj3), "t2");
        //Thread t3 = new Thread(new PrintWork("C", obj3, obj1), "t3");
        Thread t1 = new Thread(pw1, "t1");
        Thread t2 = new Thread(pw2, "t2");
        Thread t3 = new Thread(pw3, "t3");
        t3.start();
        Thread.sleep(1000);
        t2.start();
        Thread.sleep(1000);
        t1.start();
        //synchronized(obj1){
        //    obj1.notifyAll();
        //}
    }


}

class PrintWork implements Runnable {
    private String character;
    private Object myLock;
    //private Object otherLock;
    private PrintWork nextWork;
    private boolean shouldWait;
    private static Random random = new Random(System.currentTimeMillis());
    public PrintWork(String character) {
        this.character = character;
        this.myLock = new Object();
        this.shouldWait = true;
    }

    public void setNextWork(PrintWork nextWork) {
        this.nextWork = nextWork;
    }

    public void setShouldWait(boolean shouldWait) {
        this.shouldWait = shouldWait;
    }

    public Object getLock() {
        return this.myLock;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("thread " + name + " started.");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(Math.abs(random.nextInt())%500);
            } catch(InterruptedException ie) {
            }
            synchronized(myLock) {
                try {
                    while (shouldWait) {
                        myLock.wait();
                    }
                    System.out.println(name + "[" + System.currentTimeMillis() + "]" + " : " + character + " " + i);
                    if (character.equals("C")) System.out.println("--------------------");
                    this.shouldWait = true;
                    synchronized(nextWork.getLock()) {
                        nextWork.getLock().notifyAll();
                        nextWork.setShouldWait(false);
                    }
                } catch(InterruptedException ie) {
                }
            }
        }
    }
}
