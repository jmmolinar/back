package cl.blackgps.back.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleQueryThreadPool {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 1; i++) {
            Runnable query = new ThreadPeriodoOld("Iteración: " + i);
            executor.execute(query);
          }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finalizados todos los hilos");
    }
    
}
