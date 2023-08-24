package tp.enJarritas;
public interface Comestible {
    final boolean esComestible = true;
    public abstract void setFechaVencimiento(String fecha);
    public abstract String getFechaVencimiento();
    public abstract void setCalorias(short calorias);
    public abstract short getCalorias();
    

}