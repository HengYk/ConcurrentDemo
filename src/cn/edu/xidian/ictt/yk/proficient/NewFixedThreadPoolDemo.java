package cn.edu.xidian.ictt.yk.proficient;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by heart_sunny on 2018/6/8
 */
public class NewFixedThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        System.out.println(Runtime.getRuntime().availableProcessors()); // 定长线程池的大小最好根据系统资源进行设置

        for (int i = 0; i < 10; i ++) {
                int index = i;

            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "-->" + index);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        fixedThreadPool.shutdown();
    }

    /*
    4
    pool-1-thread-1-->0
    pool-1-thread-2-->1
    pool-1-thread-3-->2
    pool-1-thread-4-->3
    pool-1-thread-1-->4
    pool-1-thread-4-->5
    pool-1-thread-3-->6
    pool-1-thread-2-->7
    pool-1-thread-1-->8
    pool-1-thread-4-->9
     */
}
