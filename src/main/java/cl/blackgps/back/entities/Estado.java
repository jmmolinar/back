package cl.blackgps.back.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "Estado")
@Table(name = "estado")
public class Estado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado", unique = true, nullable = false)
    private Integer idEstado;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(
        mappedBy = "estado", 
        fetch = FetchType.EAGER)
    @JsonManagedReference(value="estado_id_estado")
    private List<OrdenHasEstado> estadoOrdenes = new ArrayList<OrdenHasEstado>();

    public Estado() {
    }

    public Estado(String nombre) {
        this.nombre = nombre;
    }

    public Estado(Integer idEstado, String nombre) {
        this.idEstado = idEstado;
        this.nombre = nombre;
    }


    public Estado(Integer idEstado, String nombre, List<OrdenHasEstado> estadoOrdenes) {
        this.idEstado = idEstado;
        this.nombre = nombre;
        this.estadoOrdenes = estadoOrdenes;
    }


    public Integer getIdEstado() {
        return this.idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<OrdenHasEstado> getEstadoOrdenes() {
        return this.estadoOrdenes;
    }

    public void setEstadoOrdenes(List<OrdenHasEstado> estadoOrdenes) {
        this.estadoOrdenes = estadoOrdenes;
    }

    @Override
    public String toString() {
        return "{" + " idEstado='" + getIdEstado() + "'" + ", nombre='" + getNombre() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Estado)) {
            return false;
        }
        Estado estado = (Estado) o;
        return idEstado == estado.idEstado && Objects.equals(nombre, estado.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstado, nombre);
    }

}
