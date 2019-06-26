import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleTest {

    public static void main(String[] agrs) {

        final A a = new A();
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            final int finalI = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    int num = (int) (Math.random() * 10);
                    System.out.println("indicator:" + finalI + "  " + Thread.currentThread().getName() + "   " + num);
                    a.matchDetal(num);
                }
            });
        }
        service.shutdown();
    }
}

class A {
    private int num = 0;
    private int total = 0;
    private int catchTag = 0;

    public double getCatchHitRatio() {
        return catchTag * 1.0 / total;
    }

    public void matchDetal(int into) {

        synchronized (this) {
            if (into < 100) {
                ++num;
                catchTag = catchTag + into;
            }
            System.out.println("------ Step 1 ------" + Thread.currentThread().getName());
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            if (catchTag % 2 == 0) {
                total = total + catchTag;
            }
            System.out.println("------ Step 2 ------" + Thread.currentThread().getName());
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("num:" + num + "   total:" + total + "   catchTag:" + catchTag + "   into:" + into);
    }

    @Override
    public String toString() {
        return "catchTag:" + catchTag + "   total:" + total;
    }
}