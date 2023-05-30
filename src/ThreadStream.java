public class ThreadStream extends Thread {
    Stream stream;

    Boolean condicion;
    public ThreadStream(Stream stream,Boolean condicion){
        this.stream = stream;
        this.condicion = condicion;
    }


    @Override
    public void run() {
        while(condicion){
            System.out.println(stream.getId());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
