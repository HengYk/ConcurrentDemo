package cn.edu.xidian.ictt.yk.basic;

/**
 * Created by heart_sunny on 2018/11/5
 */
class ExcCase {

    public int i = 0;

    public synchronized void setI() {

        while (true) {
            i++;
            System.out.println(Thread.currentThread().getName() + "-run-" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 10) {
                throw new RuntimeException(); // 抛出异常释放锁
            }
        }
    }

    public synchronized void getI() {
        System.out.println(Thread.currentThread().getName() + "-run-" + i);
    }
}

public class DemoThread07 {

    public static void main(String[] args) {

        ExcCase ec = new ExcCase();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ec.setI();
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                ec.getI();
            }
        }).start();
    }
    /*
    Thread-0-run-1
    Thread-0-run-2
    Thread-0-run-3
    Thread-0-run-4
    Thread-0-run-5
    Thread-0-run-6
    Thread-0-run-7
    Thread-0-run-8
    Thread-0-run-9
    Thread-0-run-10
    Exception in thread "Thread-0" Thread-1-run-10
     */
}
