import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main10 {

    public static void main(String[] args) {
        int maxThreads=4;
        int maxStreams=8;
        int currentThreads=-1;
        ThreadStream []hilos = new ThreadStream[maxThreads];
        Boolean []condiciones = new Boolean[maxThreads];
        Arrays.fill(condiciones, false);

        List<Stream> streams= new ArrayList<Stream>();
        for(int i=0;i<maxStreams;i++){
            streams.add(new Stream(i,3));
        }
        for(int i=0;i<maxThreads;i++){
            hilos[i]= new ThreadStream(null,condiciones[i]);
        }

        String comando="";
        int streamSeleccionado;
        Scanner scanner = new Scanner(System.in);
        comando=scanner.nextLine();
        while(!comando.equalsIgnoreCase("exit")){

            //si hay lugar
            int aux=0;
            boolean flag=false;
            while(aux<maxThreads && hilos[aux].getStream()!=null){
                    aux++;
            }



            streamSeleccionado=Integer.valueOf(String.valueOf(comando.charAt(comando.length()-1)));
            System.out.println("streamSeleccionado "+streamSeleccionado);

            if(comando.substring(0,comando.length()-1).equalsIgnoreCase("stop-")){
                for(int i=0;i<maxThreads;i++){
                    if(hilos[i].getStream()!=null && hilos[i].getStream().getId()==streamSeleccionado){
                        hilos[i].setCondicion(false);
                        hilos[i].setStream(null);
                    }
                }
                System.out.println("Deteniendo stream "+streamSeleccionado);
            }
            else if(aux==maxThreads){
                System.out.println("No hay hilos disponibles");
            }
            else if(streamSeleccionado < maxStreams && comando.substring(0,comando.length()-1).equalsIgnoreCase("stream-")
                    && streams.get(streamSeleccionado).getMaxUsers() >= streams.get(streamSeleccionado).getCurrentUsers()+1){
                streams.get(streamSeleccionado).setCurrentUsers(streams.get(streamSeleccionado).getCurrentUsers()+1);
                System.out.print("Se agrego el stream " + streamSeleccionado + " al hilo " + aux + "\n");
                hilos[aux].setStream(streams.get(streamSeleccionado));
                hilos[aux].setCondicion(true);
                hilos[aux].start();
            }

            comando=scanner.nextLine();
        }

        for(int i=0;i<maxThreads;i++){
            hilos[i].setCondicion(false);
            hilos[i].setStream(null);
        }

    }



}
