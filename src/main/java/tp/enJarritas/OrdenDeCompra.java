package tp.enJarritas;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
public class OrdenDeCompra {
    Map <String, Integer> listaDeProductos;


    public OrdenDeCompra(
       Map <String, Integer> listaDeProductos,
       Tienda tienda
    )   {
        
        //Definir metodo (foreach, map idfk) que verifique si los productos se encuentran dentro de la orden de compra
        // y si estan disponibles a la venta.
        if(listaDeProductos.size() > 3){  
        throw new Error("El maximo de productos a comprar en una orden son tres.");  
        };


       
         Map <String, Integer> listaDeProductosFiltrada = listaDeProductos.entrySet().stream()
         .filter(map -> map.getValue() < 10)
         .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));


         if(listaDeProductos.size() != listaDeProductosFiltrada.size()){
            throw new Error("No pueden pedirse mas de 10 unidades de un producto");
         }
    

        ArrayList<Boolean> esValido = new ArrayList<Boolean>();
    
        listaDeProductos.forEach((listaKey, listaCantidad) -> {
            for(int cuenta = 0 ; cuenta <= 3; cuenta++){
            Producto match =  tienda.getInventario().get(cuenta).get(listaKey);
            if(match != null 
            && match.isSeVende()){
                esValido.add(cuenta, true);
                return;
             }
         }
        });

        boolean answer = esValido.stream().allMatch(item -> item == true) && esValido.size() == 3;
        if(!answer){
            throw new Error("Uno o mas productos no existen en el inventario");
        };

        

        this.listaDeProductos = listaDeProductos;   
     }



    public Map<String, Integer> getListaDeProductos() {
        return listaDeProductos;
    }

    
   
    


    

}