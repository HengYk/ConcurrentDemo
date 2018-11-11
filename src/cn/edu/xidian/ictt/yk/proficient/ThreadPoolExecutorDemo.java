package cn.edu.xidian.ictt.yk.proficient;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by heart_sunny on 2018/6/8
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {

        ThreadPoolExecutor executorService =
                new ThreadPoolExecutor(2, 2, 60L, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(1), new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        //自定义拒绝策略
                        System.out.println(r);
                    }
                });

        for (int i = 0; i < 5; i++) {
            int index = i;

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "-->" + index);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();
    }

    /*
    -------------new LinkedBlockingDeque<>(1)------------
    cn.edu.xidian.ictt.yk.proficient.ThreadPoolExecutorDemo$2@6d6f6e28
    pool-1-thread-1-->0
    cn.edu.xidian.ictt.yk.proficient.ThreadPoolExecutorDemo$2@135fbaa4
    pool-1-thread-2-->1
    pool-1-thread-1-->2

    -------------new LinkedBlockingDeque<>(5)------------
    pool-1-thread-1-->0
    pool-1-thread-2-->1
    pool-1-thread-2-->2
    pool-1-thread-1-->3
    pool-1-thread-1-->4
     */
}
