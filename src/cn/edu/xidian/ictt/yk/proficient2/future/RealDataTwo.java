package cn.edu.xidian.ictt.yk.proficient2.future;

import java.util.concurrent.Callable;

/**
 * Created by heart_sunny on 2018/6/11
 */
public class RealDataTwo implements Callable<String>{

    private String data;

    public RealDataTwo(String data) {
        this.data = data;
    }

    @Override
    public String call() throws Exception {
        System.out.println("后台业务逻辑--耗时操作");
        Thread.sleep(5000);
        return data;
    }
}
