import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Thread> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("My group");
        list.add(new MyThread(threadGroup, "Thread 1"));
        list.add(new MyThread(threadGroup, "Thread 2"));
        list.add(new MyThread(threadGroup, "Thread 3"));
        list.add(new MyThread(threadGroup, "Thread 4"));
        System.out.println("Запускаем потоки");
        list.forEach(Thread::start);

        Thread.sleep(6_000);
        System.out.println("Прекращаем потоки");
        threadGroup.interrupt();
    }
}
