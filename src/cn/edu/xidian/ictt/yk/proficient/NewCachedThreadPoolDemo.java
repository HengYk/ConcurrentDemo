package cn.edu.xidian.ictt.yk.proficient;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by heart_sunny on 2018/6/8
 * instruction: 缓存特性
 */
public class NewCachedThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService cacheThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            int index = i;

//            try {
//                Thread.sleep(index * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(new Date(System.currentTimeMillis()));
                    try {
                        Thread.sleep(index * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "-->" + index);
                    System.out.println(new Date(System.currentTimeMillis()));
                }
            };

            cacheThreadPool.execute(runnable);
        }

        cacheThreadPool.shutdown();
    }

    /*
    Mon Nov 05 19:33:27 CST 2018
    Mon Nov 05 19:33:27 CST 2018
    Mon Nov 05 19:33:27 CST 2018
    Mon Nov 05 19:33:27 CST 2018
    Mon Nov 05 19:33:27 CST 2018
    pool-1-thread-1-->0
    Mon Nov 05 19:33:27 CST 2018
    pool-1-thread-2-->1
    Mon Nov 05 19:33:28 CST 2018
    pool-1-thread-3-->2
    Mon Nov 05 19:33:29 CST 2018
    pool-1-thread-4-->3
    Mon Nov 05 19:33:30 CST 2018
    pool-1-thread-5-->4
    Mon Nov 05 19:33:31 CST 2018
     */
}
