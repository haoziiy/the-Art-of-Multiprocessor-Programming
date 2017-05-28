package concurrency.IntroToMulticoreProgramming;

/**
 * Created by sherry on 2017/5/23.
 */
public class CurrentThreadDemo {
    public static void main(String arg[])   {
        Thread ct = Thread.currentThread();
        ct.setName( "My Thread" );
        System.out.println("Current Thread : " + ct);
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("  " + i);
                Thread.sleep(1000);
            }
        }
        catch(InterruptedException e) {
            System.out.println("Interrupted.");    }
    }

}
