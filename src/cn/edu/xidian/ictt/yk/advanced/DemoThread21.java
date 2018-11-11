package cn.edu.xidian.ictt.yk.advanced;

/**
 * Created by heart_sunny on 2018/11/2
 */
public class DemoThread21 {

    /**
     * System.out.println()语句之后的注释表示程序的执行顺序。
     *
     * @param args
     */
    public static void main(String[] args) {

        ThreadLocal<Integer> th = new ThreadLocal<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                th.set(10);
                System.out.println(Thread.currentThread().getName() + ":" + th.get());  // 1
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + th.get());// 4
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer v = th.get();
                System.out.println(Thread.currentThread().getName() + ":" + v); // 2
                th.set(100);
                System.out.println(Thread.currentThread().getName() + ":" + th.get()); // 3
            }
        }).start();
    }

    /*
    Thread-0:10
    Thread-1:null
    Thread-1:100
    Thread-0:10

    -----------结论-----------
    ThreadLocal为每一个线程创建一个独立的副本，线程中的数据操作互不干扰。
     */
}
