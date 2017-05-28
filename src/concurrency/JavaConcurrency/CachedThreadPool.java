package concurrency.JavaConcurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sherry on 2017/5/13.
 */
public class CachedThreadPool {
    public static void main(String[] args)
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0 ;i < 5 ;i++)
        {
            exec.execute(new Liftoff());
        }
        exec.shutdown();
    }
}
