import java.util.Random;

public class ThreadPrintABC {

    
    public static void main(String[] args) throws InterruptedException {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        Thread t1 = new Thread(new PrintWork("A", obj1, obj2), "t1");
        Thread t2 = new Thread(new PrintWork("B", obj2, obj3), "t2");
        Thread t3 = new Thread(new PrintWork("C", obj3, obj1), "t3");
        t3.start();
        Thread.sleep(1000);
        t2.start();
        Thread.sleep(1000);
        t1.start();
        Thread.sleep(1000);
        synchronized(obj1){
            obj1.notifyAll();
        }
    }


}

class PrintWork implements Runnable {
    private String character;
    private Object myLock;
    private Object otherLock;
    private static Random random = new Random(System.currentTimeMillis());
    public PrintWork(String character, Object myLock, Object otherLock) {
        this.character = character;
        this.myLock = myLock;
        this.otherLock = otherLock;
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
                myLock.wait();
                System.out.println(name + " : " + character + " " + i);
                synchronized(otherLock) {
                        otherLock.notifyAll();
                    }
                } catch(InterruptedException ie) {
                }
            }
        }
    }
}
