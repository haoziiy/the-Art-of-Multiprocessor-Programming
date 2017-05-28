package concurrency.NumberPrint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NumberPrint implements Runnable {

    private int state = 1;
    private int n = 1;
    // 使用lock做锁
    private ReentrantLock lock = new ReentrantLock();
    // 获得lock锁的3个分支条件
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    private Condition c4 = lock.newCondition();

    public void run() {
        // TODO Auto-generated method stub
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 25000; i++) {
                    try {

                        lock.lock();
                        while (state != 1)
                            try {

                                c1.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        for (int j = 0; j < 5; j++) {
                            System.out.println(Thread.currentThread().getName()
                                    + ": " + n++);
                        }
                        System.out.println();
                        state = 2;
                        c2.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }, "线程1").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 25000000; i++) {
                    try {
                        lock.lock();
                        while (state != 2)
                            try {
                                c2.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        for (int j = 0; j < 5; j++) {
                            System.out.println(Thread.currentThread().getName()
                                    + ": " + n++);
                        }
                        System.out.println();
                        state = 3;
                        c3.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }, "线程2").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 25000000; i++) {
                    try {

                        lock.lock();
                        while (state != 3)
                            try {
                                c3.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        for (int j = 0; j < 5; j++) {
                            System.out.println(Thread.currentThread().getName()
                                    + ": " + n++);
                        }
                        System.out.println();
                        state = 4;
                        c4.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }, "线程3").start();


        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 25000000; i++) {
                    try {

                        lock.lock();
                        while (state != 4)
                            try {
                                c4.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        for (int j = 0; j < 5; j++) {
                            System.out.println(Thread.currentThread().getName()
                                    + ": " + n++);
                        }
                        System.out.println();
                        state = 1;
                        c1.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }, "线程4").start();


    }
    public static void main(String[] args) {
        new NumberPrint().run();
    }

}
