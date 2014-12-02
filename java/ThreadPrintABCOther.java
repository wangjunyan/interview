import java.util.Random;
import java.util.Date;

public class ThreadPrintABCOther implements Runnable {

	private String name;
	private Object prev;
	private Object self;
	private static Random random = new Random(System.currentTimeMillis());

	private ThreadPrintABCOther (String name, Object prev, Object self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	@Override
	public void run() {
		int count = 10;
		while (count > 0) {
             try {
                 Thread.sleep(Math.abs(random.nextInt())%500);
             } catch(InterruptedException ie) {
             }

			synchronized (prev) {
				synchronized (self) {
					System.out.print(name);
					count--;
					self.notify();
				}
				try {
					if (count > 0)
						prev.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		ThreadPrintABCOther pa = new ThreadPrintABCOther("A", c, a);
		ThreadPrintABCOther pb = new ThreadPrintABCOther("B", a, b);
		ThreadPrintABCOther pc = new ThreadPrintABCOther("C", b, c);

		new Thread(pa).start();
		Thread.sleep(1);
		new Thread(pb).start();
		Thread.sleep(1);
		new Thread(pc).start();
	}
}
