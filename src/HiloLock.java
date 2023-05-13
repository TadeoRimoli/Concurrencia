import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class HiloLock extends Thread{


    public long getModifications() {
        return modifications;
    }
    private long modifications;
    private Contador contador;
    private Lock lockSuma;
    private Lock lockResta;

    private Boolean restaLocked,sumaLocked;

    public HiloLock(Contador contador,  Lock lockSuma,  Lock lockResta,String nombre,Boolean restaLocked,Boolean sumaLocked) {
        this.contador=contador;
        this.lockSuma=lockSuma;
        this.lockResta=lockResta;
        modifications=0;
        this.restaLocked=restaLocked;
        this.sumaLocked=sumaLocked;
        this.setName(nombre);
    }

    @Override
    public void run() {
        long i = 0;
        Condition esperaSuma = lockSuma.newCondition();
        Condition esperaResta = lockResta.newCondition();
        while (0 < contador.getContadorResta() || contador.getContadorSuma() < contador.getMax()) {
            if (contador.getContadorSuma() < contador.getMax()){
                lockSuma.lock();
                sumaLocked=true;
                contador.setContadorSuma(contador.getContadorSuma()+1);
                 lockSuma.unlock();
                //Para probar el DEADLOCK PUNTO 2 C DESCOMENTAR EL CODIGO DE ABAJO
                /*
                if(!sumaLocked)
                    lockSuma.unlock();
                else{
                    try {
                        while (sumaLocked){
                            System.out.println("Esperando que se libere el recurso para restar...."+ this.getName());
                            esperaSuma.await();
                            System.out.println("Salimoo...." + this.getName());
                        }
                    }catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    sumaLocked=false;
                    lockSuma.unlock();
                }*/
                }
                modifications++;

                if (contador.getContadorResta() > 0){
                    lockResta.lock();
                    contador.setContadorResta(contador.getContadorResta()-1);
                    lockResta.unlock();


                    //Para probar el DEADLOCK PUNTO 2 C DESCOMENTAR EL CODIGO DE ABAJO
                    /*     if(!restaLocked)
                        lockResta.unlock();
                    else{
                        try {
                            while (restaLocked){
                                System.out.println("Esperando que se libere el recurso para sumar...."+ this.getName());
                                esperaSuma.await();
                                System.out.println("Salimoo...." + this.getName());
                            }
                        }catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        restaLocked=false;
                        lockResta.unlock();
                    }*/
                    modifications++;
                }
            }
    }
}

