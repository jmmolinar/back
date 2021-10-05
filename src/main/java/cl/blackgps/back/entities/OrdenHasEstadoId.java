package cl.blackgps.back.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

//@Embeddable
public class OrdenHasEstadoId implements Serializable {

    @Column(name = "orden_id_orden")
    //private Integer ordenIdOrden = null;
    private Integer ordenIdOrden;

    @Column(name = "estado_id_estado")
    private Integer estadoIdEstado;

    public OrdenHasEstadoId() {
    }

    public OrdenHasEstadoId(Integer ordenIdOrden, Integer estadoIdEstado) {
        this.ordenIdOrden = ordenIdOrden;
        this.estadoIdEstado = estadoIdEstado;
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


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OrdenHasEstadoId that = (OrdenHasEstadoId) o;
        return Objects.equals(ordenIdOrden, that.ordenIdOrden) && 
            Objects.equals(estadoIdEstado, that.estadoIdEstado);
        /*return ordenIdOrden.equals(that.ordenIdOrden) &&
            estadoIdEstado.equals(that.estadoIdEstado);*/
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordenIdOrden, estadoIdEstado);
    }

    /*@Override
    public String toString() {
        return "{" + " ordenIdOrden='" + getOrdenIdOrden() + "'" + ", estadoIdEstado='" + getEstadoIdEstado() + "'"
                + "}";
    }*/

}
