import java.util.concurrent.atomic.AtomicInteger;

public class Main2a {
    //punto 2 a
    public static void main(String[] args) {
        int valorN = 1000;
        AtomicInteger contadorSuma= new AtomicInteger(0);
        AtomicInteger contadorResta= new AtomicInteger(1000);

        HiloPrioridades hilo1 = new HiloPrioridades(contadorResta,contadorSuma,valorN);
        HiloPrioridades hilo2 = new HiloPrioridades(contadorResta,contadorSuma,valorN);
        HiloPrioridades hilo3 = new HiloPrioridades(contadorResta,contadorSuma,valorN);

        Thread aux= new Thread();


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
    }

}
