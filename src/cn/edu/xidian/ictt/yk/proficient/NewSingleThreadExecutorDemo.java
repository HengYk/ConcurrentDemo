package cn.edu.xidian.ictt.yk.proficient;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by heart_sunny on 2018/6/8
 */
public class NewSingleThreadExecutorDemo {

    public static void main(String[] args) {

        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i ++ ) {
            int index = i;

            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(" " +Thread.currentThread().getName() + "-->" + index);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        singleThreadPool.shutdown();
    }

    /*
     pool-1-thread-1-->0
     pool-1-thread-1-->1
     pool-1-thread-1-->2
     pool-1-thread-1-->3
     pool-1-thread-1-->4
     */
}
