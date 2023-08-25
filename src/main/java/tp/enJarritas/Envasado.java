package tp.enJarritas;
import java.util.regex.Pattern;
public class Envasado extends Producto implements Comestible {
    enum tipoDeEnvase {
        PLASTICO,
        LATA,
        VIDRIO,
        CARTON,
        PET // Tereftalato de Polietileno
    };
    private Boolean esImportado;
    private String origen;
    private Short calorias;
    private String vencimiento;
    private tipoDeEnvase envase;
    private Float descuento;


  
     public Envasado(
    String id, 
    String nombre, 
    String descripcion, 
    Integer cantidadEnStock, 
    Float precioUnidad,
    Float costoUnidad,
    Boolean esImportado,
    String origen,
    Short calorias,
    String vencimiento,
    String envase,
    float descuento
   )  {
    super(id, nombre, descripcion, cantidadEnStock, precioUnidad, costoUnidad);

    if( precioUnidad > (costoUnidad + ((costoUnidad * 20) / 100 )) ){
      throw new Error("El porcentaje de ganancia no puede superar el 20%");
    }

    /* Decidí pasar el tipo de uso como un string para mayor facilidad al usar el constructor
      * toUpperCase() se asegura de que no haya problemas por el uso de minusculas/mayusculas
      * al settearlo en el constructor y en el metodo set se busca y utiliza el valor del Enum que
      * corresponde al string ingresado.
      */

    if(Pattern.matches("AB[0-9]{3}", id )){
     try {
    this.envase = tipoDeEnvase.valueOf(envase.toUpperCase());
    } catch(IllegalArgumentException err) {
      throw new Error("tipo de envase no valido");
      }
    if(descuento > 20.00){
      throw new Error("Los envasados no pueden tener mas de 20% de descuento");
      } 
    this.descuento = descuento;
    this.esImportado = esImportado;
    this.origen = origen;
    this.calorias = calorias;
    this.vencimiento = vencimiento;
    } else {  
        throw new Error("ID invalido. Los envasados deben contener un ID de 5 digitos que respete el formato AB + un numero de tres digitos"); 
      } 
    
    } //fin de constructor


     //implementacion descuentos

     public float getPrecioConDescuento(){
      final Float precioConDescuento = (float)(this.getPrecioUnidad() - ((this.getPrecioUnidad() * this.getDescuento()) / 100));
      if(precioConDescuento < this.getCostoUnidad()){  throw new Error("El descuento registrado para el producto " + this.getId() + " no pudo ser registrado");   }
      return precioConDescuento ;
   }

   public void setDescuento(float descuento){
    if(descuento > 20.00){
      throw new Error("Los envasados no pueden tener mas de 20% de descuento");
      } 
    this.descuento = descuento;
   }

   public float getDescuento(){
    return  this.descuento;
   }


    
     public short getCalorias(){
        return calorias;
     };
     public void setCalorias(short calorias){
        this.calorias = calorias;
     };

     public String getFechaVencimiento(){
        return vencimiento;
     };

    public void setFechaVencimiento(String fechaDeVencimiento){
        this.vencimiento = fechaDeVencimiento;
     };


    public tipoDeEnvase getTipoDeEnvase(){
      return envase;
    }

    public void setTipoDeEnvase( String envase){
      try {
      this.envase = tipoDeEnvase.valueOf(envase.toUpperCase());
       } catch(IllegalArgumentException err) {
      System.out.println("Tipo de envase no valido");
         }
    }

    public String getOrigen(){
      return origen;
    }
    
    public void setOrigen( String origen){
      this.origen = origen;
    }

    public Boolean getEsImportado(){
      return esImportado;
    }
    
    public void setEsImportado(Boolean esImportado){
      this.esImportado = esImportado;
    }

}
