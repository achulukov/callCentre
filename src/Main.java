import calls.Calls;
import operator.Operator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws  ExecutionException, InterruptedException{
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        Callable<String> operator = new Operator(queue);
        Collection<Callable<String>> operatorList = new ArrayList<>();
        operatorList.add(operator);
        operatorList.add(operator);
        operatorList.add(operator);
        operatorList.add(operator);

        Thread calls = new Calls(queue);
        calls.start();

        ExecutorService operatorPool = Executors.newFixedThreadPool(4);
        String result = operatorPool.invokeAny(operatorList);

        System.out.println(result);

        operatorPool.shutdown();
    }
}
