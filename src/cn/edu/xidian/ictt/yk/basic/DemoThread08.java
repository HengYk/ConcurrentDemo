package cn.edu.xidian.ictt.yk.basic;

/**
 * Created by heart_sunny on 2018/11/5
 */
class DiffLockCase {

    public void runThis() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "-run-对象锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void runClass() {
        synchronized (DiffLockCase.class) {
            System.out.println(Thread.currentThread().getName() + "-run-类锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private final Object obj = new Object();

    public void runObject() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + "-run-任意锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class DemoThread08 {

    public static void main(String[] args) {

        test(1);

        /*
        DiffLockCase diffLockCase1 = new DiffLockCase();
        DiffLockCase diffLockCase2 = new DiffLockCase();
        new Thread(new Runnable() {
            @Override
            public void run() {
                diffLockCase1.runThis();
            }
        }).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                diffLockCase2.runClass();
            }
        }).start();*/
    }

    public static void test(int type) {

        final DiffLockCase dlcOne = new DiffLockCase();
        final DiffLockCase dlcTwo = new DiffLockCase();

        if (type == 1) {
            System.out.println("对象锁测试");
        } else if (type == 2) {
            System.out.println("类锁测试");
        } else {
            System.out.println("任意锁测试");
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (type == 1) {
                    dlcOne.runThis();
                } else if (type == 2) {
                    dlcOne.runClass();
                } else {
                    dlcOne.runObject();
                }
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (type == 1) {
                    dlcTwo.runThis();
                } else {
                    if (type == 2) {
                        dlcTwo.runClass();
                    } else {
                        dlcTwo.runObject();
                    }
                }
            }
        }, "t2").start();
    }
}
