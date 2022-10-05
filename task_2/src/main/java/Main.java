import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> myCallable1 = () -> {
            int i = 0;
            while (i < 14) {
                Thread.sleep(500);
                System.out.println("Поток" + Thread.currentThread().getName() + ". Всем привет!");
                i++;
            }
            return i;
        };
        Callable<Integer> myCallable2 = () -> {
            int i = 0;
            while (i < 14) {
                Thread.sleep(500);
                System.out.println("Поток" + Thread.currentThread().getName() + ". Еще раз привет!");
                i++;
            }
            return i;
        };
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Callable<Integer>> collables = new ArrayList<>();
        collables.add(myCallable1);
        collables.add(myCallable2);

        pool.submit(myCallable1);
        pool.submit(myCallable2);
        int count = pool.invokeAny(collables);
        pool.shutdown();
        System.out.println("Прекращаем потоки. Количество фраз:" + count);
    }
}
