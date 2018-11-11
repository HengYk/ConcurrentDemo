package cn.edu.xidian.ictt.yk.proficient2.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by heart_sunny on 2018/6/10
 */
public class SingletonTest {

    public static void main(String[] args) {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i ++) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    SingletonTwo singletonThree = SingletonTwo.getInstance();
                    System.out.println(singletonThree);
                }
            });
        }

        fixedThreadPool.shutdown();
    }
}
