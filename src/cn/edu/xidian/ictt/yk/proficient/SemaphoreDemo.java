package cn.edu.xidian.ictt.yk.proficient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by heart_sunny on 2018/5/28
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(2);
        //Semaphore semaphore = new Semaphore(2, true); //强制公平，但会影响到并发性能

        for (int i = 0; i < 5; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "尝试获取许可证");
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + "获取许可证");
                        Thread.sleep(5000);
                        System.out.println(Thread.currentThread().getName() + "释放许可证");
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }

        executorService.shutdown();
    }

    /*
    -------------不计较公平-------------
    pool-1-thread-1尝试获取许可证
    pool-1-thread-2尝试获取许可证
    pool-1-thread-2获取许可证
    pool-1-thread-1获取许可证
    pool-1-thread-3尝试获取许可证
    pool-1-thread-4尝试获取许可证
    pool-1-thread-5尝试获取许可证
    pool-1-thread-2释放许可证
    pool-1-thread-1释放许可证
    pool-1-thread-3获取许可证
    pool-1-thread-4获取许可证
    pool-1-thread-3释放许可证
    pool-1-thread-4释放许可证
    pool-1-thread-5获取许可证
    pool-1-thread-5释放许可证

    注意：尽管pool-1-thread-1首先尝试获取许可证，但是pool-1-thread-2还是率先获得了许可证（无公平性）。

    -------------强制公平-------------
    pool-1-thread-1尝试获取许可证
    pool-1-thread-2尝试获取许可证
    pool-1-thread-1获取许可证
    pool-1-thread-2获取许可证
    pool-1-thread-3尝试获取许可证
    pool-1-thread-4尝试获取许可证
    pool-1-thread-5尝试获取许可证
    pool-1-thread-2释放许可证
    pool-1-thread-1释放许可证
    pool-1-thread-3获取许可证
    pool-1-thread-4获取许可证
    pool-1-thread-3释放许可证
    pool-1-thread-4释放许可证
    pool-1-thread-5获取许可证
    pool-1-thread-5释放许可证
     */
}
