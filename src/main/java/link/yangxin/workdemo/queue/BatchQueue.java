package link.yangxin.workdemo.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * 批量执行的队列，当队列中的数量达到指定数量时，则执行某个操作
 *
 * @author yangxin
 * @date 2021/10/20
 */
public class BatchQueue<T> {

    /**
     * 数量，队列中的数量达到此数时，则队列中的元素提交给执行程序
     */
    private final int batchSize;

    /**
     * 执行者
     */
    private final Consumer<List<T>> consumer;

    /**
     * 超时时间，当队列中的数量没有达到bathSize，且时间超过此时间，则将队列中的元素提交给执行程序
     */
    private final long timeoutInMs;

    private AtomicBoolean isLooping = new AtomicBoolean(false);
    private BlockingQueue<T> queue = new LinkedBlockingQueue<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private AtomicLong start = new AtomicLong(System.currentTimeMillis());

    public BatchQueue(int batchSize, long timeoutInMs, Consumer<List<T>> consumer) {
        this.batchSize = batchSize;
        this.timeoutInMs = timeoutInMs;
        this.consumer = consumer;
    }

    public boolean add(T t) {
        boolean result = queue.add(t);
        if (!isLooping.get() && result) {
            isLooping.set(true);
            startLoop();
        }
        return result;
    }

    public void completeAll() {
        while (!queue.isEmpty()) {
            drainToConsume();
        }
    }

    private void startLoop() {
        executorService.execute(new ExeThread());
    }

    private void drainToConsume() {
        List<T> drained = new ArrayList<>();
        int num = queue.drainTo(drained, batchSize);
        if (num > 0) {
            consumer.accept(drained);
            start.set(System.currentTimeMillis());
        }
    }

    private class ExeThread implements Runnable {
        @Override
        public void run() {
            start = new AtomicLong(System.currentTimeMillis());
            while (true) {
                long last = System.currentTimeMillis() - start.get();
                if (queue.size() >= batchSize || (!queue.isEmpty() && last > timeoutInMs)) {
                    drainToConsume();
                } else if (queue.isEmpty()) {
                    isLooping.set(false);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BatchQueue<String> batchQueue = new BatchQueue<>(1000,10000L, (list) -> {
            System.out.printf("开始处理, %s 条数据, 内容 :%s%n", list.size(), list);
        });

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 102; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "_" + finalI);
                batchQueue.add(finalI + "");
            });
        }

        executorService.shutdown();
    }
}
