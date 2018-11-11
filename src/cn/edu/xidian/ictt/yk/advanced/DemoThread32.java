package cn.edu.xidian.ictt.yk.advanced;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by heart_sunny on 2018/11/4
 */
public class DemoThread32 {

    public static void testOne() {

        SynchronousQueue<Integer> sq = new SynchronousQueue<>();

        sq.add(1); // java.lang.IllegalStateException: Queue full
    }

    public static void testTwo() {

        SynchronousQueue<Integer> sq = new SynchronousQueue<>();

        try {
            sq.put(1); // 阻塞...
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testThree() {

        SynchronousQueue<Integer> sq = new SynchronousQueue<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(sq.take());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sq.put(1);
                    sq.put(2);
                    sq.put(3);
                    sq.put(4);

                    /*
                    sq.add(1);
                    Thread.sleep(1000);
                    sq.add(2);
                    Thread.sleep(1000);
                    sq.add(3);
                     */
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void testFour() {

        SynchronousQueue<Integer> sq = new SynchronousQueue<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Integer v = sq.poll(2, TimeUnit.SECONDS);
                        if (v == null) {
                            System.out.println("null");
                            break;
                        } else {
                            System.out.println(v);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sq.put(1);
                    sq.put(2);
                    sq.put(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {

        // testOne();
        // testTwo();
        testThree();
        // testFour();
    }
}
