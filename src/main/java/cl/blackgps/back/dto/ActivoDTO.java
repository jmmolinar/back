package cl.blackgps.back.dto;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

//import cl.blackgps.back.entities.BodegaActivos;
import cl.blackgps.back.entities.PlanMantenimiento;

public class ActivoDTO implements Serializable {

    private int idActivo;
    private int vehiculoIdVehiculo;
    private int areaIdArea;
    private int bodegaActivosIdBodega;
    private String nombreBodega;
    private int tipoActivoIdTipoActivo;
    private String nombreTipoActivo;
    private int anio;
    //private int planMantenimientoIdPlanMantenimiento;
    private Boolean dadoDeBaja;
    private int idActivoPlanes;
    private int idPlanActivo;
    private String nombrePlanMantenimiento;
    private Boolean porKm;
    private Boolean porHora;
    private Boolean porPeriodo;
    //private List<PlanMantenimiento> activoPlanes;
    //private BodegaActivos bodegaActivos;
    private List<PlanMantenimiento> activoPlanes = new ArrayList<PlanMantenimiento>();



    public ActivoDTO() {
    }

    public ActivoDTO(int idActivo, int vehiculoIdVehiculo, int areaIdArea, int bodegaActivosIdBodega, String nombreBodega, int tipoActivoIdTipoActivo, String nombreTipoActivo, int anio, Boolean dadoDeBaja, int idActivoPlanes, int idPlanActivo, String nombrePlanMantenimiento, Boolean porKm, Boolean porHora, Boolean porPeriodo) {
        this.idActivo = idActivo;
        this.vehiculoIdVehiculo = vehiculoIdVehiculo;
        this.areaIdArea = areaIdArea;
        this.bodegaActivosIdBodega = bodegaActivosIdBodega;
        this.nombreBodega = nombreBodega;
        this.tipoActivoIdTipoActivo = tipoActivoIdTipoActivo;
        this.nombreTipoActivo = nombreTipoActivo;
        this.anio = anio;
        this.dadoDeBaja = dadoDeBaja;
        this.idActivoPlanes = idActivoPlanes;
        this.idPlanActivo = idPlanActivo;
        this.nombrePlanMantenimiento = nombrePlanMantenimiento;
        this.porKm = porKm;
        this.porHora = porHora;
        this.porPeriodo = porPeriodo;
    }


    public ActivoDTO(int idActivo, int vehiculoIdVehiculo, int areaIdArea, int bodegaActivosIdBodega, String nombreBodega, int tipoActivoIdTipoActivo, String nombreTipoActivo, int anio, Boolean dadoDeBaja, int idActivoPlanes, int idPlanActivo, String nombrePlanMantenimiento, Boolean porKm, Boolean porHora, Boolean porPeriodo, List<PlanMantenimiento> activoPlanes) {
        this.idActivo = idActivo;
        this.vehiculoIdVehiculo = vehiculoIdVehiculo;
        this.areaIdArea = areaIdArea;
        this.bodegaActivosIdBodega = bodegaActivosIdBodega;
        this.nombreBodega = nombreBodega;
        this.tipoActivoIdTipoActivo = tipoActivoIdTipoActivo;
        this.nombreTipoActivo = nombreTipoActivo;
        this.anio = anio;
        this.dadoDeBaja = dadoDeBaja;
        this.idActivoPlanes = idActivoPlanes;
        this.idPlanActivo = idPlanActivo;
        this.nombrePlanMantenimiento = nombrePlanMantenimiento;
        this.porKm = porKm;
        this.porHora = porHora;
        this.porPeriodo = porPeriodo;
        this.activoPlanes = activoPlanes;
    }


    public int getIdActivo() {
        return this.idActivo;
    }

    public void setIdActivo(int idActivo) {
        this.idActivo = idActivo;
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

    public int getBodegaActivosIdBodega() {
        return this.bodegaActivosIdBodega;
    }

    public void setBodegaActivosIdBodega(int bodegaActivosIdBodega) {
        this.bodegaActivosIdBodega = bodegaActivosIdBodega;
    }

    public String getNombreBodega() {
        return this.nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

    public int getTipoActivoIdTipoActivo() {
        return this.tipoActivoIdTipoActivo;
    }

    public void setTipoActivoIdTipoActivo(int tipoActivoIdTipoActivo) {
        this.tipoActivoIdTipoActivo = tipoActivoIdTipoActivo;
    }

    public String getNombreTipoActivo() {
        return this.nombreTipoActivo;
    }

    public void setNombreTipoActivo(String nombreTipoActivo) {
        this.nombreTipoActivo = nombreTipoActivo;
    }

    public int getAnio() {
        return this.anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Boolean isDadoDeBaja() {
        return this.dadoDeBaja;
    }

    public Boolean getDadoDeBaja() {
        return this.dadoDeBaja;
    }

    public void setDadoDeBaja(Boolean dadoDeBaja) {
        this.dadoDeBaja = dadoDeBaja;
    }

    public int getIdActivoPlanes() {
        return this.idActivoPlanes;
    }

    public void setIdActivoPlanes(int idActivoPlanes) {
        this.idActivoPlanes = idActivoPlanes;
    }

    public int getIdPlanActivo() {
        return this.idPlanActivo;
    }

    public void setIdPlanActivo(int idPlanActivo) {
        this.idPlanActivo = idPlanActivo;
    }

    public String getNombrePlanMantenimiento() {
        return this.nombrePlanMantenimiento;
    }

    public void setNombrePlanMantenimiento(String nombrePlanMantenimiento) {
        this.nombrePlanMantenimiento = nombrePlanMantenimiento;
    }

    public Boolean isPorKm() {
        return this.porKm;
    }

    public Boolean getPorKm() {
        return this.porKm;
    }

    public void setPorKm(Boolean porKm) {
        this.porKm = porKm;
    }

    public Boolean isPorHora() {
        return this.porHora;
    }

    public Boolean getPorHora() {
        return this.porHora;
    }

    public void setPorHora(Boolean porHora) {
        this.porHora = porHora;
    }

    public Boolean isPorPeriodo() {
        return this.porPeriodo;
    }

    public Boolean getPorPeriodo() {
        return this.porPeriodo;
    }

    public void setPorPeriodo(Boolean porPeriodo) {
        this.porPeriodo = porPeriodo;
    }

    public List<PlanMantenimiento> getActivoPlanes() {
        return this.activoPlanes;
    }

    public void setActivoPlanes(List<PlanMantenimiento> activoPlanes) {
        this.activoPlanes = activoPlanes;
    }
    
    
    
}
