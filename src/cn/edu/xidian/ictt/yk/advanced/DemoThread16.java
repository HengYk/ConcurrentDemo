package cn.edu.xidian.ictt.yk.advanced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by heart_sunny on 2018/11/2
 */
public class DemoThread16 implements Runnable {

    private static AtomicInteger sum = new AtomicInteger(0);

    private synchronized static void add() {

        sum.addAndGet(1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sum.addAndGet(9);

        System.out.println(Thread.currentThread().getName() + "计算后sum = " + sum);
    }

    @Override
    public void run() {
        add();
    }

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i ++) {
            es.submit(new DemoThread16());
        }

        es.shutdown();
    }

    /*
    -----------不添加synchronized关键字-----------
    pool-1-thread-1计算后sum = 28
    pool-1-thread-2计算后sum = 28
    pool-1-thread-3计算后sum = 37
    pool-1-thread-4计算后sum = 46
    pool-1-thread-5计算后sum = 55
    pool-1-thread-6计算后sum = 64
    pool-1-thread-8计算后sum = 82
    pool-1-thread-7计算后sum = 82
    pool-1-thread-10计算后sum = 100
    pool-1-thread-9计算后sum = 100

    -----------添加synchronized关键字-----------
    pool-1-thread-1计算后sum = 10
    pool-1-thread-10计算后sum = 20
    pool-1-thread-9计算后sum = 30
    pool-1-thread-8计算后sum = 40
    pool-1-thread-7计算后sum = 50
    pool-1-thread-6计算后sum = 60
    pool-1-thread-5计算后sum = 70
    pool-1-thread-4计算后sum = 80
    pool-1-thread-3计算后sum = 90
    pool-1-thread-2计算后sum = 100

    -----------结论-----------
    只Atomic类不能保证成员方法的原子性，但配合synchronized关键字可保证原子性操作。
     */
}
