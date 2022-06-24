import calls.Calls;
import operator.Operator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws  ExecutionException, InterruptedException{
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>(); //Выбрал ConcurrentLinkedQueue, так как есть неблокирующая работа
                                                                              // и все потоки одновременно работают с данными
        Callable<String> operator = new Operator(queue);
        Collection<Callable<String>> operatorList = new ArrayList<>();
        for (int i = 0; i < 4; i++) operatorList.add(operator);


        Thread calls = new Calls(queue);
        calls.start();

        ExecutorService operatorPool = Executors.newFixedThreadPool(4);
        String result = operatorPool.invokeAny(operatorList);

        System.out.println(result);

        operatorPool.shutdown();
    }
}
