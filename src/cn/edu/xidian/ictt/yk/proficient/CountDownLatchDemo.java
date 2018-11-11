package cn.edu.xidian.ictt.yk.proficient;

import java.util.concurrent.CountDownLatch;

/**
 * Created by heart_sunny on 2018/5/26
 */
public class CountDownLatchDemo {

    //注意：可以引入多个计数器CountDownLatch
    //private static final CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) {

        final CountDownLatch countDownLatch = new CountDownLatch(3);
        String name = Thread.currentThread().getName();

        new Thread(new Runnable() {

            @Override
            public synchronized void run() {
                try {
                    System.out.println("*" + name);

                    System.out.println(Thread.currentThread().getName() + " await...");
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName() + " stop!!!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public synchronized void run() {
                try {
                    System.out.println("#" + name);

                    System.out.println(Thread.currentThread().getName() + " await...");
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName() + " stop!!!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("..." + countDownLatch.getCount());
        countDownLatch.countDown();
        System.out.println("..." + countDownLatch.getCount());
        countDownLatch.countDown();
        System.out.println("..." + countDownLatch.getCount());
        countDownLatch.countDown();
        /*
          特别注意：此处所有的阻塞线程已经都被唤醒，下面的countDown方法执行时，主线程会和两个子线程争抢资源。
         */
        System.out.println("..." + countDownLatch.getCount());
        countDownLatch.countDown();
        System.out.println("..." + countDownLatch.getCount());
    }

    /*
    -------------结果可能是这样的-------------
    #main
    *main
    Thread-1 await...
    Thread-0 await...
    ...3
    ...2
    ...1
    Thread-1 stop!!!
    Thread-0 stop!!!
    ...0
    ...0

    -------------结果也可能是这样的-------------
    *main
    Thread-0 await...
    #main
    Thread-1 await...
    ...3
    ...2
    ...1
    ...0
    ...0
    Thread-0 stop!!!
    Thread-1 stop!!!
     */
}
