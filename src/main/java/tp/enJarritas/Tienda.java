package tp.enJarritas;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Tienda {
  
 private String nombre;
 private Integer maxProductosEnStock;
 private float saldoEnCaja;
 private HashMap<String, Producto> inventario;


 public Tienda(
    String nombre,
    Integer maxProductosEnStock,
    Float saldoEnCaja
 ){
    this.nombre = nombre;
    this.maxProductosEnStock = maxProductosEnStock;
    this.saldoEnCaja = saldoEnCaja;
    this.inventario = new HashMap<String, Producto>();
 }




 //metodos

/* Para encargarse de la operacion de compra decidí dividir la función en dos metodos 
 * agregarProducto() se utiliza para introducir un producto nuevo al inventario de la tienda
 * comprarProducto() se utiliza para reponer/agregar unidades al stock de un producto ya existente en el inventario.
 * Ademas de la funcionalidad descrita, ambas utilizan if/else para encargarse de verificaciones y excepciones.
 */

public void agregarProducto(int cantidad, Producto producto){
    if(cantidad < 1){ throw new Error("Se requiere minimo una unidad comprada"); }
    if(this.inventario.get(producto.getId()) != null){ System.out.println("El producto ya ha sido agregado al inventario");  
    }
    if(producto.getCantidadEnStock() != cantidad){
        throw new Error("La cantidad inicializada en stock debe corresponder a la cantidad que desea ser agregada");
    }
    if(this.saldoEnCaja - (cantidad * producto.getCostoUnidad()) < 0){ 
         throw new Error("El producto no puede ser agregado por saldo insuficiente");
        }
    if(this.getActualProductosEnStock() + cantidad > this.maxProductosEnStock){
        throw new Error(
            "El producto no puede agregarse porque superaria el stock maximo de la tienda por: " 
            + ((this.getActualProductosEnStock() + cantidad) - this.maxProductosEnStock) + " unidades."
            );
    }

   this.inventario.put(producto.getId(), producto);
   setSaldoEnCaja(getSaldoEnCaja() - (cantidad * producto.getCostoUnidad())); 
   System.out.println("Agregado el producto " + producto.getId() + " " + producto.getNombre() + " al inventario.");

}




public void comprarProducto(int cantidad, Producto producto){
 if(cantidad < 1){ throw new Error("Se requiere minimo una unidad comprada"); }
 if(this.inventario.get(producto.getId()) == null){ System.out.println("El producto no existe en el inventario"); } 

 if(this.saldoEnCaja - (cantidad * producto.getCostoUnidad()) < 0){
    throw new Error("El producto no puede ser agregado por saldo insuficiente");
    }

if(this.getActualProductosEnStock() + cantidad > this.maxProductosEnStock){
        throw new Error(
            "El producto no puede agregarse porque superaria el stock maximo de la tienda por: " 
            + ((this.getActualProductosEnStock() + cantidad) - this.maxProductosEnStock) + " unidades."
            );
    }

    Producto agregarProducto = this.inventario.get(producto.getId());
    agregarProducto.setCantidadEnStock(agregarProducto.getCantidadEnStock() + cantidad);
    float costoTotal = agregarProducto.getCostoUnidad() *  cantidad;
    this.setSaldoEnCaja(this.getSaldoEnCaja() - (costoTotal) );
    System.out.println("Se han comprado " + cantidad + " del producto " + producto.getNombre() + " por " + costoTotal);
}


/* verificacion extra sobre el ID de los productos 
si bien ya se realiza una verificacion del ID en el constructor de las clases (Bebida, Envasado, deLimpieza)
me pareció correcto contar con una seguridad extra.
Está abierta a modificacion o refactorización, puesto que los || la convierten en una función de poca escalabilidad
*/

public Producto identificarProducto(String id){
if(id.startsWith("AC") || id.startsWith("AZ") || id.startsWith("AB") ){
 return inventario.get(id);

} else { throw new Error("tipo de id invalido"); }
}


/* Para la operación de venta, elegí crear una clase llamada OrdenDeCompra que almacena la información de los productos que van a ser comprados y sus posibles descuentos en dos listas.
Considere util esto para abstraer el input de la información y poder encargarse de la venta interactuando con la instancia de la OrdenDeCompra.

El metodo venderProductos, entonces :
Realiza la venta tomando en cuenta descuentos y si la cantidad en stock de un producto satisface la cantidad demandada,
actualiza el estado del saldo en caja,
"devuelve" (solo lo imprime en pantalla) el recibo con el formato recomendado

*/



 public void venderProductos(OrdenDeCompra orden){
    ArrayList <String> recibo = new ArrayList<String>();
    ArrayList <Float> precios = new ArrayList<Float>();


    orden.listaDeProductos.forEach((id, cantidad) -> {
    Producto producto = identificarProducto(id);
    boolean aplicaDescuento = orden.getListaDeDescuentos().get(id);
    float precioUnitario = aplicaDescuento == true  
    ? producto.getPrecioConDescuento() : producto.getPrecioUnidad();

    float precio = precioUnitario * cantidad;

    if(producto instanceof Bebida){
        precio = ((Bebida)producto).getEsImportado() 
        ? (precioUnitario + ((precioUnitario * 10) / 100)) * cantidad
        : precio;
    } else if (producto instanceof Envasado)
        precio = ((Envasado)producto).getEsImportado() 
        ? (precioUnitario + ((precioUnitario * 10) / 100)) * cantidad
        : precio;
    

    if(producto.getCantidadEnStock() - cantidad < 0){
        precio = precioUnitario * producto.getCantidadEnStock();
        precios.add(precio);
        this.setSaldoEnCaja(this.getSaldoEnCaja() + precio);
        producto.setSeVende(false);
        producto.setCantidadEnStock(0);
        String lineaRecibo = aplicaDescuento ? 
        id + ", " + producto.getNombre() + " " + cantidad + " X " + precio +  " descuento de " + producto.getDescuento()+"%"
        : id + ", " + producto.getNombre() + " " + cantidad + " X " + precio;
        
        recibo.add(lineaRecibo);
        } 
        else {
         String lineaRecibo = aplicaDescuento ? 
         id + ", " + producto.getNombre() + " " + cantidad + " X " + precio +  " descuento de " + producto.getDescuento()+"%"
        : id + ", " + producto.getNombre() + " " + cantidad + " X " + precio;
        recibo.add(lineaRecibo);
        precios.add(precio);
        this.setSaldoEnCaja(this.getSaldoEnCaja() + precio);
        }
        }); //fin de loop


        recibo.forEach(str ->  System.out.println(str) );
        float precioTotal = precios.stream().reduce(0f, (subtotal, valor) -> subtotal + valor);
        System.out.println("TOTAL VENTA: " + precioTotal);
    
    
    }




    // Consideraciones especiales

    public List<Producto> obtenerComestiblesConMenorDescuento(float porcentaje_descuento){
        List<Producto> lista =  this.inventario.values().stream()
        .filter((producto) -> 
        producto instanceof Bebida && !((Bebida) producto).getEsImportado() && producto.getDescuento() < porcentaje_descuento
        || producto instanceof Envasado && !((Envasado) producto).getEsImportado() && producto.getDescuento() < porcentaje_descuento
        ).sorted((producto1, producto2) -> producto2.getPrecioUnidad().compareTo(producto1.getPrecioUnidad()))
        .collect(Collectors.toList());

        lista.forEach(comestible -> System.out.println(comestible.getNombre().toUpperCase() + " PRECIO: " + comestible.getPrecioUnidad()));
        
        return lista;
    }
    public List<String> listarProductosConUtilidadesInferiores(float porcentaje_utilidad){
        List<String> lista = this.inventario.values().stream().filter(
            (producto) -> 
            ((producto.getPrecioUnidad() - producto.getCostoUnidad()) / producto.getPrecioUnidad()) * 100 < porcentaje_utilidad 
            ).map(
                producto -> producto.getId() + " " + producto.getNombre() + " Descripcion: " + producto.getDescripcion() + " Cantidad en stock: " + producto.getCantidadEnStock()
                ).collect(Collectors.toList());


           lista.forEach(item -> System.out.println(item));
            return lista;
    }



 // GETTERS y Setters


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getMaxProductosEnStock() {
        return this.maxProductosEnStock;
    }

    public void setMaxProductosEnStock(Integer maxProductosEnStock) {
        this.maxProductosEnStock = maxProductosEnStock;
    }

    public Integer getActualProductosEnStock(){
      ArrayList<Integer>  cantidadesEnStock = new ArrayList<Integer>();
        
        this.inventario.forEach((key, producto) -> {
            cantidadesEnStock.add(producto.getCantidadEnStock());
         });
     
       Integer cantidadTotal = cantidadesEnStock.stream().reduce(0, (subtotal, valor) -> subtotal + valor);     
        return cantidadTotal;
}

    public Float getSaldoEnCaja() {
        return this.saldoEnCaja;
    }

    public void setSaldoEnCaja(Float saldoEnCaja) {
        this.saldoEnCaja = saldoEnCaja;
    }

    public HashMap<String, Producto> getInventario() {
        return this.inventario;
    }





}
