package cn.edu.xidian.ictt.yk.additional;

/**
 * Created by heart_sunny on 2018/5/16
 */
public class Input implements Runnable{

    private final Res r;

    public Input(Res r) {
        this.r = r;
    }

    @Override
    public void run() {

        int x = 0;
        while (true) {
            synchronized (r) {
                if (x == 0) {
                    r.set("yk", "man");
                } else {
                    r.set("wym", "woman");
                }
                x = (x + 1) % 2;
                /*
                if (r.flag) {
                    try {
                        r.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (x == 0) {
                    r.name = "yk";
                    r.sex = "man";
                } else {
                    r.name = "wym";
                    r.sex = "woman";
                }
                x = (x + 1) % 2;
                r.flag = true;
                r.notify();*/
            }
        }
    }
}
