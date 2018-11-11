package cn.edu.xidian.ictt.yk.advanced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by heart_sunny on 2018/11/2
 */
public class DemoThread14 implements Runnable {

    /**
     * 数据操作的原子性
     */

    private static volatile int sum = 0;

    private static void add() {

        System.out.println(Thread.currentThread().getName() + "初始化sum = " + sum);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum++;
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
            es.submit(new DemoThread14());
        }

        // shutdown()被调用之后，先前提交的任务将被执行，但不会接收新的任务
        es.shutdown();

        //保证10个子线程执行完毕再输出
        while (true) {
            if (es.isTerminated()) {
                System.out.println("sum最终 = " + sum);
                if (sum == 100) {
                    System.out.println(sum + " = ok");
                } else {
                    System.out.println(sum + " = no");
                }
                break;
            }
        }
    }

    /*
    -----------sum++之前不填加添加延迟-----------
    pool-1-thread-1初始化sum = 0
    pool-1-thread-1计算后sum = 10
    pool-1-thread-2初始化sum = 10
    pool-1-thread-2计算后sum = 20
    pool-1-thread-3初始化sum = 20
    pool-1-thread-3计算后sum = 30
    pool-1-thread-4初始化sum = 30
    pool-1-thread-4计算后sum = 40
    pool-1-thread-6初始化sum = 30
    pool-1-thread-5初始化sum = 30
    pool-1-thread-6计算后sum = 50
    pool-1-thread-7初始化sum = 40
    pool-1-thread-5计算后sum = 60
    pool-1-thread-7计算后sum = 70
    pool-1-thread-8初始化sum = 70
    pool-1-thread-8计算后sum = 80
    pool-1-thread-9初始化sum = 80
    pool-1-thread-9计算后sum = 90
    pool-1-thread-10初始化sum = 90
    pool-1-thread-10计算后sum = 100
    sum最终 = 100
    100 = ok

    -----------sum++之前填加添加延迟-----------
    pool-1-thread-1初始化sum = 0
    pool-1-thread-2初始化sum = 0
    pool-1-thread-3初始化sum = 1
    pool-1-thread-4初始化sum = 1
    pool-1-thread-5初始化sum = 1
    pool-1-thread-6初始化sum = 1
    pool-1-thread-7初始化sum = 1
    pool-1-thread-8初始化sum = 1
    pool-1-thread-9初始化sum = 1
    pool-1-thread-10初始化sum = 1
    pool-1-thread-1计算后sum = 66
    pool-1-thread-2计算后sum = 71
    pool-1-thread-5计算后sum = 72
    pool-1-thread-3计算后sum = 72
    pool-1-thread-4计算后sum = 73
    pool-1-thread-8计算后sum = 74
    pool-1-thread-6计算后sum = 74
    pool-1-thread-7计算后sum = 74
    pool-1-thread-9计算后sum = 75
    pool-1-thread-10计算后sum = 76
    sum最终 = 76
    76 = no

    -----------结论-----------
    volatile不能保证数据操作的原子性，因此无法保证线程安全。
     */
}
