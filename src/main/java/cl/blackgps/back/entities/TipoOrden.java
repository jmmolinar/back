package cl.blackgps.back.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_orden")
public class TipoOrden implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_orden", unique = true, nullable = false)
    private int idTipoOrden;

    @Column(name = "nombre")
    private String nombre;


    public TipoOrden() {
    }


    public TipoOrden(int idTipoOrden, String nombre) {
        this.idTipoOrden = idTipoOrden;
        this.nombre = nombre;
    }


    public int getIdTipoOrden() {
        return this.idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}
