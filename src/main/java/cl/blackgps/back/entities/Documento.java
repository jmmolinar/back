package cl.blackgps.back.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "documento")
public class Documento implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento", unique = true, nullable = false)
    private int idDocumento;

    @Column(name = "ruta_adjunto")
    private String rutaAdjunto;
    @Column(name = "fecha_adjunto")
    private LocalDateTime fechaAdjunto;
    @Column(name = "fecha_vencimiento")
    private LocalDateTime fechaVencimiento;
    
    @Column(name = "activo_id_activo")
    private int activoIdActivo;
    /*@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "activo_id_activo")
    private Activo activoDocumentos;*/

    /*@Column(name = "tipo_documento_id_tipo_documento")
    private int tipoDocumentoIdTipoDocumento;*/
    //RELACIÓN: Tipo de documento
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "tipo_documento_id_tipo_documento")
    private TipoDocumento tipoDocumento;

    public Documento() {
    }



    public Documento(int idDocumento, String rutaAdjunto, LocalDateTime fechaAdjunto, LocalDateTime fechaVencimiento, int activoIdActivo, TipoDocumento tipoDocumento) {
        this.idDocumento = idDocumento;
        this.rutaAdjunto = rutaAdjunto;
        this.fechaAdjunto = fechaAdjunto;
        this.fechaVencimiento = fechaVencimiento;
        this.activoIdActivo = activoIdActivo;
        this.tipoDocumento = tipoDocumento;
    }


    public int getIdDocumento() {
        return this.idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getRutaAdjunto() {
        return this.rutaAdjunto;
    }

    public void setRutaAdjunto(String rutaAdjunto) {
        this.rutaAdjunto = rutaAdjunto;
    }

    public LocalDateTime getFechaAdjunto() {
        return this.fechaAdjunto;
    }

    public void setFechaAdjunto(LocalDateTime fechaAdjunto) {
        this.fechaAdjunto = fechaAdjunto;
    }

    public LocalDateTime getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getActivoIdActivo() {
        return this.activoIdActivo;
    }

    public void setActivoIdActivo(int activoIdActivo) {
        this.activoIdActivo = activoIdActivo;
    }

    public TipoDocumento getTipoDocumento() {
        return this.tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    

}
