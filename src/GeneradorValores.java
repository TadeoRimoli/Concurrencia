import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class GeneradorValores {

    public static Vector generarAleatoriosVector(long n){
        Vector<Long> vector = new Vector<Long>();
        long startTime= System.currentTimeMillis(); //segundos
        long endTime=0;
        for (int i=0;i<n;i++){
            vector.add(getRandomValue());
        }
        endTime= System.currentTimeMillis(); //segundos
        Long tiempo= (endTime - startTime);
        System.out.println("Vector Tardo " + tiempo + " en agregarse");
        return vector;
    }

    public static void generarAleatoriosList(long n,boolean syncronized){
        List list= new ArrayList<>();
        if(syncronized){
            list = Collections.synchronizedList(list);
        }

        long startTime= System.currentTimeMillis(); //segundos
        long endTime=0;
        for (int i=0;i<n;i++){
            list.add(getRandomValue());
        }
        endTime= System.currentTimeMillis(); //segundos
        Long tiempo= (endTime - startTime);
        System.out.println((syncronized ? "synchronizedList" : "list normal") + " Tardo " + tiempo + " en agregarse");
    }

    public static void generarAleatoriosConcurrentHashMap(long n){
        ConcurrentHashMap<Integer,Long> map= new ConcurrentHashMap<Integer,Long>();

        long startTime= System.currentTimeMillis(); //segundos
        long endTime=0;
        for (int i=0;i<n;i++){
            map.put(i,getRandomValue());
        }
        endTime= System.currentTimeMillis(); //segundos
        Long tiempo= (endTime - startTime);
        System.out.println("Concurrent Hash Map tardo " + tiempo + " en agregarse");
    }

    public static void generarAleatoriosCopyOnWriteArrayList(long n){
        List<Long> list = new CopyOnWriteArrayList<Long>();

        long startTime= System.currentTimeMillis(); //segundos
        long endTime=0;
        for (int i=0;i<n;i++){
            list.add(i,getRandomValue());
            System.out.println("Valor agregado");
        }

        endTime= System.currentTimeMillis(); //segundos
        Long tiempo= (endTime - startTime);
        System.out.println("CopyOnWriteArrayList tardo " + tiempo + " en agregarse");
    }

    private static long getRandomValue(){
        Random random = new Random();
        return random.nextInt(0,1000);
    }
}
