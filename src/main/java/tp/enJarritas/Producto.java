package tp.enJarritas;

public  abstract class Producto implements Descuento {
     private String id;
     private String nombre;
     private String descripcion;
     private Integer cantidadEnStock;
     private Float precioUnidad;
     private Float costoUnidad;
     private Boolean seVende = true;

    public Producto(String id, 
    String nombre, 
    String descripcion, 
    Integer cantidadEnStock, 
    Float precioUnidad,
    Float costoUnidad)
    {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadEnStock = cantidadEnStock;
        this.precioUnidad = precioUnidad;
        this.costoUnidad = costoUnidad;
    }


    public String getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidadEnStock() {
        return this.cantidadEnStock;
    }

    public void setCantidadEnStock(Integer cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    public Float getPrecioUnidad() {
        return this.precioUnidad;
    }

    public void setPrecioUnidad(Float precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public Float getCostoUnidad() {
        return this.costoUnidad;
    }

    public void setCostoUnidad(Float costoUnidad) {
        this.costoUnidad = costoUnidad;
    }

    public Boolean isSeVende() {
        return this.seVende;
    }
    public void setSeVende(Boolean seVende) {
        this.seVende = seVende;
    }





}
