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
import javax.persistence.Lob;
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
@Table(name = "reactivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "clsReactivo.findAll", query = "SELECT c FROM clsReactivo c")
    , @NamedQuery(name = "clsReactivo.findByCodReactivo", query = "SELECT c FROM clsReactivo c WHERE c.codReactivo = :codReactivo")
    , @NamedQuery(name = "clsReactivo.findByTema", query = "SELECT c FROM clsReactivo c WHERE c.tema = :tema")
    , @NamedQuery(name = "clsReactivo.findByNivelTaxonomico", query = "SELECT c FROM clsReactivo c WHERE c.nivelTaxonomico = :nivelTaxonomico")
    , @NamedQuery(name = "clsReactivo.findByNivelDificultad", query = "SELECT c FROM clsReactivo c WHERE c.nivelDificultad = :nivelDificultad")
    , @NamedQuery(name = "clsReactivo.findByRespuestaCorrecta", query = "SELECT c FROM clsReactivo c WHERE c.respuestaCorrecta = :respuestaCorrecta")
    , @NamedQuery(name = "clsReactivo.findByDetalleBibliografia", query = "SELECT c FROM clsReactivo c WHERE c.detalleBibliografia = :detalleBibliografia")
    , @NamedQuery(name = "clsReactivo.findByEstadoReactivo", query = "SELECT c FROM clsReactivo c WHERE c.estadoReactivo = :estadoReactivo")
    , @NamedQuery(name = "clsReactivo.findByCodExamen", query = "SELECT c FROM clsReactivo c WHERE c.codExamen = :codExamen")})
public class clsReactivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_REACTIVO")
    private String codReactivo;
    @Basic(optional = false)
    @Column(name = "TEMA")
    private String tema;
    @Basic(optional = false)
    @Column(name = "NIVEL_TAXONOMICO")
    private String nivelTaxonomico;
    @Basic(optional = false)
    @Column(name = "NIVEL_DIFICULTAD")
    private String nivelDificultad;
    @Basic(optional = false)
    @Lob
    @Column(name = "ENUNCIADO")
    private String enunciado;
    @Basic(optional = false)
    @Column(name = "RESPUESTA_CORRECTA")
    private String respuestaCorrecta;
    @Basic(optional = false)
    @Column(name = "DETALLE_BIBLIOGRAFIA")
    private String detalleBibliografia;
    @Basic(optional = false)
    @Column(name = "ESTADO_REACTIVO")
    private String estadoReactivo;
    @Column(name = "COD_EXAMEN")
    private String codExamen;
    @JoinColumn(name = "COD_ASIGNACION", referencedColumnName = "COD_ASIGNACION")
    @ManyToOne
    private clsAsignacion codAsignacion;
    @OneToMany(mappedBy = "codReactivo")
    private List<clsDetalle> clsDetalleList;

    public clsReactivo() {
    }

    public clsReactivo(String codReactivo) {
        this.codReactivo = codReactivo;
    }

    public clsReactivo(String codReactivo, String tema, String nivelTaxonomico, String nivelDificultad, String enunciado, String respuestaCorrecta, String detalleBibliografia, String estadoReactivo) {
        this.codReactivo = codReactivo;
        this.tema = tema;
        this.nivelTaxonomico = nivelTaxonomico;
        this.nivelDificultad = nivelDificultad;
        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
        this.detalleBibliografia = detalleBibliografia;
        this.estadoReactivo = estadoReactivo;
    }

    public String getCodReactivo() {
        return codReactivo;
    }

    public void setCodReactivo(String codReactivo) {
        this.codReactivo = codReactivo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getNivelTaxonomico() {
        return nivelTaxonomico;
    }

    public void setNivelTaxonomico(String nivelTaxonomico) {
        this.nivelTaxonomico = nivelTaxonomico;
    }

    public String getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(String nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getDetalleBibliografia() {
        return detalleBibliografia;
    }

    public void setDetalleBibliografia(String detalleBibliografia) {
        this.detalleBibliografia = detalleBibliografia;
    }

    public String getEstadoReactivo() {
        return estadoReactivo;
    }

    public void setEstadoReactivo(String estadoReactivo) {
        this.estadoReactivo = estadoReactivo;
    }

    public String getCodExamen() {
        return codExamen;
    }

    public void setCodExamen(String codExamen) {
        this.codExamen = codExamen;
    }

    public clsAsignacion getCodAsignacion() {
        return codAsignacion;
    }

    public void setCodAsignacion(clsAsignacion codAsignacion) {
        this.codAsignacion = codAsignacion;
    }

    @XmlTransient
    public List<clsDetalle> getClsDetalleList() {
        return clsDetalleList;
    }

    public void setClsDetalleList(List<clsDetalle> clsDetalleList) {
        this.clsDetalleList = clsDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codReactivo != null ? codReactivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof clsReactivo)) {
            return false;
        }
        clsReactivo other = (clsReactivo) object;
        if ((this.codReactivo == null && other.codReactivo != null) || (this.codReactivo != null && !this.codReactivo.equals(other.codReactivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.clsReactivo[ codReactivo=" + codReactivo + " ]";
    }
    
}
