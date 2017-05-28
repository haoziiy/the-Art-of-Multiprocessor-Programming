package concurrency.IntroToMulticoreProgramming;

/**
 * Created by sherry on 2017/5/23.
 */
public class Main3 {
    public static void main(String [] args) {
        myThread t1 = new myThread("thread1");
        myThread t2 = new myThread("thread2");
        t1.start(); t2.start();
    }

}

class myThread extends Thread {
    private String name;

    public myThread(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0 ;i < 20;i++) {
            System.out.println(name + ": hello world");
            yield();
        }
    }
}
