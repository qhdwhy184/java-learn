package concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 16/4/25.
 */
public class ProducerConsumer {
    ReentrantLock lock = new ReentrantLock();
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();
    Queue<Integer> q = new ArrayDeque<>();

    public void put(Integer i) throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();
        try{

            while(q.size() == 1) {
                System.out.println("Thread name:"+ Thread.currentThread().getName() + " wait notFull!");
                notFull.await();
                System.out.println("Thread name:"+ Thread.currentThread().getName() + " get out from wait notFull!");
            }
            q.add(i);
            System.out.println("Thread name:"+ Thread.currentThread().getName() + " put Integer:" + i);
            notEmpty.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        lock.lock();
        try{
            while(q.size() == 0) {
                System.out.println("Thread name:"+ Thread.currentThread().getName() + " wait notEmpty!");
                notEmpty.await();
                System.out.println("Thread name:"+ Thread.currentThread().getName() + " get out from wait notEmpty!");
            }
            Integer i = q.remove();
            System.out.println("Thread name:"+ Thread.currentThread().getName() + " got Integer:" + i);
            notFull.signal();
        }finally {
            lock.unlock();
        }
    }

    private final static Logger logger = LoggerFactory.getLogger(ProducerConsumer.class);

    public static void main(String[] args) {

        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 1, TimeUnit.MILLISECONDS, queue,
                new ThreadFactoryBuilder().setNameFormat("HelloThread-%d").build(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("core thread is "+executor.getActiveCount()+",waiting task is "+executor.getQueue().size());
            }
        });

        final ProducerConsumer pc = new ProducerConsumer();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.put(1);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.take();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.take();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });


    }



}