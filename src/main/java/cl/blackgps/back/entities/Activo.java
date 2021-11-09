package cl.blackgps.back.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import cl.blackgps.back.dto.ActivoDTO;

@Entity
@Table(name = "activo")

/** INICIO - DTO PARA QUERY NATIVO */
@NamedNativeQuery(
    name = "consultarActivoDTO",
    query = "SELECT "
    + "activo.id_activo AS idActivo, "
    + "activo.vehiculo_id_vehiculo AS vehiculoIdVehiculo, "
    + "activo.area_id_area AS areaIdArea, "
    + "activo.bodega_activos_id_bodega_activos AS bodegaActivosIdBodega, "
    + "bodega_activos.nombre AS 'nombreBodega', "
    + "activo.tipo_activo_id_tipo_activo AS tipoActivoIdTipoActivo, "
    + "tipo_activo.nombre AS 'nombreTipoActivo', "
    + "activo.anio AS anio, "
    + "activo.dado_de_baja AS dadoDeBaja, "
    + "activo_has_plan_mantenimiento.activo_id_activo AS idActivoPlanes, "
    + "activo_has_plan_mantenimiento.plan_mantenimiento_id_plan_mantenimiento AS idPlanActivo, "
    + "plan_mantenimiento.nombre AS 'nombrePlanMantenimiento', "
    + "plan_mantenimiento.por_km AS 'porKm', "
    + "plan_mantenimiento.por_hora AS 'porHora', "
    + "plan_mantenimiento.por_periodo AS 'porPeriodo' "
    + "FROM activo "
    + "LEFT JOIN "
    + "bodega_activos ON activo.bodega_activos_id_bodega_activos = bodega_activos.id_bodega_activos "
    + "LEFT JOIN "
    + "tipo_activo ON activo.tipo_activo_id_tipo_activo = tipo_activo.id_tipo_activo "
    + "LEFT JOIN "
    + "activo_has_plan_mantenimiento ON activo.id_activo = activo_has_plan_mantenimiento.activo_id_activo "
    + "LEFT JOIN "
    + "plan_mantenimiento ON activo_has_plan_mantenimiento.plan_mantenimiento_id_plan_mantenimiento = plan_mantenimiento.id_plan_mantenimiento "       
    + "WHERE activo.id_activo = :identificador "
    + "ORDER BY activo.id_activo",
    resultSetMapping = "ActivoDTO"
)
@SqlResultSetMapping(
    name = "ActivoDTO",
    classes = @ConstructorResult(
        targetClass = ActivoDTO.class,
        columns = {
            @ColumnResult(name = "idActivo", type = Integer.class),
            @ColumnResult(name = "vehiculoIdVehiculo", type = Integer.class),
            @ColumnResult(name = "areaIdArea", type = Integer.class),
            @ColumnResult(name = "bodegaActivosIdBodega", type = Integer.class),
            @ColumnResult(name = "nombreBodega", type = String.class),
            @ColumnResult(name = "tipoActivoIdTipoActivo", type = Integer.class),
            @ColumnResult(name = "nombreTipoActivo", type = String.class),
            @ColumnResult(name = "anio", type = Integer.class),
            @ColumnResult(name = "dadoDeBaja", type = Boolean.class),
            @ColumnResult(name = "idActivoPlanes", type = Integer.class),
            @ColumnResult(name = "idPlanActivo", type = Integer.class),
            @ColumnResult(name = "nombrePlanMantenimiento", type = String.class),
            @ColumnResult(name = "porKm", type = Boolean.class),
            @ColumnResult(name = "porHora", type = Boolean.class),
            @ColumnResult(name = "porPeriodo", type = Boolean.class)
            /*@ColumnResult(name = "activoPlanes", type = PlanMantenimiento.class),*/
            /*@ColumnResult(name = "planMantenimientoIdPlanMantenimiento", type = Integer.class),
            @ColumnResult(name = "nombrePlanMantenimiento", type = String.class)*/
        }
    )
)
/** FIN - DTO PARA QUERY NATIVO */

