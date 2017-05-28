package concurrency.IntroToMulticoreProgramming;

/**
 * Created by sherry on 2017/5/23.
 */
class MyThread extends Thread {
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0 ; i < 20 ;i++) {
            System.out.println(name + ": hello world");
        }
    }
}

public class Main2 {
    public static void main(String [] args) {
        MyThread t1 = new MyThread("thread1");
        MyThread t2 = new MyThread("thread2");
        t1.start(); t2.start();
    }
}
