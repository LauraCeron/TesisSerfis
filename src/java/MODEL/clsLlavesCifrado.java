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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "llaves_cifrado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "clsLlavesCifrado.findAll", query = "SELECT c FROM clsLlavesCifrado c")
    , @NamedQuery(name = "clsLlavesCifrado.findByCodLlavesCifrado", query = "SELECT c FROM clsLlavesCifrado c WHERE c.codLlavesCifrado = :codLlavesCifrado")
    , @NamedQuery(name = "clsLlavesCifrado.findByFechainicio", query = "SELECT c FROM clsLlavesCifrado c WHERE c.fechainicio = :fechainicio")
    , @NamedQuery(name = "clsLlavesCifrado.findByFechafin", query = "SELECT c FROM clsLlavesCifrado c WHERE c.fechafin = :fechafin")
    , @NamedQuery(name = "clsLlavesCifrado.findByEstado", query = "SELECT c FROM clsLlavesCifrado c WHERE c.estado = :estado")})
public class clsLlavesCifrado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_LLAVES_CIFRADO")
    private String codLlavesCifrado;
    @Basic(optional = false)
    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainicio;
    @Basic(optional = false)
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafin;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private boolean estado;
    @JoinColumn(name = "COD_USUARIO", referencedColumnName = "COD_USUARIO")
    @ManyToOne
    private clsUsuario codUsuario;

    public clsLlavesCifrado() {
    }

    public clsLlavesCifrado(String codLlavesCifrado) {
        this.codLlavesCifrado = codLlavesCifrado;
    }

    public clsLlavesCifrado(String codLlavesCifrado, Date fechainicio, Date fechafin, boolean estado) {
        this.codLlavesCifrado = codLlavesCifrado;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.estado = estado;
    }

    public String getCodLlavesCifrado() {
        return codLlavesCifrado;
    }

    public void setCodLlavesCifrado(String codLlavesCifrado) {
        this.codLlavesCifrado = codLlavesCifrado;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public clsUsuario getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(clsUsuario codUsuario) {
        this.codUsuario = codUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codLlavesCifrado != null ? codLlavesCifrado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof clsLlavesCifrado)) {
            return false;
        }
        clsLlavesCifrado other = (clsLlavesCifrado) object;
        if ((this.codLlavesCifrado == null && other.codLlavesCifrado != null) || (this.codLlavesCifrado != null && !this.codLlavesCifrado.equals(other.codLlavesCifrado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.clsLlavesCifrado[ codLlavesCifrado=" + codLlavesCifrado + " ]";
    }
    
}
