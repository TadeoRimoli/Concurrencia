import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

// Clase para simular una válvula de entrada (Productor)
class ValvulaEntrada implements Runnable {
    private BlockingQueue<Integer> buffer;

    public ValvulaEntrada(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            // Simular el flujo de entrada de datos a la cisterna
            while(true){
                int value = ThreadLocalRandom.current().nextInt(100);
                buffer.put(value);
                System.out.println("Cantidad de datos en el buffer: "+ buffer.size());
                System.out.println("Flujo de entrada: Se agregó el elemento " + value + " a la cisterna.");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Clase para simular una válvula de salida (Consumidor)
class ValvulaSalida implements Runnable {
    private BlockingQueue<Integer> buffer;

    public ValvulaSalida(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            // Simular el drenaje de la cisterna
            while(true){
                int elemento = buffer.take();
                System.out.println("Flujo de salida: Se sacó el elemento " + elemento + " de la cisterna.");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main9 {
    public static void main(String[] args) {
        // Crear el buffer compartido (cisterna) con capacidad para 5 elementos
        BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5);

        // Crear una instancia de la válvula de entrada y la válvula de salida
        ValvulaEntrada valvulaEntrada = new ValvulaEntrada(buffer);
        ValvulaSalida valvulaSalida = new ValvulaSalida(buffer);

        // Crear los hilos para ejecutar las válvulas de entrada y salida
        Thread hiloValvulaEntrada = new Thread(valvulaEntrada);
        Thread hiloValvulaSalida = new Thread(valvulaSalida);

        // Iniciar la simulación
        hiloValvulaEntrada.start();
        hiloValvulaSalida.start();
    }
}