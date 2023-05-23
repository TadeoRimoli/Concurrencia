import java.util.concurrent.Semaphore;

public class Main2 {
    private static final int CAPACIDAD_MAXIMA = 100;
    private static Semaphore semaforo = new Semaphore(CAPACIDAD_MAXIMA);

    public static void main(String[] args) {
        Thread sensorEntrada1 = new Thread(new SensorEntrada("Sensor Entrada 1"));
        Thread sensorEntrada2 = new Thread(new SensorEntrada("Sensor Entrada 2"));
        Thread sensorSalida1 = new Thread(new SensorSalida("Sensor Salida 1"));
        Thread sensorSalida2 = new Thread(new SensorSalida("Sensor Salida 2"));

        sensorEntrada1.start();
        sensorEntrada2.start();
        sensorSalida1.start();
        sensorSalida2.start();
    }

    static class SensorEntrada implements Runnable {
        private String nombreSensor;

        public SensorEntrada(String nombreSensor) {
            this.nombreSensor = nombreSensor;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    semaforo.acquire();
                    System.out.println(nombreSensor + " - Persona ingresando. Capacidad disponible: " + semaforo.availablePermits());
                    Thread.sleep(2000); // Simulación de tiempo de ingreso
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaforo.release();
                }
            }
        }
    }

    static class SensorSalida implements Runnable {
        private String nombreSensor;

        public SensorSalida(String nombreSensor) {
            this.nombreSensor = nombreSensor;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    semaforo.acquire();
                    System.out.println(nombreSensor + " - Persona saliendo. Capacidad disponible: " + semaforo.availablePermits());
                    Thread.sleep(1500); // Simulación de tiempo de salida
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaforo.release();
                }
            }
        }
    }
}