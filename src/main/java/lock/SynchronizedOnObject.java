package lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SynchronizedOnObject {
    private ConcurrentHashMap<String, Object> autoCheckLocks = new ConcurrentHashMap<>();
    ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        SynchronizedOnObject soo = new SynchronizedOnObject();
        soo.test();


    }

    private void test() {
        autoCheckLocks.put("AAA", new Object());
        autoCheckLocks.put("BBB", new Object());
//        autoCheckLocks.put("CCC", new Object());
        executor.submit(() -> {
            runWithLock("AAA");
        });

        executor.submit(() -> {
            runWithLock("AAA");
        });

        executor.submit(() -> {
            runWithLock("BBB");
        });
        System.out.println("Thread is exiting..." + Thread.currentThread().getName());
    }

    private void runWithLock(String lockName) {
        Object lock = autoCheckLocks.get(lockName);
        synchronized (lock) {
            IntStream.range(0,10).forEach(i -> {
                System.out.println("lockName:" + lockName+ ", ThreadName:" + Thread.currentThread().getName() + ", current i:" + i);
                try {
                    Thread.sleep(1000);
                    System.out.println("lockName:" + lockName + ", ThreadName:" + Thread.currentThread().getName() +", current i:" + i + ", finished sleep.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("Thread is exiting..." + Thread.currentThread().getName());

    }

}
