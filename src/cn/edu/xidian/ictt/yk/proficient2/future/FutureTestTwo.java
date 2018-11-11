package cn.edu.xidian.ictt.yk.proficient2.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by heart_sunny on 2018/6/11
 */
public class FutureTestTwo {

    public static void main(String[] args) {

        RealDataTwo realDataTwo = new RealDataTwo("yk");
        FutureTask<String> futureTask = new FutureTask<String>(realDataTwo);
        FutureTask<String> futureTask2 = new FutureTask<String>(realDataTwo);

        ExecutorService executorService = Executors.newFixedThreadPool(2);//注意线程数为1和为2时的区别（newFixedThreadPool特性）
        executorService.submit(futureTask);
        executorService.submit(futureTask2);

        System.out.println("执行其他业务逻辑");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("请求数据" + futureTask.get());
            System.out.println("请求数据" + futureTask2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
