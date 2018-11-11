package cn.edu.xidian.ictt.yk.basic;

/**
 * Created by heart_sunny on 2018/11/4
 */
class AddCase {

    private /*static*/ int count = 0;

    public /*synchronized*/ /*static*/ void add() {
        count ++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-" + count);
    }
}

/**
 * Synchronized作用在非静态方法上代表的对象锁，一个对象一个锁，多个对象之间不会发生锁竞争。
 * <p>
 * Synchronized作用在静态方法上则升级为类锁，所有对象共享一把锁，存在锁竞争。
 */
public class DemoThread02 {

    public static void main(String[] args) {

        AddCase ac = new AddCase();
        AddCase acOne = new AddCase();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ac.add();
                //AddCase.add();
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ac.add();
                //acOne.add();
                //AddCase.add();
            }
        }, "t2").start();
    }
}
