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


    public deLimpieza(
    String id, 
    String nombre, 
    String descripcion, 
    Integer cantidadEnStock, 
    Float precioUnidad,
    Float costoUnidad,
    String uso
        ) {
        super(id, nombre, descripcion, cantidadEnStock, precioUnidad, costoUnidad);
        
        if(uso.toUpperCase() != "ROPA" && precioUnidad > (costoUnidad + ( (costoUnidad * 25) / 100 ))
          || uso.toUpperCase() != "MULTIUSO"  && precioUnidad > (costoUnidad + ( (costoUnidad * 25) / 100 )) 
          ) {
            throw new Error("El porcentaje de ganancia no puede superar el 25%");
          } else if
          ( precioUnidad < (costoUnidad + ( (costoUnidad * 10) / 100 )) ) 
          {
            throw new Error("El porcentaje de ganancia no puede ser menor a 10%");
          } 



        if(Pattern.matches("AZ[0-9]{3}", id )){
        try {
            this.uso = aplicacion.valueOf(uso.toUpperCase());
             } catch(IllegalArgumentException err) {
            System.out.println("Tipo de envase no valido");
               }
    
            } else {  throw new Error("ID invalido. Los productos de limpieza deben contener un ID de 5 digitos que respete el formato AZ + un numero de tres digitos"); } 
    };

     //implementacion descuentos

     public float getPrecioConDescuento(float porcentaje){
        if(porcentaje > 25.00){
             throw new Error("Los productos de limpieza no pueden tener mas de 25% de descuento");
        } 
        final Float precioConDescuento = (float)(this.getPrecioUnidad() - ((this.getPrecioUnidad() * porcentaje) / 100));
        if(precioConDescuento < this.getCostoUnidad()){  throw new Error("El descuento registrado para el producto " + this.getId() + " no pudo ser registrado");   }
        return precioConDescuento ;
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
