package concurrency.IntroToMulticoreProgramming;

/**
 * Created by sherry on 2017/5/23.
 *
 * Sharing Data Across Java Threads：
 * Can pass an object instance to the child thread constructor,
 * and retain that object instance in a data member
 */
public class Main5 {
    public static void main(String [] args) {
        SharedData data = new SharedData();
        MyThread5 t1 = new MyThread5(data);
        t1.start();

        for (int i = 0;i < 20;i++) {
            System.out.println(data.a--);
        }
    }
    static class SharedData {
        public int a = 0;
        public String s = null;

        public SharedData() {
            a = 10;
            s = "Test";
        }
    }

    static class MyThread5 extends Thread {
        private SharedData m_data = null;

        public MyThread5(SharedData data) {
            m_data = data;
        }

        public void run() {
            for (int i = 0;i < 20;i++) {
                m_data.a++;
                System.out.println(m_data.a++);
            }
        }
    }


}
