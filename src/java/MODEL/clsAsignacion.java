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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lau Cerón
 */
@Entity
@Table(name = "asignacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "clsAsignacion.findAll", query = "SELECT c FROM clsAsignacion c")
    , @NamedQuery(name = "clsAsignacion.findByCodAsignacion", query = "SELECT c FROM clsAsignacion c WHERE c.codAsignacion = :codAsignacion")
    , @NamedQuery(name = "clsAsignacion.findByNumPreguntas", query = "SELECT c FROM clsAsignacion c WHERE c.numPreguntas = :numPreguntas")
    , @NamedQuery(name = "clsAsignacion.findByBibliografiaSugerida", query = "SELECT c FROM clsAsignacion c WHERE c.bibliografiaSugerida = :bibliografiaSugerida")})
public class clsAsignacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_ASIGNACION")
    private String codAsignacion;
    @Basic(optional = false)
    @Column(name = "NUM_PREGUNTAS")
    private int numPreguntas;
    @Basic(optional = false)
    @Column(name = "BIBLIOGRAFIA_SUGERIDA")
    private String bibliografiaSugerida;
    @JoinColumn(name = "COD_EXAMEN", referencedColumnName = "COD_EXAMEN")
    @ManyToOne
    private clsExamen codExamen;
    @JoinColumn(name = "COD_MATERIA", referencedColumnName = "COD_MATERIA")
    @ManyToOne
    private clsMateria codMateria;
    @JoinColumn(name = "COD_USUARIO", referencedColumnName = "COD_USUARIO")
    @ManyToOne
    private clsUsuario codUsuario;
    @OneToMany(mappedBy = "codAsignacion")
    private List<clsReactivo> clsReactivoList;
    @OneToMany(mappedBy = "codAsignacion")
    private List<clsUsuario> clsUsuarioList;

    public clsAsignacion() {
    }

    public clsAsignacion(String codAsignacion) {
        this.codAsignacion = codAsignacion;
    }

    public clsAsignacion(String codAsignacion, int numPreguntas, String bibliografiaSugerida) {
        this.codAsignacion = codAsignacion;
        this.numPreguntas = numPreguntas;
        this.bibliografiaSugerida = bibliografiaSugerida;
    }

    public String getCodAsignacion() {
        return codAsignacion;
    }

    public void setCodAsignacion(String codAsignacion) {
        this.codAsignacion = codAsignacion;
    }

    public int getNumPreguntas() {
        return numPreguntas;
    }

    public void setNumPreguntas(int numPreguntas) {
        this.numPreguntas = numPreguntas;
    }

    public String getBibliografiaSugerida() {
        return bibliografiaSugerida;
    }

    public void setBibliografiaSugerida(String bibliografiaSugerida) {
        this.bibliografiaSugerida = bibliografiaSugerida;
    }

    public clsExamen getCodExamen() {
        return codExamen;
    }

    public void setCodExamen(clsExamen codExamen) {
        this.codExamen = codExamen;
    }

    public clsMateria getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(clsMateria codMateria) {
        this.codMateria = codMateria;
    }

    public clsUsuario getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(clsUsuario codUsuario) {
        this.codUsuario = codUsuario;
    }

    @XmlTransient
    public List<clsReactivo> getClsReactivoList() {
        return clsReactivoList;
    }

    public void setClsReactivoList(List<clsReactivo> clsReactivoList) {
        this.clsReactivoList = clsReactivoList;
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
        hash += (codAsignacion != null ? codAsignacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof clsAsignacion)) {
            return false;
        }
        clsAsignacion other = (clsAsignacion) object;
        if ((this.codAsignacion == null && other.codAsignacion != null) || (this.codAsignacion != null && !this.codAsignacion.equals(other.codAsignacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.clsAsignacion[ codAsignacion=" + codAsignacion + " ]";
    }
    
}
