/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lau Cerón
 */
@Entity
@Table(name = "detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "clsDetalle.findAll", query = "SELECT c FROM clsDetalle c")
    , @NamedQuery(name = "clsDetalle.findByCodDetalleReactivo", query = "SELECT c FROM clsDetalle c WHERE c.codDetalleReactivo = :codDetalleReactivo")
    , @NamedQuery(name = "clsDetalle.findByRespuesta", query = "SELECT c FROM clsDetalle c WHERE c.respuesta = :respuesta")
    , @NamedQuery(name = "clsDetalle.findByJustificacion", query = "SELECT c FROM clsDetalle c WHERE c.justificacion = :justificacion")
    , @NamedQuery(name = "clsDetalle.findByCorrecta", query = "SELECT c FROM clsDetalle c WHERE c.correcta = :correcta")})
public class clsDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_DETALLE_REACTIVO")
    private String codDetalleReactivo;
    @Basic(optional = false)
    @Column(name = "RESPUESTA")
    private String respuesta;
    @Basic(optional = false)
    @Column(name = "JUSTIFICACION")
    private String justificacion;
    @Basic(optional = false)
    @Column(name = "CORRECTA")
    private boolean correcta;
    @JoinColumn(name = "COD_REACTIVO", referencedColumnName = "COD_REACTIVO")
    @ManyToOne
    private clsReactivo codReactivo;

    public clsDetalle() {
    }

    public clsDetalle(String codDetalleReactivo) {
        this.codDetalleReactivo = codDetalleReactivo;
    }

    public clsDetalle(String codDetalleReactivo, String respuesta, String justificacion, boolean correcta) {
        this.codDetalleReactivo = codDetalleReactivo;
        this.respuesta = respuesta;
        this.justificacion = justificacion;
        this.correcta = correcta;
    }

    public String getCodDetalleReactivo() {
        return codDetalleReactivo;
    }

    public void setCodDetalleReactivo(String codDetalleReactivo) {
        this.codDetalleReactivo = codDetalleReactivo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

    public clsReactivo getCodReactivo() {
        return codReactivo;
    }

    public void setCodReactivo(clsReactivo codReactivo) {
        this.codReactivo = codReactivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDetalleReactivo != null ? codDetalleReactivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof clsDetalle)) {
            return false;
        }
        clsDetalle other = (clsDetalle) object;
        if ((this.codDetalleReactivo == null && other.codDetalleReactivo != null) || (this.codDetalleReactivo != null && !this.codDetalleReactivo.equals(other.codDetalleReactivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.clsDetalle[ codDetalleReactivo=" + codDetalleReactivo + " ]";
    }
    
}
