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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lau Cerón
 */
@Entity
@Table(name = "perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "clsPerfil.findAll", query = "SELECT c FROM clsPerfil c")
    , @NamedQuery(name = "clsPerfil.findByCodPerfil", query = "SELECT c FROM clsPerfil c WHERE c.codPerfil = :codPerfil")
    , @NamedQuery(name = "clsPerfil.findByNombrePerfil", query = "SELECT c FROM clsPerfil c WHERE c.nombrePerfil = :nombrePerfil")})
public class clsPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_PERFIL")
    private String codPerfil;
    @Basic(optional = false)
    @Column(name = "NOMBRE_PERFIL")
    private String nombrePerfil;
    @JoinTable(name = "usuario_perfil", joinColumns = {
        @JoinColumn(name = "COD_PERFIL", referencedColumnName = "COD_PERFIL")}, inverseJoinColumns = {
        @JoinColumn(name = "COD_USUARIO", referencedColumnName = "COD_USUARIO")})
    @ManyToMany
    private List<clsUsuario> clsUsuarioList;
    @ManyToMany(mappedBy = "clsPerfilList")
    private List<clsAccion> clsAccionList;

    public clsPerfil() {
    }

    public clsPerfil(String codPerfil) {
        this.codPerfil = codPerfil;
    }

    public clsPerfil(String codPerfil, String nombrePerfil) {
        this.codPerfil = codPerfil;
        this.nombrePerfil = nombrePerfil;
    }

    public String getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(String codPerfil) {
        this.codPerfil = codPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    @XmlTransient
    public List<clsUsuario> getClsUsuarioList() {
        return clsUsuarioList;
    }

    public void setClsUsuarioList(List<clsUsuario> clsUsuarioList) {
        this.clsUsuarioList = clsUsuarioList;
    }

    @XmlTransient
    public List<clsAccion> getClsAccionList() {
        return clsAccionList;
    }

    public void setClsAccionList(List<clsAccion> clsAccionList) {
        this.clsAccionList = clsAccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPerfil != null ? codPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof clsPerfil)) {
            return false;
        }
        clsPerfil other = (clsPerfil) object;
        if ((this.codPerfil == null && other.codPerfil != null) || (this.codPerfil != null && !this.codPerfil.equals(other.codPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.clsPerfil[ codPerfil=" + codPerfil + " ]";
    }
    
}
