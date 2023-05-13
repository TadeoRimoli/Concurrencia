import java.text.SimpleDateFormat;
import java.util.Date;

public class ConcurrenciaRunnable implements Runnable {

    ConcurrenciaRunnable(int delay,int k,int n){
        this.delayMillis=delay;
        this.k=k;
        this.n=n;
    }
    int delayMillis;int k; int n;
    public void test(){
        int sum = 0;

        if(k>n){
            System.out.println("K es mayor a N");
        }else{
            System.out.println("Hora de inicio: " + new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
            for (int i = k; i <= n; i++) {
                sum += i;
                try {
                    System.out.println("suma hasta el momento: " + sum+"...");
                    Thread.sleep(delayMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Hora de fin: " + new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
        }
    }

    @Override
    public void run() {
        test();
    }
}
