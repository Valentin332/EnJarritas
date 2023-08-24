package tp.enJarritas;

public final class App {
    private App() {
    }
    public static void main(String[] args) {
    short cantidadStock = 30;
    double plataInicial = 40.000;
      Tienda enJarritas = new Tienda("enJarritas", cantidadStock, plataInicial);

      System.out.println(enJarritas.getNombre());



    }
}
