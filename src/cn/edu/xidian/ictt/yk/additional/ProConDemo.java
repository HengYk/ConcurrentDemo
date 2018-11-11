package cn.edu.xidian.ictt.yk.additional;

/**
 * Created by heart_sunny on 2018/5/18
 */
public class ProConDemo {

    public static void main(String[] args) {

        Resource resource = new Resource();

        Thread t1 = new Thread(new Producer(resource));
        Thread t2 = new Thread(new Producer(resource));
        Thread t3 = new Thread(new Consumer(resource));
        Thread t4 = new Thread(new Consumer(resource));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
