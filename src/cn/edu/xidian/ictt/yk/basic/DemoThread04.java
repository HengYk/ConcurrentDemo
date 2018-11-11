package cn.edu.xidian.ictt.yk.basic;

/**
 * Created by heart_sunny on 2018/11/5
 */
class DirtyReadCase {

    private String username = "yk";
    private String address = "gp";

    public DirtyReadCase() {
    }

    public synchronized void setVal(String username, String address) {
        this.username = username;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.address = address;
        System.out.println("setVal结果：" + Thread.currentThread().getName() + "-" + username + "-" + address);
    }

    public /*synchronized*/ void getVal() {
        System.out.println("getVal结果：" + Thread.currentThread().getName() + "-" + username + "-" + address);
    }
}

public class DemoThread04 {

    public static void main(String[] args) {

        DirtyReadCase drc = new DirtyReadCase();

        new Thread(new Runnable() {
            @Override
            public void run() {
                drc.setVal("Yk", "Jc");
            }
        }, "t").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        drc.getVal();
    }
    /*
    getVal结果：main-Yk-gp
    setVal结果：t-Yk-Jc
     */
}