public class Activo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activo", unique = true, nullable = false)
    private int idActivo;

    @Column(name = "anio")
    private int anio;
    @Column(name = "dado_de_baja")
    private Boolean dadoDeBaja;
    @Column(name = "vehiculo_id_vehiculo")
    private int vehiculoIdVehiculo;
    @Column(name = "area_id_area")
    private int areaIdArea;

    /*@Column(name = "tipo_activo_id_tipo_activo")
    private int tipoActivoIdTipoActivo;*/

    /*@Column(name = "bodega_activos_id_bodega_activos")
    private int bodegaActivosIdBodegaActivos;*/


    //RELACIÓN: Bodega de activos
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "bodega_activos_id_bodega_activos")
    private BodegaActivos bodegaActivos;

    //RELACIÓN: Tipo de activo
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "tipo_activo_id_tipo_activo")
    private TipoActivo tipoActivo;

    
    //RELACIÓN: Planes de mantenimiento
    //@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
        name = "activo_has_plan_mantenimiento",
        joinColumns = @JoinColumn(name = "activo_id_activo"),
        inverseJoinColumns = @JoinColumn(name = "plan_mantenimiento_id_plan_mantenimiento")
    )
    private List<PlanMantenimiento> activoPlanes = new ArrayList<PlanMantenimiento>();



    //RELACIÓN: Documentos del activo
    //@OneToMany(mappedBy = "activoDocumentos",cascade = {CascadeType.DETACH, CascadeType.MERGE}, orphanRemoval = true) // Los cambios que se hagan en Activo se reflejaran en sus Documentos
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true) // Los cambios que se hagan en Activo se reflejaran en sus Documentos
    @JoinColumn(name = "activo_id_activo") // clave foranea en Documento
    private List<Documento> documentos = new ArrayList<Documento>();

    //RELACIÓN: Ordenes del activo
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "activo_id_activo")
    private List<Orden> ordenes = new ArrayList<Orden>();


    public Activo() {
    }

    public Activo(int idActivo, int anio, Boolean dadoDeBaja, int vehiculoIdVehiculo, int areaIdArea, BodegaActivos bodegaActivos, TipoActivo tipoActivo, List<PlanMantenimiento> activoPlanes, List<Documento> documentos, List<Orden> ordenes) {
        this.idActivo = idActivo;
        this.anio = anio;
        this.dadoDeBaja = dadoDeBaja;
        this.vehiculoIdVehiculo = vehiculoIdVehiculo;
        this.areaIdArea = areaIdArea;
        this.bodegaActivos = bodegaActivos;
        this.tipoActivo = tipoActivo;
        this.activoPlanes = activoPlanes;
        this.documentos = documentos;
        this.ordenes = ordenes;
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

    public Boolean isDadoDeBaja() {
        return this.dadoDeBaja;
    }

    public Boolean getDadoDeBaja() {
        return this.dadoDeBaja;
    }

    public void setDadoDeBaja(Boolean dadoDeBaja) {
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

    public BodegaActivos getBodegaActivos() {
        return this.bodegaActivos;
    }

    public void setBodegaActivos(BodegaActivos bodegaActivos) {
        this.bodegaActivos = bodegaActivos;
    }

    public TipoActivo getTipoActivo() {
        return this.tipoActivo;
    }

    public void setTipoActivo(TipoActivo tipoActivo) {
        this.tipoActivo = tipoActivo;
    }

    public List<PlanMantenimiento> getActivoPlanes() {
        return this.activoPlanes;
    }

    public void setActivoPlanes(List<PlanMantenimiento> activoPlanes) {
        this.activoPlanes = activoPlanes;
    }

    public List<Documento> getDocumentos() {
        return this.documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public List<Orden> getOrdenes() {
        return this.ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }


    
}
