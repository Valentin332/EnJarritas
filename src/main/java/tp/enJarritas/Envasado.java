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


    // constructor, agregar verificacion de id y negar la construccion en caso de que no se cumpla
     public Envasado(
    String id, 
    String nombre, 
    String descripcion, 
    Integer cantidadEnStock, 
    Double precioUnidad,
    Double costoUnidad,
    Boolean esImportado,
    String origen,
    Short calorias,
    String vencimiento,
    String envase
   )  {
    super(id, nombre, descripcion, cantidadEnStock, precioUnidad, costoUnidad);

    if(   precioUnidad > (costoUnidad + ((costoUnidad * 20) / 100 ))   ){
      throw new Error("El porcentaje de ganancia no puede superar el 20%");
    }


    if(Pattern.matches(id, "AB[0-9]{3}")){
    this.esImportado = esImportado;
    this.origen = origen;
    this.calorias = calorias;
    this.vencimiento = vencimiento;
    try {
      this.envase = tipoDeEnvase.valueOf(envase.toUpperCase());
       } catch(IllegalArgumentException err) {
      System.out.println("Tipo de envase no valido");
         }
      } else {  throw new Error("ID invalido"); } 
    }


     //implementacion descuentos

     public Float getPrecioConDescuento(Float porcentaje){
      if(porcentaje > 20.00){
           throw new Error("Los envasados no pueden tener mas de 20% de descuento");
      } 
      final Float precioConDescuento = (float)(this.getPrecioUnidad() - ((this.getPrecioUnidad() * porcentaje) / 100));
      if(precioConDescuento < this.getCostoUnidad()){  throw new Error("El descuento registrado para el producto " + this.getId() + " no pudo ser registrado");   }
      return precioConDescuento ;
   }




    //implementar metodos abstractos
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
