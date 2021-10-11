package cl.blackgps.back.threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledQueryThreadPool {

    public static void main(String[] args) {

        //int numDeHilos = Runtime.getRuntime().availableProcessors();

        ScheduledExecutorService ser = Executors.newScheduledThreadPool(1);
        ser.schedule(new QueryThread("Hilo: " + Thread.currentThread().getId()), 1, TimeUnit.SECONDS);
        ser.scheduleAtFixedRate(new QueryThread("Hilo: " + Thread.currentThread().getId()), 1, 4, TimeUnit.SECONDS);

        System.out.println("Finalizados todos los hilos");
    }
    
}
