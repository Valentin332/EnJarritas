package tp.enJarritas;
import java.util.ArrayList;
import java.util.HashMap;

public class Tienda {
  
 private String nombre;
 private Short maxProductosEnStock;
 private Double saldoEnCaja;
 private ArrayList<HashMap<String, Producto>> inventario;


 public Tienda(
    String nombre,
    Short maxProductosEnStock,
    Double saldoEnCaja
 ){
    this.nombre = nombre;
    this.maxProductosEnStock = maxProductosEnStock;
    this.saldoEnCaja = saldoEnCaja;
    this.inventario = new ArrayList<HashMap<String, Producto>>();
    HashMap<String, Producto> bebidas = new HashMap<String, Producto>();
    HashMap<String, Producto> deLimpiezas = new HashMap<String, Producto>();
    HashMap<String, Producto> envasados = new HashMap<String, Producto>();
    

    inventario.add(bebidas);
    inventario.add(deLimpiezas);
    inventario.add(envasados);


   
   




 }




 //metodos


private void verificarAdicion(int index, int cantidad, Producto producto){
    if(this.inventario.get(index).containsValue(producto)){ System.out.println("El producto ya ha sido agregado al inventario");  
    }
    if(this.saldoEnCaja - (cantidad * producto.getCostoUnidad()) < 0){
    throw new Error("El producto no puede ser agregado por saldo insuficiente");
    }
   this.inventario.get(index).put(producto.getId(), producto);
   setSaldoEnCaja(getSaldoEnCaja() - (cantidad * producto.getCostoUnidad())); 



}

private void verificarCompra(int index, int cantidad, Producto producto){
 if(!this.inventario.get(index).containsValue(producto)){ System.out.println("El producto no existe en el inventario"); } 

 if(this.saldoEnCaja - (cantidad * producto.getCostoUnidad()) < 0){
    throw new Error("El producto no puede ser agregado por saldo insuficiente");
    }

    Producto agregarProducto = this.inventario.get(index).get(producto.getId());
    agregarProducto.setCantidadEnStock(agregarProducto.getCantidadEnStock() + cantidad);
    this.setSaldoEnCaja(this.getSaldoEnCaja() - (agregarProducto.getCostoUnidad() *  cantidad) );
}




 public void agregarProducto(Producto producto, int cantidad){
    if(cantidad < 1){ throw new Error("Se requiere minimo una unidad comprada"); }

    if(producto instanceof Bebida){
      verificarAdicion(0, cantidad, producto);
    } else if(producto instanceof deLimpieza){
        verificarAdicion(1, cantidad, producto);
    } else if (producto instanceof Envasado) {
        verificarAdicion(2, cantidad, producto);

    } else throw new Error("Tipo de producto invalido");

    
 } 


public void comprarProducto(Producto producto, int cantidad){
if(cantidad < 1){ throw new Error("Se requiere minimo una unidad comprada"); }

    if(producto instanceof Bebida){
      verificarCompra(0, cantidad, producto);
    } else if(producto instanceof deLimpieza){
        verificarCompra(1, cantidad, producto);
    } else if (producto instanceof Envasado) {
        verificarCompra(2, cantidad, producto);

    } else throw new Error("Tipo de producto invalido");



}


public Producto identificarProducto(String id){
if(id.startsWith("AC")){
 return (Bebida)inventario.get(0).get(id);

} else if(id.startsWith("AZ")){
return (deLimpieza)inventario.get(1).get(id);
} else if (id.startsWith("AB")){
return (Envasado)inventario.get(2).get(id);
} else throw new Error("tipo de id invalido");

}


// como mierda se si hay descuentos o no?
 public void venderProductos(OrdenDeCompra orden){
    ArrayList <String> recibo = new ArrayList<String>();
    ArrayList <Double> precios = new ArrayList<Double>();


    orden.listaDeProductos.forEach((id, cantidad) -> {
    Producto producto = identificarProducto(id);
    double precio = producto.getPrecioUnidad() * cantidad;
    
    if(producto.getCantidadEnStock() - cantidad < 0){
        precio = producto.getCantidadEnStock() * producto.getPrecioUnidad();
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
        // to do + precios.stream().reduce(0,(a, b) -> a + b)
        System.out.println("TOTAL VENTA: ");
    
    
    }

 // GETTERS y Setters


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getMaxProductosEnStock() {
        return this.maxProductosEnStock;
    }

    public void setMaxProductosEnStock(Short maxProductosEnStock) {
        this.maxProductosEnStock = maxProductosEnStock;
    }

    public Double getSaldoEnCaja() {
        return this.saldoEnCaja;
    }

    public void setSaldoEnCaja(Double saldoEnCaja) {
        this.saldoEnCaja = saldoEnCaja;
    }

    public ArrayList <HashMap<String, Producto>> getInventario() {
        return this.inventario;
    }







}
