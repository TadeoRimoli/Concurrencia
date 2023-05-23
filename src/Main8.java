import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main8 {
    public static class SistemaReservas {
        private ReadWriteLock lock = new ReentrantReadWriteLock();
        private String reserva;

        public String consultarReserva() {
            lock.readLock().lock();
            try {
                System.out.println("Consultando reserva: " + reserva);
                return reserva;
            } finally {
                lock.readLock().unlock();
            }
        }

        public void realizarReserva(String cliente, String asiento) {
            lock.writeLock().lock();
            try {
                System.out.println("Realizando reserva");
                try {
                    Thread.sleep(1000); // Simulación de tiempo de reserva
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reserva = "Cliente " + cliente + ", Asiento " + asiento;
                System.out.println("Reserva realizada: Cliente " + cliente + ", Asiento " + asiento);
            } finally {
                lock.writeLock().unlock();
            }
        }
    }
    public static void main(String[] args) {
        SistemaReservas sistemaReservas = new SistemaReservas();

        // Hilos de lectores (consultas de reserva)
        Thread lector1 = new Thread(() -> {
            String reserva = sistemaReservas.consultarReserva();
            System.out.println("Lector 1 - Reserva: " + reserva);
        });

        Thread lector2 = new Thread(() -> {
            String reserva = sistemaReservas.consultarReserva();
            System.out.println("Lector 2 - Reserva: " + reserva);
        });

        // Hilo de escritor (realización de reserva)
        Thread escritor1 = new Thread(() -> {
            sistemaReservas.realizarReserva("Julian", "A1");
        });

        Thread escritor2 = new Thread(() -> {
            sistemaReservas.realizarReserva("Tadeo", "B2");
        });

        escritor1.start();
        lector1.start();

        escritor2.start();
        lector2.start();
    }
}
