package cn.edu.xidian.ictt.yk.proficient2.masterWorker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by heart_sunny on 2018/6/14
 */
public class Worker implements Runnable{

    private ConcurrentLinkedDeque<Task> workQueue;

    private ConcurrentHashMap<String, Object> taskResultMap;

    public Worker() {

    }

    public void setWorkQueue(ConcurrentLinkedDeque<Task> workQueue) {
        this.workQueue = workQueue;
    }

    public void setTaskResultMap(ConcurrentHashMap<String, Object> taskResultMap) {
        this.taskResultMap = taskResultMap;
    }

    @Override
    public void run() {

        while (true) {
            Task task = this.workQueue.poll();
            if (task == null) {
                break;
            }
            System.out.println("Worker-" + Thread.currentThread().getName() + "-执行任务" + task.getId());

            //处理任务
            Object output = handle(task);
            this.taskResultMap.put(Integer.toString(task.getId()), output);
        }
    }

    private Object handle(Task input) {
        Object output = null;
        try {
            Thread.sleep(3000);
            output = input.getPrice() + 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }
}
