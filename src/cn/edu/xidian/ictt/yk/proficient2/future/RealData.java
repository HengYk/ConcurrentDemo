package cn.edu.xidian.ictt.yk.proficient2.future;

/**
 * Created by heart_sunny on 2018/6/11
 */
public class RealData implements Data {

    private String data;

    public RealData(String data) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = data;
    }

    @Override
    public String getResult() {
        return data;
    }
}
