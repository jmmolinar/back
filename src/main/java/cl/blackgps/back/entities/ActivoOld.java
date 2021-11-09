package cl.blackgps.back.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "activo")
public class ActivoOld {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activo", unique = true, nullable = false)
    private int idActivo;

    @Column(name = "anio")
    private int anio;
    @Column(name = "dado_de_baja")
    private int dadoDeBaja;
    @Column(name = "vehiculo_id_vehiculo")
    private int vehiculoIdVehiculo;
    @Column(name = "area_id_area")
    private int areaIdArea;
    @Column(name = "tipo_activo_id_tipo_activo", insertable = true, updatable = true)
    private int tipoActivoIdTipoActivo;
    @Column(name = "bodega_activos_id_bodega_activos")
    private int bodegaActivosIdBodegaActivos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "activo_id_activo")
    private List<Documento> documentos = new ArrayList<Documento>();


    public ActivoOld() {
    }

    public ActivoOld(int idActivo, int anio, int dadoDeBaja, int vehiculoIdVehiculo, int areaIdArea, int tipoActivoIdTipoActivo, int bodegaActivosIdBodegaActivos, List<Documento> documentos) {
        this.idActivo = idActivo;
        this.anio = anio;
        this.dadoDeBaja = dadoDeBaja;
        this.vehiculoIdVehiculo = vehiculoIdVehiculo;
        this.areaIdArea = areaIdArea;
        this.tipoActivoIdTipoActivo = tipoActivoIdTipoActivo;
        this.bodegaActivosIdBodegaActivos = bodegaActivosIdBodegaActivos;
        this.documentos = documentos;
    }


    public int getIdActivo() {
        return this.idActivo;
    }

    public void setIdActivo(int idActivo) {
        this.idActivo = idActivo;
    }

    public int getAnio() {
        return this.anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDadoDeBaja() {
        return this.dadoDeBaja;
    }

    public void setDadoDeBaja(int dadoDeBaja) {
        this.dadoDeBaja = dadoDeBaja;
    }


    public int getVehiculoIdVehiculo() {
        return this.vehiculoIdVehiculo;
    }

    public void setVehiculoIdVehiculo(int vehiculoIdVehiculo) {
        this.vehiculoIdVehiculo = vehiculoIdVehiculo;
    }

    public int getAreaIdArea() {
        return this.areaIdArea;
    }

    public void setAreaIdArea(int areaIdArea) {
        this.areaIdArea = areaIdArea;
    }

    public int getTipoActivoIdTipoActivo() {
        return this.tipoActivoIdTipoActivo;
    }

    public void setTipoActivoIdTipoActivo(int tipoActivoIdTipoActivo) {
        this.tipoActivoIdTipoActivo = tipoActivoIdTipoActivo;
    }

    public int getBodegaActivosIdBodegaActivos() {
        return this.bodegaActivosIdBodegaActivos;
    }

    public void setBodegaActivosIdBodegaActivos(int bodegaActivosIdBodegaActivos) {
        this.bodegaActivosIdBodegaActivos = bodegaActivosIdBodegaActivos;
    }

    public List<Documento> getDocumentos() {
        return this.documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }


}

