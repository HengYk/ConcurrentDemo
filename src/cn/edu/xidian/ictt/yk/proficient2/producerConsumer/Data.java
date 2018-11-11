package cn.edu.xidian.ictt.yk.proficient2.producerConsumer;

/**
 * Created by heart_sunny on 2018/6/11
 */
public class Data {

    private String id;
    private String name;

    public Data(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
