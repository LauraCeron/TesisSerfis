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
@Table(name = "area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "clsArea.findAll", query = "SELECT c FROM clsArea c")
    , @NamedQuery(name = "clsArea.findByCodArea", query = "SELECT c FROM clsArea c WHERE c.codArea = :codArea")
    , @NamedQuery(name = "clsArea.findByNombreArea", query = "SELECT c FROM clsArea c WHERE c.nombreArea = :nombreArea")
    , @NamedQuery(name = "clsArea.findByEstadoArea", query = "SELECT c FROM clsArea c WHERE c.estadoArea = :estadoArea")})
public class clsArea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_AREA")
    private String codArea;
    @Basic(optional = false)
    @Column(name = "NOMBRE_AREA")
    private String nombreArea;
    @Basic(optional = false)
    @Column(name = "ESTADO_AREA")
    private boolean estadoArea;
    @OneToMany(mappedBy = "codArea")
    private List<clsMateria> clsMateriaList;
    @OneToMany(mappedBy = "codArea")
    private List<clsUsuario> clsUsuarioList;

    public clsArea() {
    }

    public clsArea(String codArea) {
        this.codArea = codArea;
    }

    public clsArea(String codArea, String nombreArea, boolean estadoArea) {
        this.codArea = codArea;
        this.nombreArea = nombreArea;
        this.estadoArea = estadoArea;
    }

    public String getCodArea() {
        return codArea;
    }

    public void setCodArea(String codArea) {
        this.codArea = codArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public boolean getEstadoArea() {
        return estadoArea;
    }

    public void setEstadoArea(boolean estadoArea) {
        this.estadoArea = estadoArea;
    }

    @XmlTransient
    public List<clsMateria> getClsMateriaList() {
        return clsMateriaList;
    }

    public void setClsMateriaList(List<clsMateria> clsMateriaList) {
        this.clsMateriaList = clsMateriaList;
    }

    @XmlTransient
    public List<clsUsuario> getClsUsuarioList() {
        return clsUsuarioList;
    }

    public void setClsUsuarioList(List<clsUsuario> clsUsuarioList) {
        this.clsUsuarioList = clsUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codArea != null ? codArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof clsArea)) {
            return false;
        }
        clsArea other = (clsArea) object;
        if ((this.codArea == null && other.codArea != null) || (this.codArea != null && !this.codArea.equals(other.codArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.clsArea[ codArea=" + codArea + " ]";
    }
    
}
