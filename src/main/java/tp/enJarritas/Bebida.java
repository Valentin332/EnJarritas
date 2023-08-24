package tp.enJarritas;
import java.util.regex.Pattern;
public class Bebida extends Producto implements Descuento, Comestible{

    private Boolean esAlcoholica;
    private Float graduacionAlcoholica;

    private Boolean esImportado;
    private String origen;

    private String fechaDeVencimiento;
    private Short calorias;

    public Bebida(
    String id, 
    String nombre, 
    String descripcion, 
    Integer cantidadEnStock, 
    Double precioUnidad,
    Double costoUnidad,
    Boolean esImportado,
    String origen,
    Boolean esAlcoholica,
    Float graduacionAlcoholica,
    short calorias,
    String fechaDeVencimiento
){
    super(id, nombre, descripcion, cantidadEnStock, precioUnidad, costoUnidad);
    if(Pattern.matches(id, "AC[0-9]{3}")){
    this.esImportado = esImportado;
    this.origen = origen;


    this.esAlcoholica = esAlcoholica;
    this.graduacionAlcoholica = graduacionAlcoholica;

    this.calorias = calorias;
    this.fechaDeVencimiento = fechaDeVencimiento;
    } else { throw new Error("ID invalido"); }

    }

    //implementacion descuentos

     public float getPrecioConDescuento(float porcentaje){
        if(porcentaje > 15.00){
             throw new Error("Las bebidas no pueden tener mas de 15% de descuento");
        } 
        final Float precioConDescuento = (float)(this.getPrecioUnidad() - ((this.getPrecioUnidad() * porcentaje) / 100));
        if(precioConDescuento < this.getCostoUnidad()){  throw new Error("El descuento registrado para el producto " + this.getId() + " no pudo ser registrado");   }
        return precioConDescuento ;
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
