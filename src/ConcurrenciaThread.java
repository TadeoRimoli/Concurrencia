import java.text.SimpleDateFormat;
import java.util.Date;

public class ConcurrenciaThread extends Thread{
    int delayMillis;long k; long n;
    long sum=0;
    ConcurrenciaThread(int delay,long k,long n){
        this.delayMillis=delay;
        this.k=k;
        this.n=n;
    }
    public long getResultado(){
        return sum;
    }
    public void test(){

        if(k>n){
            System.out.println("K es mayor a N");
        }else{
//            System.out.println("Hora de inicio: " + new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
            for (long i = k; i <= n; i++) {
                sum += i;
                try {
//                    System.out.println("suma hasta el momento: " + sum+"...");
                    Thread.sleep(delayMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            System.out.println("Hora de fin: " + new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
        }
    }

    public void run() {
        test();
    }
}
