package tp.enJarritas;
import java.util.regex.Pattern;
public class deLimpieza extends Producto  {
    enum aplicacion {
        COCINA,
        PISOS,
        ROPA,
        MULTIUSO
    };
    private aplicacion uso;
    private Float descuento;

    public deLimpieza(
    String id, 
    String nombre, 
    String descripcion, 
    Integer cantidadEnStock, 
    Float precioUnidad,
    Float costoUnidad,
    Float descuento,
    String uso
        ) {
        super(id, nombre, descripcion, cantidadEnStock, precioUnidad, costoUnidad);
        
          /* Decidí pasar el tipo de uso como un string para mayor facilidad al usar el constructor
           * toUpperCase() se asegura de que no haya problemas por el uso de minusculas/mayusculas
           * al settearlo en el constructor y en el metodo set se busca y utiliza el valor del Enum que
           * corresponde al string ingresado.
           */
          
        if(Pattern.matches("AZ[0-9]{3}", id )){
        try {
            this.uso = aplicacion.valueOf(uso.toUpperCase());

           if(descuento > 25.00){
             throw new Error("Los productos de limpieza no pueden tener mas de 25% de descuento");
            } 
            
            this.descuento = descuento;

             } catch(IllegalArgumentException err) {
            System.out.println("Tipo de envase no valido");
               }
    
            } else {  
            throw new Error("ID invalido. Los productos de limpieza deben contener un ID de 5 digitos que respete el formato AZ + un numero de tres digitos");
           } 
        


          if(uso.toUpperCase() != "ROPA" && precioUnidad > (costoUnidad + ( (costoUnidad * 25) / 100 ))
          || uso.toUpperCase() != "MULTIUSO"  && precioUnidad > (costoUnidad + ( (costoUnidad * 25) / 100 )) 
          ) {
            throw new Error("El porcentaje de ganancia no puede superar el 25%");
          } else if ( precioUnidad < (costoUnidad + ( (costoUnidad * 10) / 100 )) ) 
          { throw new Error("El porcentaje de ganancia no puede ser menor a 10%"); } 
      }; //fin de constructor


      //implementacion descuentos

     public float getPrecioConDescuento(){
        
        final Float precioConDescuento = (float)(this.getPrecioUnidad() - ((this.getPrecioUnidad() * this.getDescuento()) / 100));
        
        if(precioConDescuento < this.getCostoUnidad()){  throw new Error("El descuento registrado para el producto " + this.getId() + " no pudo ser registrado");   }
        return precioConDescuento ;
     }

     public void setDescuento(float descuento){
      if(descuento > 25.00){
             throw new Error("Los productos de limpieza no pueden tener mas de 25% de descuento");
        } 
       this.descuento = descuento; 
     }

     public float getDescuento(){
      return this.descuento;
     }


    
    public void setUso( String uso){
     try {
            this.uso = aplicacion.valueOf(uso.toUpperCase());
             } catch(IllegalArgumentException err) {
            System.out.println("Tipo de envase no valido");
               }
    }  

    public aplicacion getUso(){
        return uso;
    }

}
