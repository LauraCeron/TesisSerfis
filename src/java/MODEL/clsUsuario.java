/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lau Cerón
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "clsUsuario.findAll", query = "SELECT c FROM clsUsuario c")
    , @NamedQuery(name = "clsUsuario.findByCodUsuario", query = "SELECT c FROM clsUsuario c WHERE c.codUsuario = :codUsuario")
    , @NamedQuery(name = "clsUsuario.findByNombreUsuario", query = "SELECT c FROM clsUsuario c WHERE c.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "clsUsuario.findByCorreo", query = "SELECT c FROM clsUsuario c WHERE c.correo = :correo")
    , @NamedQuery(name = "clsUsuario.findByIdentificacion", query = "SELECT c FROM clsUsuario c WHERE c.identificacion = :identificacion")})
public class clsUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_USUARIO")
    private String codUsuario;
    @Basic(optional = false)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "CORREO")
    private String correo;
    @Basic(optional = false)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @ManyToMany(mappedBy = "clsUsuarioList")
    private List<clsPerfil> clsPerfilList;
    @OneToMany(mappedBy = "codUsuario")
    private List<clsAsignacion> clsAsignacionList;
    @OneToMany(mappedBy = "codUsuario")
    private List<clsExamen> clsExamenList;
    @JoinColumn(name = "COD_AREA", referencedColumnName = "COD_AREA")
    @ManyToOne
    private clsArea codArea;
    @JoinColumn(name = "COD_ASIGNACION", referencedColumnName = "COD_ASIGNACION")
    @ManyToOne
    private clsAsignacion codAsignacion;
    @OneToMany(mappedBy = "codUsuario")
    private List<clsLlavesCifrado> clsLlavesCifradoList;

    public clsUsuario() {
    }

    public clsUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public clsUsuario(String codUsuario, String nombreUsuario, String correo, String identificacion) {
        this.codUsuario = codUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.identificacion = identificacion;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @XmlTransient
    public List<clsPerfil> getClsPerfilList() {
        return clsPerfilList;
    }

    public void setClsPerfilList(List<clsPerfil> clsPerfilList) {
        this.clsPerfilList = clsPerfilList;
    }

    @XmlTransient
    public List<clsAsignacion> getClsAsignacionList() {
        return clsAsignacionList;
    }

    public void setClsAsignacionList(List<clsAsignacion> clsAsignacionList) {
        this.clsAsignacionList = clsAsignacionList;
    }

    @XmlTransient
    public List<clsExamen> getClsExamenList() {
        return clsExamenList;
    }

    public void setClsExamenList(List<clsExamen> clsExamenList) {
        this.clsExamenList = clsExamenList;
    }

    public clsArea getCodArea() {
        return codArea;
    }

    public void setCodArea(clsArea codArea) {
        this.codArea = codArea;
    }

    public clsAsignacion getCodAsignacion() {
        return codAsignacion;
    }

    public void setCodAsignacion(clsAsignacion codAsignacion) {
        this.codAsignacion = codAsignacion;
    }

    @XmlTransient
    public List<clsLlavesCifrado> getClsLlavesCifradoList() {
        return clsLlavesCifradoList;
    }

    public void setClsLlavesCifradoList(List<clsLlavesCifrado> clsLlavesCifradoList) {
        this.clsLlavesCifradoList = clsLlavesCifradoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUsuario != null ? codUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof clsUsuario)) {
            return false;
        }
        clsUsuario other = (clsUsuario) object;
        if ((this.codUsuario == null && other.codUsuario != null) || (this.codUsuario != null && !this.codUsuario.equals(other.codUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.clsUsuario[ codUsuario=" + codUsuario + " ]";
    }
    
}
