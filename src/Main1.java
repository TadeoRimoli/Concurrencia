public class Main1 {
    public static void main(String[] args) {
        int delay=0;
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
        System.out.println("Tiempo de ejecución en 3 hilos: " + (endTime - startTime) + " ms");
    }
}
