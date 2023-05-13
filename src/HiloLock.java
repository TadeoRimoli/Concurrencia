import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class HiloLock extends Thread{


    public long getModifications() {
        return modifications;
    }
    private long modifications;
    private Contador contador;
    private Lock lockSuma;
    private Lock lockResta;

    public HiloLock(Contador contador,  Lock lockSuma,  Lock lockResta,String nombre) {
        this.contador=contador;
        this.lockSuma=lockSuma;
        this.lockResta=lockResta;
        modifications=0;
        this.setName(nombre);
    }

    @Override
    public void run() {
        long i = 0;
        while (0 < contador.getContadorResta() || contador.getContadorSuma() < contador.getMax()) {
            if (contador.getContadorSuma() < contador.getMax()){
                lockSuma.lock();
                contador.setContadorSuma(contador.getContadorSuma()+1);
                lockSuma.unlock();
                modifications++;
            }
            if (contador.getContadorResta() > 0){
                lockResta.lock();
                contador.setContadorResta(contador.getContadorResta()-1);
                lockResta.unlock();
                modifications++;
            }
        }
    }
}
