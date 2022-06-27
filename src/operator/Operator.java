package operator;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Operator implements Callable<String> {
    ConcurrentLinkedQueue<Integer> queue;
    private final int WAIT = 3000;

    public Operator(ConcurrentLinkedQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public String call() throws Exception {

        while (true){
            try {
                Thread.currentThread().sleep(WAIT);
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
            if (queue.size() == 0) return "Все звонки обработаны";

            Integer client = queue.poll();

            if (client != null) System.out.println("Клиент номер " + client +  " - обработан");

        }
    }
}
