package cn.edu.xidian.ictt.yk.proficient;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.Exchanger;

/**
 * Created by heart_sunny on 2018/5/28
 */
class ExchangerRunnable implements Runnable {

    private Exchanger<String> exchanger;
    private Object object;

    public ExchangerRunnable(Exchanger<String> exchanger, Object object) {
        this.exchanger = exchanger;
        this.object = object;
    }

    @Override
    public void run() {
        Object previous = this.object;
        System.out.println(Thread.currentThread().getName() + "交换前：" + previous);

        //个人观点：先处理完，先进行交换。
        try {
            if ("A".equals(this.object)) {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "处理了1s");
            } else if ("B".equals(this.object)) {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "处理了2s");
            } else if ("C".equals(this.object)) {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + "处理了3s");
            } else if ("D".equals(this.object)) {
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName() + "处理了4s");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            this.object = this.exchanger.exchange((String) this.object);
            System.out.println(Thread.currentThread().getName() + " exchange " + previous + " for " + this.object);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ExchangerDemo {

    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        ExchangerRunnable runnable = new ExchangerRunnable(exchanger, "A");
        ExchangerRunnable runnable1 = new ExchangerRunnable(exchanger, "B");

        new Thread(runnable).start();
        new Thread(runnable1).start();

        Exchanger<String> exchanger2 = new Exchanger<>();

        ExchangerRunnable runnable3 = new ExchangerRunnable(exchanger2, "C");
        ExchangerRunnable runnable4 = new ExchangerRunnable(exchanger2, "D");

        new Thread(runnable3).start();
        new Thread(runnable4).start();
    }

    /*
    Thread-0交换前：A
    Thread-1交换前：B
    Thread-2交换前：C
    Thread-3交换前：D
    Thread-0处理了1s
    Thread-1处理了2s
    Thread-1 exchange B for A
    Thread-0 exchange A for B
    Thread-2处理了3s
    Thread-3处理了4s
    Thread-3 exchange D for C
    Thread-2 exchange C for D
     */
}
