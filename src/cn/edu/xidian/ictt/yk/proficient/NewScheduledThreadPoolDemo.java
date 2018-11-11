package cn.edu.xidian.ictt.yk.proficient;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by heart_sunny on 2018/6/8
 */
public class NewScheduledThreadPoolDemo {

    public static void main(String[] args) {

        // testOne();
        // testTwo();
        testThree();
    }

    /**
     * 延迟时间不包含执行时间
     */
    public static void testThree() {

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

        ScheduledFuture<?> scheduledFuture = scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("*" + new Date(System.currentTimeMillis()));
                try {
                    Thread.sleep(1000); //注意睡眠时间为1000s和3000s时的差异
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                System.out.println(new Date(System.currentTimeMillis()));
            }
        }, 0 , 2, TimeUnit.SECONDS);

        /*
        ------------睡眠时间3000s------------
        *Mon Nov 05 19:53:09 CST 2018
        pool-1-thread-1
        Mon Nov 05 19:53:13 CST 2018
        *Mon Nov 05 19:53:15 CST 2018
        pool-1-thread-1
        Mon Nov 05 19:53:18 CST 2018
        *Mon Nov 05 19:53:20 CST 2018
        pool-1-thread-2
        Mon Nov 05 19:53:23 CST 2018
        *Mon Nov 05 19:53:25 CST 2018
        pool-1-thread-2
        Mon Nov 05 19:53:28 CST 2018
        *Mon Nov 05 19:53:30 CST 2018
        ......

        ------------睡眠时间1000s------------
        *Mon Nov 05 19:54:08 CST 2018
        pool-1-thread-1
        Mon Nov 05 19:54:10 CST 2018
        *Mon Nov 05 19:54:12 CST 2018
        pool-1-thread-1
        Mon Nov 05 19:54:13 CST 2018
        *Mon Nov 05 19:54:15 CST 2018
        pool-1-thread-2
        Mon Nov 05 19:54:16 CST 2018
        *Mon Nov 05 19:54:18 CST 2018
        pool-1-thread-2
        Mon Nov 05 19:54:19 CST 2018
        *Mon Nov 05 19:54:21 CST 2018
        ......
         */
    }

    /**
     * 延迟时间包含执行时间
     */
    public static void testTwo() {

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

        ScheduledFuture<?> scheduledFuture = scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("#" + new Date(System.currentTimeMillis()));
                try {
                    Thread.sleep(1000); //注意睡眠时间为1000s和3000s时的差异
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                System.out.println(new Date(System.currentTimeMillis()));
            }
        }, 0, 2, TimeUnit.SECONDS);

        /*
        ------------睡眠时间3000s------------
        #Mon Nov 05 19:47:37 CST 2018
        pool-1-thread-1
        Mon Nov 05 19:47:40 CST 2018
        #Mon Nov 05 19:47:40 CST 2018
        pool-1-thread-1
        Mon Nov 05 19:47:43 CST 2018
        #Mon Nov 05 19:47:43 CST 2018
        pool-1-thread-2
        Mon Nov 05 19:47:46 CST 2018
        #Mon Nov 05 19:47:46 CST 2018
        pool-1-thread-2
        Mon Nov 05 19:47:49 CST 2018
        #Mon Nov 05 19:47:49 CST 2018
        ......

        ------------睡眠时间1000s------------
        #Mon Nov 05 19:49:27 CST 2018
        pool-1-thread-1
        Mon Nov 05 19:49:28 CST 2018
        #Mon Nov 05 19:49:29 CST 2018
        pool-1-thread-1
        Mon Nov 05 19:49:30 CST 2018
        #Mon Nov 05 19:49:31 CST 2018
        pool-1-thread-2
        Mon Nov 05 19:49:32 CST 2018
        #Mon Nov 05 19:49:33 CST 2018
        pool-1-thread-2
        Mon Nov 05 19:49:34 CST 2018
        #Mon Nov 05 19:49:35 CST 2018
        ......
         */
    }

    /**
     *  ScheduledExecutorService比Timer更安全，功能更强大
     */
    public static void testOne() {

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

        for (int i = 0; i < 5; i ++) {
            int finalI = i;

            scheduledThreadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(new Date(System.currentTimeMillis()));
                    System.out.println(Thread.currentThread().getName() + "-->" + finalI);
                    System.out.println(new Date(System.currentTimeMillis()));
                }
            }, i, TimeUnit.SECONDS);
        }

        scheduledThreadPool.shutdown();

        /*
        Mon Nov 05 19:45:20 CST 2018
        pool-1-thread-1-->0
        Mon Nov 05 19:45:20 CST 2018
        Mon Nov 05 19:45:21 CST 2018
        pool-1-thread-2-->1
        Mon Nov 05 19:45:21 CST 2018
        Mon Nov 05 19:45:22 CST 2018
        pool-1-thread-3-->2
        Mon Nov 05 19:45:22 CST 2018
        Mon Nov 05 19:45:23 CST 2018
        pool-1-thread-1-->3
        Mon Nov 05 19:45:23 CST 2018
        Mon Nov 05 19:45:24 CST 2018
        pool-1-thread-2-->4
        Mon Nov 05 19:45:24 CST 2018
         */
    }
}
