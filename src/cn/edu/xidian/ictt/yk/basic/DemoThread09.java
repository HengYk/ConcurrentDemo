package cn.edu.xidian.ictt.yk.basic;

/**
 * Created by heart_sunny on 2018/11/5
 */
class ChangeLockCase {

    //教程上说：不要在线程中修改对象锁的引用，引用被修改会导致锁失效。但“此处”未证实，欢迎大家指正。
    private String lock = "lock handler";

    //当同一个类的多个不同对象（实例）调用 加this锁和object锁 的方法时，多个对象之间不会发生锁竞争。
    //但是当调用 加class锁或者其他对象锁 的方法时，多个对象之间会发生锁竞争。
    private final Object object = new Object();

    public void method() {

        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + "-start");

            //说明：修改锁引用之后，锁竞争依然存在。真让人头大！
            //lock = "change lock handler";

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-end");
        }
    }
}

public class DemoThread09 {

    public static void main(String[] args) {

        ChangeLockCase clcOne = new ChangeLockCase();
        ChangeLockCase clcTwo = new ChangeLockCase();

        new Thread(new Runnable() {
            @Override
            public void run() {
                clcOne.method();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                clcTwo.method();
            }
        }).start();
    }
}
