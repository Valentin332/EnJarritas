package tp.enJarritas;
import java.util.Scanner;
import java.util.HashMap;

public final class App {
    private App() {
    }
    public static void main(String[] args) {
      Tienda enJarritas = new Tienda("enJarritas", 1000, 40000f);
      Scanner testInputScan = new Scanner(System.in);


        

        // PRODUCTOS DE EJEMPLO EN EL INVENTARIO
        //3 BEBIDAS
       Bebida cocaCola = new Bebida(
        "AC001",
        "Coca Cola 500ML",
        "es una bebida azucarada gaseosa vendida a nivel mundial en tiendas, restaurantes y máquinas expendedoras en más de doscientos países o territorios.",
        15,
        13.50f,
        8.50f,
        true,
        "USA",
        false,
        0.00f,
        (short)210,
        "24/8/2023",
        15f
        );
        Bebida pepis = new Bebida(
        "AC002",
        "pepis 500ML",
        "es una bebida azucarada gaseosa vendida a nivel mundial en tiendas, restaurantes y máquinas expendedoras en más de doscientos países o territorios.",
        15,
        12.50f,
        8.50f,
        true,
        "USA",
        false,
        0.00f,
        (short)220,
        "22/8/2023",
        10f
        );
        Bebida manaos = new Bebida(
        "AC003",
        "manaos uva 500Ml",
        "quinto compuesto de la marca, con ingredientes ácido cítrico y jugo de uva.",
        30,
        9.50f,
        5.50f,
        false,
        "Argentina",
        false,
        0.00f,
        (short)190,
        "30/8/2023",
        5f
        );

        //3 PRODUCTOS DE LIMPIEZA
        deLimpieza ariel = new deLimpieza(
        "AZ001",
        "Ariel limpieza profunda",
        "Descripción. Ariel Limpieza Profunda líquido para lavar ropa blanca y de color posee doble poder quitamanchas, los ingredientes activos de su fórmula penetran las fibras profundamente para remover manchas comunes y difíciles mientras cuida tu ropa.",
        5,
        18.20f,
        16.00f,
        2f,
        "ropa"
        );
        deLimpieza ala = new deLimpieza(
        "AZ002",
        "Ala Detergente Lavavajillas Ultra Limon",
        "s un detergente lavavajillas semi concentrado que contiene agentes de limpieza que te ayudan en la limpieza diaria de tu vajilla, removiendo la grasa y la suciedad logrando un resultado brillante y un desengrase total de tus platos.",
        10,
        14.20f,
        12.00f,
        10f,
        "ropa"
        );

        deLimpieza escobaVirulana = new deLimpieza(
        "AZ003",
        "Escoba interiores virulana",
        "Barre facil todo tipo de pisos. Ideal para pisos semi-plasticos, ceramicos, patios y terrazas.",
        7,
        20.00f,
        17.00f,
        0f,
        "ropa"
        );

        //3 ENVASADOS

        Envasado cheesyAlfredo = new Envasado(
        "AB001",
        "Cheesy Alfredo Pasta Box",
        "Contains pasta, sauce and seasonings. Single serving pack",
        8,
        8f,
        7f,
        true,
        "India",
        (short)237,
        "18/9/2023",
        "carton",
        0f
        );
        Envasado alcoDuraznos = new Envasado(
        "AB002",
        "Duraznos Alco",
        "Duraznos En Mitades Enlatados Conserva Alco 820g",
        10,
        10f,
        9f,
        false,
        "Argentina",
        (short)400,
        "24/9/2023",
        "lata",
        0f
        );
        Envasado tomatesInca = new Envasado(
        "AB003",
        "Tomates triturados Inca",
        "Tomates triturados Inca para salsa 950g",
        11,
        13f,
        11f,
        true,
        "Mexico",
        (short)300,
        "30/10/2023",
        "vidrio",
        5f
        );

      enJarritas.agregarProducto(cocaCola.getCantidadEnStock(),  cocaCola );
      enJarritas.agregarProducto(pepis.getCantidadEnStock(),  pepis );
      enJarritas.agregarProducto(manaos.getCantidadEnStock(),  manaos );

      enJarritas.agregarProducto(ariel.getCantidadEnStock(), ariel);
      enJarritas.agregarProducto(ala.getCantidadEnStock(), ala);
      enJarritas.agregarProducto(escobaVirulana.getCantidadEnStock(), escobaVirulana);

      enJarritas.agregarProducto(cheesyAlfredo.getCantidadEnStock(), cheesyAlfredo);
      enJarritas.agregarProducto(alcoDuraznos.getCantidadEnStock(), alcoDuraznos);
      enJarritas.agregarProducto(tomatesInca.getCantidadEnStock(), tomatesInca);
      
      //CONSIDERACIONES ESPECIALES
       // enJarritas.listarProductosConUtilidadesInferiores();
        // enJarritas.obtenerComestiblesConMenorDescuento();

      // TEST DE COMPRA, ADICION Y VENTA
      System.out.println("Hola, bienvenido a " + enJarritas.getNombre() + " ¿Que quieres hacer? "); 
      System.out.println("Ingrese 1 para reponer stock de un producto");
      System.out.println("ingrese 2 para  realizar una compra"); 
      System.out.println("ingrese 3 para añadir un nuevo producto al inventario");
      

       byte opcion = testInputScan.nextByte();
      
       if(opcion == 1){
        App.mostrarInventario(enJarritas);

        System.out.println("Ingrese el codigo del producto que desea reponer y luego la cantidad");
        String codigo = testInputScan.next();
        Producto producto = enJarritas.getInventario().get(codigo);
        Integer cantidad = testInputScan.nextInt();

        enJarritas.comprarProducto(cantidad, producto);
        System.out.println("SALDO EN CAJA ACTUAL: " + enJarritas.getSaldoEnCaja());
        testInputScan.close();


       } else if(opcion == 2){
        App.mostrarInventario(enJarritas);
        System.out.println("Cuantos productos distintos desea comprar? (1-3)");
        byte cantidadProductos = testInputScan.nextByte();

        if(cantidadProductos < 1 && cantidadProductos > 3) {
          testInputScan.close();
          throw new Error("Por favor ingrese un valor entre 1 y 3");
        } else {
          HashMap <String, Integer> listaDeProductos = new HashMap<String, Integer>();
          HashMap <String, Boolean> listaDeDescuentos =  new HashMap<String, Boolean>();

          for(int contar = 0; contar < cantidadProductos; contar++){
            System.out.println("Ingrese el codigo del producto y luego cuantas unidades desea (1-10)");
            String codigo = testInputScan.next();
            Integer cantidad = testInputScan.nextInt();
            System.out.println("Desea acceder a un descuento ? responda true para si y false para no");
            Boolean descuento = testInputScan.nextBoolean();
            listaDeProductos.put(codigo, cantidad);
            listaDeDescuentos.put(codigo, descuento);

          }
          OrdenDeCompra testOrden = new OrdenDeCompra(listaDeProductos, listaDeDescuentos, enJarritas);
          enJarritas.venderProductos(testOrden);

         System.out.println("SALDO EN CAJA ACTUAL: " + enJarritas.getSaldoEnCaja());
          }
      testInputScan.close();
      } 
       
      else if(opcion == 3){
      App.mostrarInventario(enJarritas);
      System.out.println("¿Que tipo de producto desea agregar?");
      System.out.println("Presione 1 para producto de limpieza");
      System.out.println("Presione 2 para producto envasado");
      System.out.println("Presione 3 para agregar una bebida");
      byte tipoDeProducto = testInputScan.nextByte();

      if(tipoDeProducto > 3 || tipoDeProducto < 1){
        testInputScan.close();
        throw new Error("Por favor ingrese un numero entre el 1 y el 3");
      }

       System.out.println("Ingrese el nombre del producto");
       String nombre = testInputScan.next();
       testInputScan.nextLine();
    

       System.out.println("Ingrese la cantidad en stock inicial del producto");
       Integer cantidadEnStock = testInputScan.nextInt();

       System.out.println("ingrese la descripcion del producto");
       String descripcion = testInputScan.nextLine();
       testInputScan.nextLine();

       System.out.println("Ingrese el costo por unidad del producto");
       Float costoUnidad = testInputScan.nextFloat();

       System.out.println("Ingrese el precio por unidad del producto");
       Float precioUnidad = testInputScan.nextFloat();

      if( tipoDeProducto == 1){
         System.out.println("Ingrese un ID de longitud 5 que respete el formato AZXXX donde XXX son digitos");
       String id = testInputScan.next();
        System.out.println("Cual es el tipo de USO del producto de limpieza? Puede ser de valor : COCINA, PISOS, ROPA, MULTIUSO");
        String uso = testInputScan.next();
        System.err.println("¿Cual es el porcentaje de descuento que puede tener el producto? Si no admite descuentos, ingrese 0");
        Float descuento = testInputScan.nextFloat();

        deLimpieza productoNuevo = new deLimpieza(id, nombre, descripcion, cantidadEnStock, precioUnidad, costoUnidad, descuento, uso);
        enJarritas.agregarProducto(productoNuevo.getCantidadEnStock(), productoNuevo);
        App.mostrarInventario(enJarritas);
        
      } else if(tipoDeProducto == 2){
         System.out.println("Ingrese un ID de longitud 5 que respete el formato ABXXX donde XXX son digitos");
        String id = testInputScan.next();

        System.out.println("El producto es importado? Ingrese true para si, false para no");
        Boolean esImportado = testInputScan.nextBoolean();

        System.out.println("CUal es el origen del producto?");
        String origen = testInputScan.nextLine();
        testInputScan.nextLine();

        System.out.println("¿Cuantas calorias tiene el producto?");
        Short calorias = testInputScan.nextShort();

        System.out.println("Ingrese la fecha de vencimiento del producto");
        String vencimiento = testInputScan.nextLine();
        testInputScan.nextLine();

        System.out.println("¿Cual es el tipo de envase? Los envases permitidos son : PLASTICO, LATA, VIDRIO, PET");
        String envase = testInputScan.next();

        System.err.println("¿Cual es el porcentaje de descuento que puede tener el producto? Si no admite descuentos, ingrese 0");
        Float descuento = testInputScan.nextFloat();

        Envasado productoNuevo = new Envasado(id, nombre, descripcion, cantidadEnStock, precioUnidad, costoUnidad, esImportado, origen, calorias, vencimiento, envase, descuento);
        enJarritas.agregarProducto(productoNuevo.getCantidadEnStock(), productoNuevo);
        App.mostrarInventario(enJarritas);
        
      } else if (tipoDeProducto == 3){
         System.out.println("Ingrese un ID de longitud 5 que respete el formato ACXXX donde XXX son digitos");
       String id = testInputScan.next();

         System.out.println("El producto es importado? Ingrese true para si, false para no");
        Boolean esImportado = testInputScan.nextBoolean();

        System.out.println("CUal es el origen del producto?");
        String origen = testInputScan.nextLine();
        testInputScan.nextLine();

        System.out.println("¿Cuantas calorias tiene el producto?");
        Short calorias = testInputScan.nextShort();

        System.out.println("Ingrese la fecha de vencimiento del producto");
        String vencimiento = testInputScan.nextLine();
        testInputScan.nextLine();

        System.err.println("¿Cual es el porcentaje de descuento que puede tener el producto? Si no admite descuentos, ingrese 0");
        Float descuento = testInputScan.nextFloat();

        System.out.println("Es una bebida alcoholica? Si lo es, responda true. Si no, responda false");
        Boolean esAlcoholica = testInputScan.nextBoolean();

        System.out.println("Cual es la graduacion alcoholica de la bebida? Si no es una bebida alcoholica, responda 0");
        Float graduacionAlcoholica = testInputScan.nextFloat();

        Bebida productoNuevo = new Bebida(id, nombre, descripcion, cantidadEnStock, precioUnidad, costoUnidad, esImportado, origen, esAlcoholica, graduacionAlcoholica, calorias, vencimiento, descuento);
        enJarritas.agregarProducto(productoNuevo.getCantidadEnStock(), productoNuevo);
        App.mostrarInventario(enJarritas);
        
      }


       testInputScan.close(); 
       }
       else {
        System.out.println("Por favor, ingresar 1, 2 o 3");
        testInputScan.close();
       }
      
      
      
      
  
    }
    // METODOS PARA TESTEO
    static void  mostrarInventario(Tienda tienda){
      System.out.println("Estos son los productos en el inventario:");
        tienda.getInventario().forEach((k,producto) -> 
        System.out.println(k + "  "+ producto.getNombre() 
        + ", cantidad en stock : " + producto.getCantidadEnStock()
        + " precio unitario : " + producto.getPrecioUnidad()));
        System.out.println("SALDO EN CAJA ACTUAL: " + tienda.getSaldoEnCaja());
    
    
      }

}
