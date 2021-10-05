package cl.blackgps.back.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity //(name = "OrdenHasEstado")
@Table(name = "orden_has_estado")
@IdClass(OrdenHasEstadoId.class)
public class OrdenHasEstado implements Serializable {

    @Id
    @Column(name = "orden_id_orden")
    private Integer ordenIdOrden;

    @Id
    @Column(name = "estado_id_estado")
    private Integer estadoIdEstado;

    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "fecha_asignado")
    private LocalDateTime fechaAsignado;
    
    /*@ManyToOne(targetEntity = Orden.class,fetch = FetchType.LAZY)*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "orden_id_orden", 
        referencedColumnName = "id_orden", 
        insertable = false, 
        updatable = false
        //nullable = true
        //foreignKey = @ForeignKey(name = "orden_id_orden", value = ConstraintMode.NO_CONSTRAINT)
    )
    @JsonBackReference(value = "orden_id_orden")
    private Orden orden;

    /*@ManyToOne(targetEntity = Estado.class,fetch = FetchType.LAZY)*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "estado_id_estado", 
        referencedColumnName = "id_estado", 
        insertable = false, 
        updatable = false
    )
    @JsonBackReference(value = "estado_id_estado")
    private Estado estado;

    
    
    public OrdenHasEstado() {
    }

    public OrdenHasEstado(Integer ordenIdOrden, Integer estadoIdEstado) {
        this.ordenIdOrden = ordenIdOrden;
        this.estadoIdEstado = estadoIdEstado;
    }

    public OrdenHasEstado(Integer ordenIdOrden, Integer estadoIdEstado, int idUsuario, LocalDateTime fechaAsignado, Orden orden, Estado estado) {
        this.ordenIdOrden = ordenIdOrden;
        this.estadoIdEstado = estadoIdEstado;
        this.idUsuario = idUsuario;
        this.fechaAsignado = fechaAsignado;
        this.orden = orden;
        this.estado = estado;
    }

    public Integer getOrdenIdOrden() {
        return this.ordenIdOrden;
    }

    public void setOrdenIdOrden(Integer ordenIdOrden) {
        this.ordenIdOrden = ordenIdOrden;
    }

    public Integer getEstadoIdEstado() {
        return this.estadoIdEstado;
    }

    public void setEstadoIdEstado(Integer estadoIdEstado) {
        this.estadoIdEstado = estadoIdEstado;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getFechaAsignado() {
        return this.fechaAsignado;
    }

    public void setFechaAsignado(LocalDateTime fechaAsignado) {
        this.fechaAsignado = fechaAsignado;
    }

    public Orden getOrden() {
        return this.orden;
    }

    public void setOrden(Orden orden) {
        
        /** */
        /*if(!orden.getOrdenEstados().contains(this)){
            orden.getOrdenEstados().add(this);
        }*/
        /** */

        this.orden = orden;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    public void setId(OrdenHasEstadoId id) {
        this.ordenIdOrden = id.getOrdenIdOrden();
        this.estadoIdEstado = id.getEstadoIdEstado();
    }

    public OrdenHasEstadoId getId() {
        return new OrdenHasEstadoId(
            ordenIdOrden,
            estadoIdEstado
        );
    }


    @Override
    public String toString() {
        return "{" + " ordenIdOrden='" + getOrdenIdOrden() + "'" + ", estadoIdEstado='" + getEstadoIdEstado() + "'"
                + ", idUsuario='" + getIdUsuario() + "'" + ", fechaAsignado='" + getFechaAsignado() + "'" + "}";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrdenHasEstado)) {
            return false;
        }
        OrdenHasEstado ordenHasEstado = (OrdenHasEstado) o;
        return Objects.equals(ordenIdOrden, ordenHasEstado.ordenIdOrden) && Objects.equals(estadoIdEstado, ordenHasEstado.estadoIdEstado) && idUsuario == ordenHasEstado.idUsuario && Objects.equals(fechaAsignado, ordenHasEstado.fechaAsignado) && Objects.equals(orden, ordenHasEstado.orden) && Objects.equals(estado, ordenHasEstado.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordenIdOrden, estadoIdEstado, idUsuario, fechaAsignado, orden, estado);
    }

}
