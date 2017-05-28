package concurrency.IntroToMulticoreProgramming;

/**
 * Created by sherry on 2017/5/23.
 * Join Example
 */
public class Main4 {
    static class MyThread extends Thread {
        public void run() {
            for (int i=0; i < 1000; i++) {
                System.out.println("hello world1");
            }
        }
    }
    public static void main(String [] args) {
        MyThread t1 = new MyThread();
        t1.start();
        try {
            t1.join(); // wait for the thread to terminate
        } catch (InterruptedException e) {
            System.out.println("ERROR: Thread was interrupted");
        }
        System.out.println("Thread is done!");
    }
}

