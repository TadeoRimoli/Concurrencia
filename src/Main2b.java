import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main2b {
    //punto 2 b

    public static void main(String[] args) {
        Contador contador = new Contador(0,100,100);
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
        }
    }
}
