package concurrency.JavaConcurrency;

import java.util.concurrent.Callable;

/**
 * Created by sherry on 2017/5/13.
 */
public class TaskWithResult implements Callable<String>{
    private int id;
    public TaskWithResult(int id){
        this.id = id;
    }
    public String call(){
        return "result of TaskWithResult" + id;
    }
}
