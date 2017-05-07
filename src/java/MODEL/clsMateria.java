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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.metamodel.SingularAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lau Cerón
 */
@Entity
@Table(name = "materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "clsMateria.findAll", query = "SELECT c FROM clsMateria c")
    , @NamedQuery(name = "clsMateria.findByCodMateria", query = "SELECT c FROM clsMateria c WHERE c.codMateria = :codMateria")
    , @NamedQuery(name = "clsMateria.findByNombreMateria", query = "SELECT c FROM clsMateria c WHERE c.nombreMateria = :nombreMateria")
    , @NamedQuery(name = "clsMateria.findByEstado", query = "SELECT c FROM clsMateria c WHERE c.estado = :estado")})
public class clsMateria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_MATERIA")
    private String codMateria;
    @Basic(optional = false)
    @Column(name = "NOMBRE_MATERIA")
    private String nombreMateria;
    @Column(name = "ESTADO")
    private Boolean estado;
    @OneToMany(mappedBy = "codMateria")
    private List<clsAsignacion> clsAsignacionList;
    @JoinColumn(name = "COD_AREA", referencedColumnName = "COD_AREA")
    @ManyToOne
    private clsArea codArea;

    public clsMateria() {
    }

    public clsMateria(String codMateria) {
        this.codMateria = codMateria;
    }

    public clsMateria(String codMateria, String nombreMateria) {
        this.codMateria = codMateria;
        this.nombreMateria = nombreMateria;
    }

    public String getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(String codMateria) {
        this.codMateria = codMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<clsAsignacion> getClsAsignacionList() {
        return clsAsignacionList;
    }

    public void setClsAsignacionList(List<clsAsignacion> clsAsignacionList) {
        this.clsAsignacionList = clsAsignacionList;
    }

    public clsArea getCodArea() {
        return codArea;
    }

    public void setCodArea(clsArea codArea) {
        this.codArea = codArea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codMateria != null ? codMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof clsMateria)) {
            return false;
        }
        clsMateria other = (clsMateria) object;
        if ((this.codMateria == null && other.codMateria != null) || (this.codMateria != null && !this.codMateria.equals(other.codMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.clsMateria[ codMateria=" + codMateria + " ]";
    }

    
    
}
