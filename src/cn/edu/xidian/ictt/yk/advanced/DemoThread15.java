package cn.edu.xidian.ictt.yk.advanced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by heart_sunny on 2018/11/2
 */
public class DemoThread15 implements Runnable{

    private static AtomicInteger sum = new AtomicInteger(0);

    private static void add() {

        System.out.println(Thread.currentThread().getName() + "初始化sum = " + sum);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum.addAndGet(1);
        }
        System.out.println(Thread.currentThread().getName() + "计算后sum = " + sum);

    }

    @Override
    public void run() {
        add();
    }

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            es.submit(new DemoThread15());
        }

        // shutdown()被调用之后，先前提交的任务将被执行，但不会接收新的任务
        es.shutdown();

        //保证10个子线程执行完毕再输出
        while (true) {
            if (es.isTerminated()) {
                System.out.println("sum最终 = " + sum);
                if (sum.get() == 100) {
                    System.out.println(sum + " = ok");
                } else {
                    System.out.println(sum + " = no");
                }
                break;
            }
        }
    }

    /*
    -----------去掉volatile关键字，采用java.util.concurrent.atomic.*-----------
    pool-1-thread-1初始化sum = 0
    pool-1-thread-2初始化sum = 0
    pool-1-thread-3初始化sum = 0
    pool-1-thread-4初始化sum = 0
    pool-1-thread-5初始化sum = 0
    pool-1-thread-6初始化sum = 0
    pool-1-thread-7初始化sum = 0
    pool-1-thread-8初始化sum = 1
    pool-1-thread-9初始化sum = 1
    pool-1-thread-10初始化sum = 1
    pool-1-thread-2计算后sum = 91
    pool-1-thread-3计算后sum = 92
    pool-1-thread-4计算后sum = 93
    pool-1-thread-6计算后sum = 95
    pool-1-thread-7计算后sum = 95
    pool-1-thread-5计算后sum = 96
    pool-1-thread-1计算后sum = 97
    pool-1-thread-10计算后sum = 100
    pool-1-thread-9计算后sum = 100
    pool-1-thread-8计算后sum = 100
    sum最终 = 100
    100 = ok

    -----------结论-----------
    对比DemoThread14，我们发现Atomic类可以保证共享变量的原子性。

    -----------特殊的-----------
    Atomic类采用了CAS(checkAndSet)这种非锁机制。
     */
}
