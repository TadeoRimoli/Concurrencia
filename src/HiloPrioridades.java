import java.util.concurrent.atomic.AtomicInteger;

public class HiloPrioridades extends Thread{

    private AtomicInteger contadorSuma;

    public long getModifications() {
        return modifications;
    }

    public void setModifications(long modifications) {
        this.modifications = modifications;
    }

    private AtomicInteger contadorResta;
    private long max;

    private long modifications;
    public HiloPrioridades(AtomicInteger contadorResta, AtomicInteger contadorSuma, long n) {
        this.contadorSuma=contadorSuma;
        this.contadorResta=contadorResta;
        this.max=n;
        modifications=0;
    }

    @Override
    public void run() {
        long i = 0;
        while (0 < contadorResta.get() || contadorSuma.get() < max) {
            if (contadorSuma.get() < max){
                contadorSuma.compareAndSet(contadorSuma.get(), contadorSuma.get() + 1);
                modifications++;
            }
            if (contadorResta.get() > 0){
                contadorResta.compareAndSet(contadorResta.get(), contadorResta.get() - 1);
                modifications++;
            }
        }
    }
}
