package cl.blackgps.back.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria_servicio")
public class CategoriaServicio implements Serializable{
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_categoria_servicio", unique = true, nullable = false)
    private int idCategoriaServicio;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "codigo")
    private String codigo;


    public CategoriaServicio() {
    }


    public CategoriaServicio(int idCategoriaServicio, String nombre, String codigo) {
        this.idCategoriaServicio = idCategoriaServicio;
        this.nombre = nombre;
        this.codigo = codigo;
    }


    public int getIdCategoriaServicio() {
        return this.idCategoriaServicio;
    }

    public void setIdCategoriaServicio(int idCategoriaServicio) {
        this.idCategoriaServicio = idCategoriaServicio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
