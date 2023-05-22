import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

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
     /*   Contador contador = new Contador(0,100,100);
        Lock lockSuma = new ReentrantLock();
        Lock lockResta = new ReentrantLock();

        Boolean restaLocked=false;
        Boolean sumaLocked=false;
        HiloLock hilo1 = new HiloLock(contador,lockSuma,lockResta,"hilo 1",restaLocked,sumaLocked);
        HiloLock hilo2 = new HiloLock(contador,lockSuma,lockResta,"hilo 2",restaLocked,sumaLocked);
        HiloLock hilo3 = new HiloLock(contador,lockSuma,lockResta,"hilo 3",restaLocked,sumaLocked);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try{
            hilo1.join();
            hilo2.join();
            hilo3.join();
            System.out.println("Modificaciones hilo 1: "+hilo1.getModifications());
            System.out.println("Modificaciones hilo 2: "+hilo2.getModifications());
            System.out.println("Modificaciones hilo 3: "+hilo3.getModifications());
            System.out.println("Contador que deberia estar en 100: "+contador.getContadorSuma());
            System.out.println("Contador que deberia estar en 0: "+contador.getContadorResta());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }*/


        //punto 3
/*        Persona persona = new Persona("Tadeo","Rimoli",EstadoCivil.EnumEstadoCivil.SOLTERO);
        EstadoCivil.EnumEstadoCivil estadoCivil = persona.getEstadoCivil();
        Persona personaActualizada = persona.cambiarEstadoCivil(EstadoCivil.EnumEstadoCivil.CASADO);
        System.out.println(persona.getEstadoCivil());  // Salida: SOLTERO
        System.out.println(personaActualizada.getEstadoCivil());  // Salida: SOLTERO*/

        /*
        * 4. Generar una colección de N enteros con valores aleatorios. Utilizar las siguientes
        clases:
        a) Vector
        b) ArrayList o LinkedList
        c) ArrayList o LinkedList convertido a sincronizado
        d) ConcurrentHashMap
        e) CopyOnWriteArrayList
        • Comparar tiempos de creación y de búsqueda de determinado valor
        • Probar modificaciones y consultas concurrentes.
        • Calcular el promedio de los valores, mediante expresiones lamdba. Resolver
        sin paralelismo y con paralelismo.
        * */

        Vector<Long> vector = GeneradorValores.generarAleatoriosVector(10000000);
        AtomicLong promedio= new AtomicLong();
        promedio.set(0);

        {
            Long endTime;
            Long startTime = System.currentTimeMillis();
            vector.stream().forEach(e -> promedio.set(promedio.longValue()+e));
            endTime=System.currentTimeMillis();
            System.out.println("Tardo en recorrer el vector sin paralelismo: " + (endTime-startTime) +" mili segundos");
            System.out.println(promedio);
            System.out.println(promedio.longValue()/vector.size());
        }
        {
            Long startTime;
            Long endTime;
            AtomicLong promedioParallel= new AtomicLong();
            promedioParallel.set(0);
            startTime = System.currentTimeMillis();
//            Long resutaldo = vector.stream().parallel().reduce(0l,Long::sum).longValue(); //tarda 100 ms

            Long resutaldo = vector.stream().mapToLong(Long::valueOf).sum(); // tarda 50 ms

            endTime=System.currentTimeMillis();
            System.out.println(resutaldo);
            System.out.println("Tardo en recorrer el vector con paralelismo: " + (endTime-startTime) +" mili segundos");

//            System.out.println(promedioParallel);
//            System.out.println(promedioParallel.get()/vector.size());
        }

//        System.out.println(promedio.doubleValue()/vector.size());
//        GeneradorValores.generarAleatoriosList(10000000,false);
//        GeneradorValores.generarAleatoriosList(1000000000,true);
//        GeneradorValores.generarAleatoriosConcurrentHashMap(10000000);
//        GeneradorValores.generarAleatoriosCopyOnWriteArrayList(100000);


        /*
        * con 100000000
        * Vector Tardo 10967 en agregarse
        list normal Tardo 10151 en agregarse
        synchronizedList Tardo 11539 en agregarse
        Concurrent Hash Map tardo 27900 en agregarse
        CopyOnWriteArrayList tardo 3765000 en agregarse
        * */

    }

}