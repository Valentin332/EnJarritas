package tp.enJarritas;

import java.util.HashMap;

public final class App {
    private App() {
    }
    public static void main(String[] args) {
      Tienda enJarritas = new Tienda("enJarritas", 30, 40000f);


      System.out.println(enJarritas.getNombre());
      System.out.println(enJarritas.getSaldoEnCaja());
       System.out.println(enJarritas.getInventario());
        //AGREGAR PRODUCTOS A LA TIENDA
       Bebida cocaCola = new Bebida(
        "AC001",
        "Coca Cola",
        "es una bebida azucarada gaseosa vendida a nivel mundial en tiendas, restaurantes y máquinas expendedoras en más de doscientos países o territorios.",
        15,
        12.50f,
        8.50f,
        true,
        "USA",
        false,
        0.00f,
        (short)210,
        "24/8/2023"
        );
        Bebida pepis = new Bebida(
        "AC002",
        "pepis",
        "es una bebida azucarada gaseosa vendida a nivel mundial en tiendas, restaurantes y máquinas expendedoras en más de doscientos países o territorios.",
        15,
        12.50f,
        8.50f,
        true,
        "USA",
        false,
        0.00f,
        (short)210,
        "22/8/2023"
        );




         deLimpieza ariel = new deLimpieza(
        "AZ002",
        "Ariel",
        "Descripción. Ariel Limpieza Profunda líquido para lavar ropa blanca y de color posee doble poder quitamanchas, los ingredientes activos de su fórmula penetran las fibras profundamente para remover manchas comunes y difíciles mientras cuida tu ropa.",
        5,
        18.20f,
        16.00f,
        "ropa"
        );

        Envasado cheesyAlfredo = new Envasado(
        "AB003",
        "Cheesy Alfredo Pasta Box",
        "una caja de pasta qcyo",
        8,
        8f,
        7f,
        false,
        "Argentina",
        (short)200,
        "18/9/2023",
        "carton"
        );

      enJarritas.agregarProducto(cocaCola, cocaCola.getCantidadEnStock());
      enJarritas.agregarProducto(ariel, ariel.getCantidadEnStock());
      enJarritas.agregarProducto(pepis, pepis.getCantidadEnStock());
      enJarritas.agregarProducto(cheesyAlfredo, cheesyAlfredo.getCantidadEnStock());
      

      // realizar una compra
      HashMap<String, Integer> listaTest = new HashMap<String, Integer>();
      listaTest.put("AB003",3);
      listaTest.put("AZ002",3);

      HashMap<String, Float> listaTestDescuento = new HashMap<String, Float>();
      listaTestDescuento.put("AC002",0f);
      listaTestDescuento.put("AZ002",0f);
    
      OrdenDeCompra testOrden = new OrdenDeCompra(listaTest, listaTestDescuento, enJarritas);
      System.out.println( "saldo en caja actual " + enJarritas.getSaldoEnCaja());
      enJarritas.venderProductos(testOrden);
      System.out.println("saldo en caja actual " + enJarritas.getSaldoEnCaja());

         

    }
}
