package cl.blackgps.back.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orden")
public class Orden implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden", unique = true, nullable = false)
    private Integer idOrden;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_inicial")
    private LocalDateTime fechaInicial;
    @Column(name = "fecha_final")
    private LocalDateTime fechaFinal;


    /*@Column(name = "tipo_orden_id_tipo_orden")
    private int tipoOrdenIdTipoOrden;*/
    //RELACIÓN: Tipo de activo
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "tipo_orden_id_tipo_orden")
    private TipoOrden tipoOrden;

    @Column(name = "activo_id_activo")
    private int activoIdActivo;

    /*@Column(name = "taller_servicio_id_taller_servicio")
    private int tallerServicioIdTallerServicio;*/
    //RELACIÓN: Bodega de activos
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "taller_servicio_id_taller_servicio")
    private TallerServicio tallerServicio;
    
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "ruta_adjunto_completado")
    private String rutaAdjuntoCompletado;
    @Column(name = "fecha_ruta_completado")
    private LocalDateTime fechaRutaCompletado;

    //RELACIÓN Unidireccional: Estados de la órden
    /*@ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
        name = "orden_has_estado",
        joinColumns = @JoinColumn(name = "estado_id_estado"),
        inverseJoinColumns = @JoinColumn(name = "orden_id_orden")
    )
    //private List<OrdenHasEstado> ordenEstados = new ArrayList<OrdenHasEstado>();
    private List<Estado> estados = new ArrayList<Estado>();*/

    //RELACIÓN Bidireccional: Estados de la órden
    //@OneToMany(targetEntity = OrdenHasEstado.class,mappedBy = "orden",cascade = {CascadeType.ALL},orphanRemoval = true, fetch = FetchType.LAZY) 
    @OneToMany(
        mappedBy = "orden",
        cascade = {CascadeType.ALL},
        orphanRemoval = true, 
        fetch = FetchType.LAZY) 
    @JsonManagedReference(value = "orden_id_orden")
    private List<OrdenHasEstado> ordenEstados = new ArrayList<OrdenHasEstado>();

    /** */
    public void addOrdenEstados(OrdenHasEstado ordenHasEstado){
        ordenEstados.add(ordenHasEstado);
        ordenHasEstado.setOrden(this);
    }

    public void removeOrdenEstados(OrdenHasEstado ordenHasEstado){
        ordenEstados.remove(ordenHasEstado);
        ordenHasEstado.setOrden(null);
    }
    /** */


    public Orden() {
    }


    public Orden(Integer idOrden, LocalDateTime fechaCreacion, LocalDateTime fechaInicial, LocalDateTime fechaFinal, TipoOrden tipoOrden, int activoIdActivo, TallerServicio tallerServicio, String observaciones, String rutaAdjuntoCompletado, LocalDateTime fechaRutaCompletado, List<OrdenHasEstado> ordenEstados) {
        this.idOrden = idOrden;
        this.fechaCreacion = fechaCreacion;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.tipoOrden = tipoOrden;
        this.activoIdActivo = activoIdActivo;
        this.tallerServicio = tallerServicio;
        this.observaciones = observaciones;
        this.rutaAdjuntoCompletado = rutaAdjuntoCompletado;
        this.fechaRutaCompletado = fechaRutaCompletado;
        this.ordenEstados = ordenEstados;
    }


    public Integer getIdOrden() {
        return this.idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public LocalDateTime getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaInicial() {
        return this.fechaInicial;
    }

    public void setFechaInicial(LocalDateTime fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDateTime getFechaFinal() {
        return this.fechaFinal;
    }

    public void setFechaFinal(LocalDateTime fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public TipoOrden getTipoOrden() {
        return this.tipoOrden;
    }

    public void setTipoOrden(TipoOrden tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public int getActivoIdActivo() {
        return this.activoIdActivo;
    }

    public void setActivoIdActivo(int activoIdActivo) {
        this.activoIdActivo = activoIdActivo;
    }

    public TallerServicio getTallerServicio() {
        return this.tallerServicio;
    }

    public void setTallerServicio(TallerServicio tallerServicio) {
        this.tallerServicio = tallerServicio;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRutaAdjuntoCompletado() {
        return this.rutaAdjuntoCompletado;
    }

    public void setRutaAdjuntoCompletado(String rutaAdjuntoCompletado) {
        this.rutaAdjuntoCompletado = rutaAdjuntoCompletado;
    }

    public LocalDateTime getFechaRutaCompletado() {
        return this.fechaRutaCompletado;
    }

    public void setFechaRutaCompletado(LocalDateTime fechaRutaCompletado) {
        this.fechaRutaCompletado = fechaRutaCompletado;
    }

    public List<OrdenHasEstado> getOrdenEstados() {

        //return this.ordenEstados;

        if(this.getIdOrden() != null){

            System.out.println("Dentro del getOrdenEstados");
            System.out.println("Cantidad de estados: " + ordenEstados.size());
            
            for(int i = 0; i < ordenEstados.size(); i++){

                ordenEstados.get(i).setOrdenIdOrden(this.getIdOrden());

                System.out.println("En get ordenIdOrden: " + ordenEstados.get(i).getOrdenIdOrden());
                System.out.println("En get estadoIdEstado: " + ordenEstados.get(i).getEstadoIdEstado());
                System.out.println("En get idUsuario: " + ordenEstados.get(i).getIdUsuario());
                System.out.println("En get fechaAsignado: " + ordenEstados.get(i).getFechaAsignado());
                
            }

        }
        
        return this.ordenEstados;
    }

    public void setOrdenEstados(List<OrdenHasEstado> ordenEstados) {

        /*for(int i = 0; i < ordenEstados.size(); i++){

            ordenEstados.get(i).setOrdenIdOrden(this.getIdOrden());
            ordenEstados.get(i).getId().setOrdenIdOrden(this.getIdOrden());;
            
        }*/

        this.ordenEstados = ordenEstados;
    }




    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Orden)) {
            return false;
        }
        Orden orden = (Orden) o;
        return Objects.equals(idOrden, orden.idOrden) && Objects.equals(fechaCreacion, orden.fechaCreacion) && Objects.equals(fechaInicial, orden.fechaInicial) && Objects.equals(fechaFinal, orden.fechaFinal) && Objects.equals(tipoOrden, orden.tipoOrden) && activoIdActivo == orden.activoIdActivo && Objects.equals(tallerServicio, orden.tallerServicio) && Objects.equals(observaciones, orden.observaciones) && Objects.equals(rutaAdjuntoCompletado, orden.rutaAdjuntoCompletado) && Objects.equals(fechaRutaCompletado, orden.fechaRutaCompletado) && Objects.equals(ordenEstados, orden.ordenEstados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrden, fechaCreacion, fechaInicial, fechaFinal, tipoOrden, activoIdActivo, tallerServicio, observaciones, rutaAdjuntoCompletado, fechaRutaCompletado, ordenEstados);
    }


    /*@Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Orden)) {
            return false;
        }
        Orden orden = (Orden) o;
        return idOrden == orden.idOrden && Objects.equals(fechaCreacion, orden.fechaCreacion) && Objects.equals(fechaInicial, orden.fechaInicial) && Objects.equals(fechaFinal, orden.fechaFinal) && Objects.equals(tipoOrden, orden.tipoOrden) && activoIdActivo == orden.activoIdActivo && Objects.equals(tallerServicio, orden.tallerServicio) && Objects.equals(observaciones, orden.observaciones) && Objects.equals(rutaAdjuntoCompletado, orden.rutaAdjuntoCompletado) && Objects.equals(fechaRutaCompletado, orden.fechaRutaCompletado) && Objects.equals(ordenEstados, orden.ordenEstados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrden, fechaCreacion, fechaInicial, fechaFinal, tipoOrden, activoIdActivo, tallerServicio, observaciones, rutaAdjuntoCompletado, fechaRutaCompletado, ordenEstados);
    }*/


    @Override
    public String toString() {
        return "{" +
            " idOrden='" + getIdOrden() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", fechaInicial='" + getFechaInicial() + "'" +
            ", fechaFinal='" + getFechaFinal() + "'" +
            ", tipoOrden='" + getTipoOrden() + "'" +
            ", activoIdActivo='" + getActivoIdActivo() + "'" +
            ", tallerServicio='" + getTallerServicio() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            ", rutaAdjuntoCompletado='" + getRutaAdjuntoCompletado() + "'" +
            ", fechaRutaCompletado='" + getFechaRutaCompletado() + "'" +
            ", ordenEstados='" + getOrdenEstados() + "'" +
            "}";
    }  

}
