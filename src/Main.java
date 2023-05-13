import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        //punto 1
      /*  int delay=0;
        int k=1;
        long n=2000000;
        long startTime;
        long endTime ;
        long resultado;
        ConcurrenciaRunnable concurrenciaIntance =  new ConcurrenciaRunnable(delay,k,n);
        Thread thread = new Thread(concurrenciaIntance);
                thread.start();

        ConcurrenciaThread miThread = new ConcurrenciaThread(delay,k,n);
        startTime = System.currentTimeMillis();
        miThread.start();
        try{
            miThread.join();
        }catch(Exception e){
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        resultado = miThread.getResultado();
        System.out.println("Resultado: " + resultado);
        System.out.println("Tiempo de ejecución en un hilo solo: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();

        ConcurrenciaThread hilo1 = new ConcurrenciaThread(delay,k, n/3);
        ConcurrenciaThread hilo2 = new ConcurrenciaThread(delay,n/3 + 1, 2*n/3);
        ConcurrenciaThread hilo3 = new ConcurrenciaThread(delay,2*n/3 + 1, n);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        resultado = hilo1.getResultado() + hilo2.getResultado() + hilo3.getResultado();

        endTime = System.currentTimeMillis();
        System.out.println("Resultado: " + resultado);
        System.out.println("Tiempo de ejecución en 3 hilos: " + (endTime - startTime) + " ms");*/

        //punto 2 a
        /*int valorN = 1000;
        AtomicInteger contadorSuma= new AtomicInteger(0);
        AtomicInteger contadorResta= new AtomicInteger(1000);


        HiloPrioridades hilo1 = new HiloPrioridades(contadorResta,contadorSuma,valorN);
        HiloPrioridades hilo2 = new HiloPrioridades(contadorResta,contadorSuma,valorN);
        HiloPrioridades hilo3 = new HiloPrioridades(contadorResta,contadorSuma,valorN);

        hilo1.setPriority(Thread.MAX_PRIORITY); // Prioridad más alta
        hilo2.setPriority(Thread.NORM_PRIORITY); // Prioridad normal
        hilo3.setPriority(Thread.MIN_PRIORITY); // Prioridad más baja

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try{
            hilo1.join();
            hilo2.join();
            hilo3.join();
            System.out.println(hilo1.getModifications());
            System.out.println(hilo2.getModifications());
            System.out.println(hilo3.getModifications());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }*/

        //punto 2 b
        Contador contador = new Contador(0,100,100);
        Lock lockSuma = new ReentrantLock();
        Lock lockResta = new ReentrantLock();

        HiloLock hilo1 = new HiloLock(contador,lockSuma,lockResta,"hilo 1");
        HiloLock hilo2 = new HiloLock(contador,lockSuma,lockResta,"hilo 2");
        HiloLock hilo3 = new HiloLock(contador,lockSuma,lockResta,"hilo 3");

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try{
            hilo1.join();
            hilo2.join();
            hilo3.join();
            System.out.println(hilo1.getModifications());
            System.out.println(hilo2.getModifications());
            System.out.println(hilo3.getModifications());
            System.out.println(contador.getContadorSuma());
            System.out.println(contador.getContadorResta());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }



}