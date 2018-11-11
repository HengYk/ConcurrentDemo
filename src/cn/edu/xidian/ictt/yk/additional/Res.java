package cn.edu.xidian.ictt.yk.additional;

/**
 * Created by heart_sunny on 2018/5/16
 */
public class Res {

    /*
    String name;
    String sex;
    boolean flag = false;*/

    private String name;
    private String sex;
    private boolean flag = false;

    public synchronized void set(String name, String sex) {
        if (this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name;
        this.sex = sex;
        this.flag = true;
        this.notify();
    }

    public synchronized void out() {
        if (!this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.name + "..." + this.sex);
        this.flag = false;
        this.notify();
    }
}
