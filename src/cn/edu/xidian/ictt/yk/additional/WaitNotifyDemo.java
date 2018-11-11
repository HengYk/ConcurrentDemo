package cn.edu.xidian.ictt.yk.additional;

/**
 * Created by heart_sunny on 2018/5/16
 */
public class WaitNotifyDemo {

    public static void main(String[] args) {

        Res r = new Res();
        new Thread(new Input(r)).start();
        new Thread(new Output(r)).start();
    }
}
