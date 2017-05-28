package concurrency.IntroToMulticoreProgramming;

/**
 * Created by sherry on 2017/5/23.
 * Creating new Thread...
 */
public class ThreadDemo implements Runnable{
    ThreadDemo()
    {
        Thread ct = Thread.currentThread();
        System.out.println("Current Thread : " + ct);
        Thread t = new Thread(this,"Demo Thread");
        t.start();
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
            System.out.println("Interrupted.");
        }
        System.out.println("Exiting main thread.");
    }

    public void run()    {
        try   {
            for(int i = 5; i > 0; i--)   {
                System.out.println("  " + i);
                Thread.sleep(1000);
            }
        }
        catch(InterruptedException e)   {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
    public static void main(String args[])  {
        new ThreadDemo();
    }
}
