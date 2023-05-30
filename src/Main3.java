public class Main3 {

    public static void main(String[] args) {
        Persona persona = new Persona("Tadeo","Rimoli",EstadoCivil.EnumEstadoCivil.SOLTERO);
        EstadoCivil.EnumEstadoCivil estadoCivil = persona.getEstadoCivil();
        Persona personaActualizada = persona.cambiarEstadoCivil(EstadoCivil.EnumEstadoCivil.CASADO);
        System.out.println(persona.getEstadoCivil());  // Salida: SOLTERO
        System.out.println(personaActualizada.getEstadoCivil());  // Salida: SOLTERO
    }

}
