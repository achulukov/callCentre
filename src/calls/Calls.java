package calls;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Calls extends Thread{
    ConcurrentLinkedQueue<Integer> queue;
    private final int WAIT = 1000;

    public Calls(ConcurrentLinkedQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int count = 0;
        for (int j = 0; j < 5; j++){
            for (int i = 0; i < 10; i++) {
                count++;
                queue.add(count);
            }
            try {
                Thread.sleep(WAIT);
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
