package cl.blackgps.back.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;*/

@Entity
@Table(name = "bodega_activos")
/*@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter*/
public class BodegaActivos implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bodega_activos", unique = true, nullable = false)
    private int idBodegaActivos;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "region")
    private String region;

    @Column(name = "comuna")
    private String comuna;

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero")
    private int numero;

    @Column(name = "latitud")
    private Float latitud;

    @Column(name = "longitud")
    private Float longitud;

    @Column(name = "id_geocerca_cabecera")
    private int idGeocercaCabecera;
    


    public BodegaActivos() {
    }


    public BodegaActivos(int idBodegaActivos, String nombre, String region, String comuna, String calle, int numero, Float latitud, Float longitud, int idGeocercaCabecera) {
        this.idBodegaActivos = idBodegaActivos;
        this.nombre = nombre;
        this.region = region;
        this.comuna = comuna;
        this.calle = calle;
        this.numero = numero;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idGeocercaCabecera = idGeocercaCabecera;
    }


    public int getIdBodegaActivos() {
        return this.idBodegaActivos;
    }

    public void setIdBodegaActivos(int idBodegaActivos) {
        this.idBodegaActivos = idBodegaActivos;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getComuna() {
        return this.comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getCalle() {
        return this.calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Float getLatitud() {
        return this.latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return this.longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public int getIdGeocercaCabecera() {
        return this.idGeocercaCabecera;
    }

    public void setIdGeocercaCabecera(int idGeocercaCabecera) {
        this.idGeocercaCabecera = idGeocercaCabecera;
    }

    
}
