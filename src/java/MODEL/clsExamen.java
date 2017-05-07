/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lau Cerón
 */
@Entity
@Table(name = "examen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "clsExamen.findAll", query = "SELECT c FROM clsExamen c")
    , @NamedQuery(name = "clsExamen.findByCodExamen", query = "SELECT c FROM clsExamen c WHERE c.codExamen = :codExamen")
    , @NamedQuery(name = "clsExamen.findByTipo", query = "SELECT c FROM clsExamen c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "clsExamen.findByDescripcion", query = "SELECT c FROM clsExamen c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "clsExamen.findByNumPreguntasExamen", query = "SELECT c FROM clsExamen c WHERE c.numPreguntasExamen = :numPreguntasExamen")
    , @NamedQuery(name = "clsExamen.findByFechainicio", query = "SELECT c FROM clsExamen c WHERE c.fechainicio = :fechainicio")
    , @NamedQuery(name = "clsExamen.findByFechafin", query = "SELECT c FROM clsExamen c WHERE c.fechafin = :fechafin")})
public class clsExamen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_EXAMEN")
    private String codExamen;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "NUM_PREGUNTAS_EXAMEN")
    private int numPreguntasExamen;
    @Basic(optional = false)
    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainicio;
    @Basic(optional = false)
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafin;
    @OneToMany(mappedBy = "codExamen")
    private List<clsAsignacion> clsAsignacionList;
    @JoinColumn(name = "COD_USUARIO", referencedColumnName = "COD_USUARIO")
    @ManyToOne
    private clsUsuario codUsuario;

    public clsExamen() {
    }

    public clsExamen(String codExamen) {
        this.codExamen = codExamen;
    }

    public clsExamen(String codExamen, String tipo, String descripcion, int numPreguntasExamen, Date fechainicio, Date fechafin) {
        this.codExamen = codExamen;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.numPreguntasExamen = numPreguntasExamen;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
    }

    public String getCodExamen() {
        return codExamen;
    }

    public void setCodExamen(String codExamen) {
        this.codExamen = codExamen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumPreguntasExamen() {
        return numPreguntasExamen;
    }

    public void setNumPreguntasExamen(int numPreguntasExamen) {
        this.numPreguntasExamen = numPreguntasExamen;
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

    @XmlTransient
    public List<clsAsignacion> getClsAsignacionList() {
        return clsAsignacionList;
    }

    public void setClsAsignacionList(List<clsAsignacion> clsAsignacionList) {
        this.clsAsignacionList = clsAsignacionList;
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
        hash += (codExamen != null ? codExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof clsExamen)) {
            return false;
        }
        clsExamen other = (clsExamen) object;
        if ((this.codExamen == null && other.codExamen != null) || (this.codExamen != null && !this.codExamen.equals(other.codExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.clsExamen[ codExamen=" + codExamen + " ]";
    }
    
}
