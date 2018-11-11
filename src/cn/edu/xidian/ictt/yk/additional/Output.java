package cn.edu.xidian.ictt.yk.additional;

/**
 * Created by heart_sunny on 2018/5/16
 */
public class Output implements Runnable{

    private final Res r;

    public Output(Res r) {
        this.r = r;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (r) {
                r.out();
                /*
                if (!r.flag) {
                    try {
                        r.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(r.name + "..." + r.sex);
                r.flag = false;
                r.notify();*/
            }
        }
    }
}
