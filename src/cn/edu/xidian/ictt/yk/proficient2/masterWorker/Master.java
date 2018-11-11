package cn.edu.xidian.ictt.yk.proficient2.masterWorker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by heart_sunny on 2018/6/14
 */
public class Master {

    //任务列表
    private ConcurrentLinkedDeque<Task> taskQueue = new ConcurrentLinkedDeque<>();

    //存放worker的Map
    private HashMap<String, Thread> workers = new HashMap<>();

    //存放每一个worker的处理结果
    private ConcurrentHashMap<String, Object> taskResultMap = new ConcurrentHashMap<>();

    /**
     * 构造函数
     *
     * @param worker      worker处理类
     * @param workerCount worker的数量
     */
    public Master(Worker worker, int workerCount) {

        worker.setWorkQueue(this.taskQueue);
        worker.setTaskResultMap(this.taskResultMap);

        for (int i = 0; i < workerCount; i++) {
            this.workers.put(Integer.toString(i), new Thread(worker));
        }
    }

    public void submit(Task task) {
        this.taskQueue.add(task);
    }

    public void execute() {
        for (Map.Entry<String, Thread> me: workers.entrySet()) {
            me.getValue().start();
        }
    }

    public boolean isComplete() {
        for (Map.Entry<String, Thread> me: workers.entrySet()) {
            if (me.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    public int getResult() {
        int priceResult = 0;
        for (Map.Entry<String, Object> me: taskResultMap.entrySet()) {
            priceResult += (int) me.getValue();
        }
        return priceResult;
    }
}
