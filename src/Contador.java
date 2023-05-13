class Contador {
    private long contadorSuma;
    private long contadorResta;
    private long max;

    public void setMax(long max) {
        this.max = max;
    }

    public void setContadorSuma(long contadorSuma) {
        this.contadorSuma = contadorSuma;
    }

    public void setContadorResta(long contadorResta) {
        this.contadorResta = contadorResta;
    }

    public Contador(long contadorSuma, long contadorResta, long max) {
        this.contadorSuma = contadorSuma;
        this.contadorResta = contadorResta;
        this.max = max;
    }

    public long getContadorSuma() {
        return contadorSuma;
    }

    public long getContadorResta() {
        return contadorResta;
    }

    public long getMax() {
        return max;
    }
}