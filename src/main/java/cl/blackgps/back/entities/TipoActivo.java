package cl.blackgps.back.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_activo")
/* @Audited */ /* REVISAR EL TEMA DE LA AUDITORIA*/
/*public class TipoActivo extends Base{*/
public class TipoActivo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_activo", unique = true, nullable = false)
    private int idTipoActivo;

    @Column(name = "nombre")
    private String nombre;


    public TipoActivo() {
    }


    public TipoActivo(int idTipoActivo, String nombre) {
        this.idTipoActivo = idTipoActivo;
        this.nombre = nombre;
    }


    public int getIdTipoActivo() {
        return this.idTipoActivo;
    }

    public void setIdTipoActivo(int idTipoActivo) {
        this.idTipoActivo = idTipoActivo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
