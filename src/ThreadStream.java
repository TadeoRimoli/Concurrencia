public class ThreadStream extends Thread {
    Stream stream;

    Boolean condicion;

    public Boolean getCondicion() {
        return condicion;
    }

    public Stream getStream() {
        return stream;
    }

    public void setCondicion(Boolean condicion) {
        this.condicion = condicion;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public ThreadStream(Stream stream, Boolean condicion){
        this.stream = stream;
        this.condicion = condicion;
    }


    @Override
    public void run() {
        while(condicion){
            System.out.println(stream.getId());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
