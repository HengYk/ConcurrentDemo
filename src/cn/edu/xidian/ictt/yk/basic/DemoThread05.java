package cn.edu.xidian.ictt.yk.basic;

/**
 * Created by heart_sunny on 2018/11/5
 */
class RunCase {
    public synchronized void runOne() {

        runTwo(); // 锁重入，第二把锁释放之后才会继续释放第一把锁。
        System.out.println(Thread.currentThread().getName() + "-run1");
    }

    public synchronized void runTwo() {

        System.out.println(Thread.currentThread().getName() + "-run2");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class DemoThread05 {

    public static void main(String[] args) {

        RunCase rc = new RunCase();

        new Thread(new Runnable() {
            @Override
            public void run() {
                rc.runOne();
            }
        }).start();
    }

    /*
    Thread-0-run2
    Thread-0-run1
     */
}
