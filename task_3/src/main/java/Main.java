import java.util.Arrays;
import java.util.concurrent.*;

// Возможно я что-то не так написал, но по моим наблюдениям без Future в основном быстрее, независимо от размеров
public class Main {
    static int[] arr;

    static Callable<Integer> call = () -> reqSum(arr);
    static ForkJoinPool pool = new ForkJoinPool(16);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        arr = generate(20_000_000);

        System.out.println("Начало подсчета...");
        long millis = System.currentTimeMillis();
        boolean flag = true;
        if (flag) {
            Future<Integer> task = pool.submit(call);
            int sum = task.get();
            millis = System.currentTimeMillis() - millis;
            System.out.println("Прошло времени " + millis + " ms \nСумма = " + sum);
            pool.shutdown();
        } else {
            int sum = reqSum(arr);
            millis = System.currentTimeMillis() - millis;
            System.out.println("Прошло времени " + millis + " ms \nСумма = " + sum);
        }

    }

    static int[] generate(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }

    static int reqSum(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        return reqSum(Arrays.stream(arr).limit(arr.length / 2).toArray()) + reqSum(Arrays.stream(arr).skip(arr.length / 2).toArray());
    }
}
