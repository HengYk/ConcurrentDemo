package cn.edu.xidian.ictt.yk.proficient2.masterWorker;

import java.util.Random;

/**
 * Created by heart_sunny on 2018/6/14
 */
public class Main {

    public static void main(String[] args) {

        //Master master = new Master(new Worker(), 8);

        System.out.println(Runtime.getRuntime().availableProcessors());
        Master master = new Master(new Worker(), Runtime.getRuntime().availableProcessors());

        Random random = new Random();
        for (int i = 0; i <= 20; i ++) {
            Task task = new Task();
            task.setId(i);
            task.setPrice(random.nextInt(1000));
            master.submit(task);
        }

        master.execute();

        long start = System.currentTimeMillis();

        //循环检查并等待所有worker执行完毕
        while (true) {
            if (master.isComplete()) {
                long end = System.currentTimeMillis() - start;
                int priceResult = master.getResult();
                System.out.println("最终结果：" + priceResult + "，执行时间：" + end);
                break;
            }
        }
    }
}
