import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main10 {

    public static void main(String[] args) {
        int maxThreads=4;
        int currentThreads=-1;
        Thread []hilos = new Thread[maxThreads];
        Boolean []condiciones = new Boolean[maxThreads];
        Arrays.fill(condiciones, false);

        List<Stream> streams= new ArrayList<Stream>();
        for(int i=0;i<8;i++){
            streams.add(new Stream(i,3));
        }


        String comando="";
        Scanner scanner = new Scanner(System.in);

        while(!comando.equalsIgnoreCase("exit")){
            System.out.println("entro.");

            comando=scanner.nextLine();
            if(currentThreads==maxThreads){
                System.out.println("No se puede conectar al stream ya que no hay mas hilos disponibles.");
            }else{
                currentThreads++;
                if(comando.equalsIgnoreCase("stream-1")){
                    condiciones[currentThreads]=true;
                    hilos[currentThreads]= new ThreadStream(streams.get(1),condiciones[currentThreads]);
                    hilos[currentThreads].start();
                }
            }

        }

        if(currentThreads>=0){
            System.out.println("ENTRO IF");
        }

    }



}
