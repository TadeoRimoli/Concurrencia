import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// Clase para realizar la suma lenta
class SlowSum {
    // Método de suma lenta
    public static int slowSum(int k, int n) {
        int sum = 0;
        for (int i = k; i <= n; i++) {
            sum += i;
            try {
                Thread.sleep(100); // Pausa de 100 milisegundos para simular una operación lenta
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}

class ThreadPropio extends Thread{
    int resultado;
    public int getResultado() {
        return resultado;
    }

    private CyclicBarrier cyclicBarrier;
    private int k,n;

    public ThreadPropio(CyclicBarrier cyclicBarrier,int k,int n){
        this.cyclicBarrier=cyclicBarrier;
        this.k=k;
        this.n=n;
    }

    @Override
    public void run() {

        System.out.println("Hilo " + getName() + " iniciado.");
        resultado = SlowSum.slowSum(k, n);
        System.out.println("Hilo " + getName() + " finalizado. Resultado: " + resultado);

        try {
            cyclicBarrier.await(); // Esperar a que todos los hilos alcancen el punto de encuentro
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

public class Main7 {
    public static void main(String[] args) {
        int k = 1;
        int n = 100;
        int numThreads = 3;
        CyclicBarrier barrier = new CyclicBarrier(numThreads);

        System.out.println("Ejecución utilizando subclase de Thread:");
        long startTime = System.currentTimeMillis();

        ThreadPropio[] threads = new ThreadPropio[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i]  = new ThreadPropio(barrier,k,n);
            threads[i].start();
        }

        try {
            Long resultadoTotal=0l;
            barrier.await(); // Esperar a que todos los hilos alcancen el punto de encuentro
            for (ThreadPropio thread : threads) {
                resultadoTotal+=thread.getResultado();
            }
            System.out.println("Resultado Total "+resultadoTotal);

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo total: " + (endTime - startTime) + " ms");
    }
}