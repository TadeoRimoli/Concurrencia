import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

public class Main4 {

    public static void main(String[] args) {

        /*4. Generar una colección de N enteros con valores aleatorios. Utilizar las siguientes clases:
        a) Vector
        b) ArrayList o LinkedList
        c) ArrayList o LinkedList convertido a sincronizado
        d) ConcurrentHashMap
        e) CopyOnWriteArrayList
        • Comparar tiempos de creación y de búsqueda de determinado valor
        • Probar modificaciones y consultas concurrentes.
        • Calcular el promedio de los valores, mediante expresiones lamdba. Resolver
        sin paralelismo y con paralelismo.
        */

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
            Long resutaldo = vector.stream().parallel().reduce(0l,Long::sum).longValue(); //tarda 100 ms

            resutaldo = vector.stream().mapToLong(Long::valueOf).sum(); // tarda 50 ms

            endTime=System.currentTimeMillis();
            System.out.println(resutaldo);
            System.out.println("Tardo en recorrer el vector con paralelismo: " + (endTime-startTime) +" mili segundos");
            System.out.println(promedioParallel);
            System.out.println(promedioParallel.get()/vector.size());
        }

        System.out.println(promedio.doubleValue()/vector.size());
        GeneradorValores.generarAleatoriosList(10000000,false);
        GeneradorValores.generarAleatoriosList(1000000000,true);
        GeneradorValores.generarAleatoriosConcurrentHashMap(10000000);
        GeneradorValores.generarAleatoriosCopyOnWriteArrayList(100000);

        /*
        * con 100000000
        * Vector Tardo 10967 en agregarse
        list normal Tardo 10151 en agregarse
        synchronizedList Tardo 11539 en agregarse
        Concurrent Hash Map tardo 27900 en agregarse
        CopyOnWriteArrayList tardo 3765000 en agregarse
        **/
    }

}
