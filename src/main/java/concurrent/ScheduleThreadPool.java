package concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPool {
    public static void main(String[] args) {
        int N = 10;

        for (int i = 11; i < 20; i++) {
            executor.scheduleWithFixedDelay(new MyThread("name"+i), 3L, 3L, TimeUnit.SECONDS);
        }
    }

//    private static void foo(String name) {
//        System.out.println("run() " + (++n));
//        synchronized (executor) {
//            try {
//                executor.wait();
//            } catch (InterruptedException ex) {
//                System.out.println("InterruptedException:{}" + ex);
//            }
//        }
//        System.out.println("finished()");
//    }
    private static int n = 0;
    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private static class MyThread implements Runnable {
        private String name;

        public MyThread(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            for(int i=0; i<10; i++) {
                System.out.println(name + ":" + i);
                if(!name.equals("name12")) {
                    int j = 1/0;
                }

                try {
                    Thread.sleep(1000 *5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
