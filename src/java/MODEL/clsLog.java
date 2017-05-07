/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lau Cerón
 */
@Entity
@Table(name = "log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "clsLog.findAll", query = "SELECT c FROM clsLog c")
    , @NamedQuery(name = "clsLog.findByCodLog", query = "SELECT c FROM clsLog c WHERE c.codLog = :codLog")
    , @NamedQuery(name = "clsLog.findByNombreUsuarioLog", query = "SELECT c FROM clsLog c WHERE c.nombreUsuarioLog = :nombreUsuarioLog")
    , @NamedQuery(name = "clsLog.findByNombreTabla", query = "SELECT c FROM clsLog c WHERE c.nombreTabla = :nombreTabla")
    , @NamedQuery(name = "clsLog.findByAccion", query = "SELECT c FROM clsLog c WHERE c.accion = :accion")
    , @NamedQuery(name = "clsLog.findByCodigoRegistro", query = "SELECT c FROM clsLog c WHERE c.codigoRegistro = :codigoRegistro")
    , @NamedQuery(name = "clsLog.findByFecha", query = "SELECT c FROM clsLog c WHERE c.fecha = :fecha")})
public class clsLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_LOG")
    private String codLog;
    @Basic(optional = false)
    @Column(name = "NOMBRE_USUARIO_LOG")
    private String nombreUsuarioLog;
    @Basic(optional = false)
    @Column(name = "NOMBRE_TABLA")
    private String nombreTabla;
    @Basic(optional = false)
    @Column(name = "ACCION")
    private String accion;
    @Basic(optional = false)
    @Column(name = "CODIGO_REGISTRO")
    private String codigoRegistro;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public clsLog() {
    }

    public clsLog(String codLog) {
        this.codLog = codLog;
    }

    public clsLog(String codLog, String nombreUsuarioLog, String nombreTabla, String accion, String codigoRegistro, Date fecha) {
        this.codLog = codLog;
        this.nombreUsuarioLog = nombreUsuarioLog;
        this.nombreTabla = nombreTabla;
        this.accion = accion;
        this.codigoRegistro = codigoRegistro;
        this.fecha = fecha;
    }

    public String getCodLog() {
        return codLog;
    }

    public void setCodLog(String codLog) {
        this.codLog = codLog;
    }

    public String getNombreUsuarioLog() {
        return nombreUsuarioLog;
    }

    public void setNombreUsuarioLog(String nombreUsuarioLog) {
        this.nombreUsuarioLog = nombreUsuarioLog;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codLog != null ? codLog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof clsLog)) {
            return false;
        }
        clsLog other = (clsLog) object;
        if ((this.codLog == null && other.codLog != null) || (this.codLog != null && !this.codLog.equals(other.codLog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.clsLog[ codLog=" + codLog + " ]";
    }
    
}
