package cn.edu.xidian.ictt.yk.additional;

/**
 * Created by heart_sunny on 2018/5/18
 */
public class Consumer implements Runnable {

    private Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {

        while (true) {
            resource.out();
        }
    }
}
