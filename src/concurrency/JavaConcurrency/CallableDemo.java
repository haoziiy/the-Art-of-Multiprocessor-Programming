package concurrency.JavaConcurrency;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by sherry on 2017/5/13.
 */
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for (int i = 0 ; i < 10 ; i++)
        {
            results.add(exec.submit(new TaskWithResult(i)));
        }
        for (Future<String> fs : results)
        {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e){
                System.out.println(e);
                return;
            } catch (ExecutionException e){
                System.out.println(e);
                return;
            } finally {
                exec.shutdown();
            }
        }
    }

}
