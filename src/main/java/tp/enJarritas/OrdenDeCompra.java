package tp.enJarritas;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
public class OrdenDeCompra {
    HashMap <String, Integer> listaDeProductos;
    HashMap <String, Float> listaDeDescuentos;


    public OrdenDeCompra(
       HashMap <String, Integer> listaDeProductos,
       HashMap <String, Float> listaDeDescuentos,
       Tienda tienda
    )   {
        
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
            for(int cuenta = 0 ; cuenta < 3; cuenta++){
            Producto match =  tienda.getInventario().get(listaKey);
            Float matchLista = listaDeDescuentos.get(listaKey);
            if(
            match != null 
            && match.isSeVende() 
            && matchLista != null){
                esValido.add(cuenta, true);
                return;
             }
         }


        }); // fin de constructor

        boolean answer = esValido.stream().allMatch(item -> item == true) && esValido.size() == listaDeProductos.size();
        if(!answer){
            throw new Error("Uno o mas productos no existen en el inventario o la lista de descuentos no coincide");
        };

        

        this.listaDeProductos = listaDeProductos;
        this.listaDeDescuentos = listaDeDescuentos;   
     }


    public Map<String, Integer> getListaDeProductos() {
        return listaDeProductos;
    }
    public Map<String, Float> getListaDeDescuentos() {
        return listaDeDescuentos;
    }










}
   
    


