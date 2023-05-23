public class EstadoCivil {
    public enum EnumEstadoCivil {

        SOLTERO(1),
        CASADO(2),
        DIVORCIADO(3),
        VIUDO(4);
        private long value;
        private EnumEstadoCivil(long value) {
            this.value = value;
        }

        public long getValue() {
            return value;
        }
    }

}
