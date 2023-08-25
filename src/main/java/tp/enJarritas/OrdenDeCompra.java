package tp.enJarritas;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
public class OrdenDeCompra {
    //se almacena ID del producto y la cantidad deseada.
     HashMap <String, Integer> listaDeProductos;
     //se almacena  ID del producto y si se aplica o no un descuento
    HashMap <String, Boolean> listaDeDescuentos;


    public OrdenDeCompra(
       HashMap <String, Integer> listaDeProductos,
       HashMap <String, Boolean> listaDeDescuentos,
       Tienda tienda
    )   {
        
        if(listaDeProductos.size() > 3){  
        throw new Error("El maximo de productos a comprar en una orden son tres.");  
        };


        //crea una nueva lista que filtra aquellos productos de los cuales se piden mas de 10 unidades.
         Map <String, Integer> listaDeProductosFiltrada = listaDeProductos.entrySet().stream()
         .filter(map -> map.getValue() <= 10)
         .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

        //si la lista resultante no concuerda con la original,
        // existe un producto del cual se pidieron mas de 10 unidades y se rechaza el pedido.
         if(listaDeProductos.size() != listaDeProductosFiltrada.size()){
            throw new Error("No pueden pedirse mas de 10 unidades de un producto");
         }
    

        /* Verificación donde se chequea si todos los productos pedidos estan en inventario y se encuentran disponibles a la venta.
         * También se compara si la lista de productos y la lista de descuentos contienen los mismos productos
         * Finalmente se agrega al arrayList esValido un true si todas las condiciones se cumplen
         * answer chequea si todos los valores de esValido son true y si contienen la misma cantidad de elementos que los productos de la lista.
         * Una vez esté todo Ok, se genera la orden de compra
         */
        ArrayList<Boolean> esValido = new ArrayList<Boolean>();
    
        listaDeProductos.forEach((listaKey, listaCantidad) -> {
            for(int cuenta = 0 ; cuenta < 3; cuenta++){
            Producto match =  tienda.getInventario().get(listaKey);
            Boolean matchLista = listaDeDescuentos.get(listaKey);
            if(
            match != null 
            && match.isSeVende() 
            && matchLista != null){
                esValido.add(cuenta, true);
                return;
             }
         }


        }); 

        boolean answer = esValido.stream().allMatch(item -> item == true) && esValido.size() == listaDeProductos.size();
        if(!answer){
            throw new Error("Uno o mas productos no existen en el inventario o la lista de descuentos no coincide");
        };

        

        this.listaDeProductos = listaDeProductos;
        this.listaDeDescuentos = listaDeDescuentos;   
     } //FIN DE CONSTRUCTOR


    public Map<String, Integer> getListaDeProductos() {
        return listaDeProductos;
    }
    public Map<String, Boolean> getListaDeDescuentos() {
        return listaDeDescuentos;
    }










}
   
    


