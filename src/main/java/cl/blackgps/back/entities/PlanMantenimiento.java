package cl.blackgps.back.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plan_mantenimiento")
public class PlanMantenimiento implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan_mantenimiento", unique = true, nullable = false)
    private int idPlanMantenimiento;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "por_km")
    private Boolean porKm;

    @Column(name = "por_hora")
    private Boolean porHora;

    @Column(name = "por_periodo")
    private Boolean porPeriodo;

    /*@ManyToMany(mappedBy = "activoPlanes")
    private List<Activo> activos = new ArrayList<Activo>();*/


    public PlanMantenimiento() {
    }



    public PlanMantenimiento(int idPlanMantenimiento, String nombre, Boolean porKm, Boolean porHora, Boolean porPeriodo) {
        this.idPlanMantenimiento = idPlanMantenimiento;
        this.nombre = nombre;
        this.porKm = porKm;
        this.porHora = porHora;
        this.porPeriodo = porPeriodo;
    }


    public int getIdPlanMantenimiento() {
        return this.idPlanMantenimiento;
    }

    public void setIdPlanMantenimiento(int idPlanMantenimiento) {
        this.idPlanMantenimiento = idPlanMantenimiento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    
}
