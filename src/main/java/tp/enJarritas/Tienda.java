package tp.enJarritas;
import java.util.ArrayList;
import java.util.HashMap;

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


private void verificarAdicion(int cantidad, Producto producto){
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

}




private void verificarCompra(int cantidad, Producto producto){
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
    this.setSaldoEnCaja(this.getSaldoEnCaja() - (agregarProducto.getCostoUnidad() *  cantidad) );
    System.out.println("Se han comprado " + cantidad + " del producto " + producto.getNombre());
}




 public void agregarProducto(Producto producto, int cantidad){
    if(cantidad < 1){ throw new Error("Se requiere minimo una unidad comprada"); }
    verificarAdicion(cantidad, producto);
    } 


public void comprarProducto(Producto producto, int cantidad){
if(cantidad < 1){ throw new Error("Se requiere minimo una unidad comprada"); }
verificarCompra(cantidad, producto);
}


public Producto identificarProducto(String id){
if(id.startsWith("AC")){
 return (Bebida)inventario.get(id);

} else if(id.startsWith("AZ")){
return (deLimpieza)inventario.get(id);

} else if (id.startsWith("AB")){
return (Envasado)inventario.get(id);

} else throw new Error("tipo de id invalido");

}



 public void venderProductos(OrdenDeCompra orden){
    ArrayList <String> recibo = new ArrayList<String>();
    ArrayList <Float> precios = new ArrayList<Float>();


    orden.listaDeProductos.forEach((id, cantidad) -> {
    Producto producto = identificarProducto(id);

    float precio =  producto.getPrecioConDescuento(orden.listaDeDescuentos.get(id)) * cantidad;

    if(producto instanceof Bebida){
        precio = ((Bebida)producto).getEsImportado() 
        ? producto.getPrecioUnidad() + ((producto.getPrecioUnidad() * 10) / 100)
        : precio;
    } else if (producto instanceof Bebida)
        precio = ((Bebida)producto).getEsImportado() 
        ? producto.getPrecioUnidad() + ((producto.getPrecioUnidad() * 10) / 100)
        : precio;
    
    if(producto.getCantidadEnStock() - cantidad < 0){
        precio = producto.getCantidadEnStock() * producto.getPrecioConDescuento(orden.listaDeDescuentos.get(id));
        precios.add(precio);
        this.setSaldoEnCaja(this.getSaldoEnCaja() + precio);
        producto.setSeVende(false);
        producto.setCantidadEnStock(0);
         recibo.add(id + ", " + producto.getNombre() + " " + cantidad + " X " + precio);
        } 

        else {
        recibo.add(id + ", " + producto.getNombre() + " " + cantidad + " X " + precio);
        precios.add(precio);
        this.setSaldoEnCaja(this.getSaldoEnCaja() + precio);
        }
        }); //fin de loop


        recibo.forEach(str ->  System.out.println(str) );
        float precioFinal = precios.stream().reduce(0f, (subtotal, valor) -> subtotal + valor);
        System.out.println("TOTAL VENTA: " + precioFinal);
    
    
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
