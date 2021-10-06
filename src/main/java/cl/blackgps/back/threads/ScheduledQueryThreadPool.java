package cl.blackgps.back.threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledQueryThreadPool {

    public static void main(String[] args) {

        ScheduledExecutorService ser = Executors.newScheduledThreadPool(5);
        ser.schedule(new QueryThread("Hilo: " + Thread.currentThread().getId()), 2, TimeUnit.SECONDS);
        ser.scheduleAtFixedRate(new QueryThread("Hilo: " + Thread.currentThread().getId()), 2, 5, TimeUnit.SECONDS);

        System.out.println("Finalizados todos los hilos");
    }
    
}
