package tp.enJarritas;
import java.util.regex.Pattern;
public class Bebida extends Producto implements Comestible{

    private Boolean esAlcoholica;
    private Float graduacionAlcoholica;


    
    private Boolean esImportado;
    private String origen;

    private String fechaDeVencimiento;
    private Short calorias;

    private Float descuento;

    public Bebida(
    String id, 
    String nombre, 
    String descripcion, 
    int cantidadEnStock, 
    Float precioUnidad,
    Float costoUnidad,
    boolean esImportado,
    String origen,
    boolean esAlcoholica,
    float graduacionAlcoholica,
    short calorias,
    String fechaDeVencimiento,
    float descuento
){
    super(id, nombre, descripcion, cantidadEnStock, precioUnidad, costoUnidad);
    if(Pattern.matches("AC[0-9]{3}", id )){
    this.esImportado = esImportado;
    this.origen = origen;


    this.esAlcoholica = esAlcoholica;
    this.graduacionAlcoholica = graduacionAlcoholica;

    this.calorias = calorias;
    this.fechaDeVencimiento = fechaDeVencimiento;
    if(descuento > 15.00){
         throw new Error("Las bebidas no pueden tener mas de 15% de descuento");
    }
    this.descuento = descuento;
    } else { throw new Error("ID invalido. Las bebidas deben contener un ID de 5 digitos que respete el formato AC + un numero de tres digitos"); }

    }

    //implementacion descuentos

     public float getPrecioConDescuento(){ 
        final Float precioConDescuento = (float)(this.getPrecioUnidad() - ((this.getPrecioUnidad() * this.getDescuento()) / 100));
        if(precioConDescuento < this.getCostoUnidad()){  throw new Error("El descuento registrado para el producto " + this.getId() + " " + this.getNombre() + " no pudo ser registrado");   }
        return precioConDescuento ;
     }

     public void setDescuento(float descuento){
        if(descuento > 15.00){
         throw new Error("Las bebidas no pueden tener mas de 15% de descuento");
        }
    this.descuento = descuento;
     }

     public float getDescuento(){
        return this.descuento;
     }


    //getters and setters
    public boolean getEsAlcoholica() {
        return this.esAlcoholica;
    }

    public void setEsAlcoholica(boolean esAlcoholica) {
        this.esAlcoholica = esAlcoholica;
    }

    public float getGraduacionAlcoholica() {
        return this.graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(float graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }


    public boolean getEsImportado() {
        return this.esImportado;
    }

    public void setEsImportado(boolean esImportado) {
        this.esImportado = esImportado;
    }

    public String getOrigen() {
        return this.origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }


    public void setFechaVencimiento(String fecha) {
     this.fechaDeVencimiento = fecha;
    }

    public String getFechaVencimiento() {
        return this.fechaDeVencimiento;
    }

    public void setCalorias(short calorias) {
       this.calorias = calorias;
    }

    public short getCalorias() {
        return this.calorias;
    }


}
