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
@Table(name = "accion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "clsAccion.findAll", query = "SELECT c FROM clsAccion c")
    , @NamedQuery(name = "clsAccion.findByCodAccion", query = "SELECT c FROM clsAccion c WHERE c.codAccion = :codAccion")
    , @NamedQuery(name = "clsAccion.findByNombreAccion", query = "SELECT c FROM clsAccion c WHERE c.nombreAccion = :nombreAccion")})
public class clsAccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_ACCION")
    private String codAccion;
    @Basic(optional = false)
    @Column(name = "NOMBRE_ACCION")
    private String nombreAccion;
    @JoinTable(name = "perfil_accion", joinColumns = {
        @JoinColumn(name = "COD_ACCION", referencedColumnName = "COD_ACCION")}, inverseJoinColumns = {
        @JoinColumn(name = "COD_PERFIL", referencedColumnName = "COD_PERFIL")})
    @ManyToMany
    private List<clsPerfil> clsPerfilList;

    public clsAccion() {
    }

    public clsAccion(String codAccion) {
        this.codAccion = codAccion;
    }

    public clsAccion(String codAccion, String nombreAccion) {
        this.codAccion = codAccion;
        this.nombreAccion = nombreAccion;
    }

    public String getCodAccion() {
        return codAccion;
    }

    public void setCodAccion(String codAccion) {
        this.codAccion = codAccion;
    }

    public String getNombreAccion() {
        return nombreAccion;
    }

    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }

    @XmlTransient
    public List<clsPerfil> getClsPerfilList() {
        return clsPerfilList;
    }

    public void setClsPerfilList(List<clsPerfil> clsPerfilList) {
        this.clsPerfilList = clsPerfilList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codAccion != null ? codAccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof clsAccion)) {
            return false;
        }
        clsAccion other = (clsAccion) object;
        if ((this.codAccion == null && other.codAccion != null) || (this.codAccion != null && !this.codAccion.equals(other.codAccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.clsAccion[ codAccion=" + codAccion + " ]";
    }
    
}
