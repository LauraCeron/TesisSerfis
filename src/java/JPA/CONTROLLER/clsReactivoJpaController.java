/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA.CONTROLLER;

import JPA.CONTROLLER.exceptions.NonexistentEntityException;
import JPA.CONTROLLER.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import MODEL.clsAsignacion;
import MODEL.clsDetalle;
import MODEL.clsReactivo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lau Cerón
 */
public class clsReactivoJpaController implements Serializable {

    public clsReactivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(clsReactivo clsReactivo) throws PreexistingEntityException, Exception {
        if (clsReactivo.getClsDetalleList() == null) {
            clsReactivo.setClsDetalleList(new ArrayList<clsDetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsAsignacion codAsignacion = clsReactivo.getCodAsignacion();
            if (codAsignacion != null) {
                codAsignacion = em.getReference(codAsignacion.getClass(), codAsignacion.getCodAsignacion());
                clsReactivo.setCodAsignacion(codAsignacion);
            }
            List<clsDetalle> attachedClsDetalleList = new ArrayList<clsDetalle>();
            for (clsDetalle clsDetalleListclsDetalleToAttach : clsReactivo.getClsDetalleList()) {
                clsDetalleListclsDetalleToAttach = em.getReference(clsDetalleListclsDetalleToAttach.getClass(), clsDetalleListclsDetalleToAttach.getCodDetalleReactivo());
                attachedClsDetalleList.add(clsDetalleListclsDetalleToAttach);
            }
            clsReactivo.setClsDetalleList(attachedClsDetalleList);
            em.persist(clsReactivo);
            if (codAsignacion != null) {
                codAsignacion.getClsReactivoList().add(clsReactivo);
                codAsignacion = em.merge(codAsignacion);
            }
            for (clsDetalle clsDetalleListclsDetalle : clsReactivo.getClsDetalleList()) {
                clsReactivo oldCodReactivoOfClsDetalleListclsDetalle = clsDetalleListclsDetalle.getCodReactivo();
                clsDetalleListclsDetalle.setCodReactivo(clsReactivo);
                clsDetalleListclsDetalle = em.merge(clsDetalleListclsDetalle);
                if (oldCodReactivoOfClsDetalleListclsDetalle != null) {
                    oldCodReactivoOfClsDetalleListclsDetalle.getClsDetalleList().remove(clsDetalleListclsDetalle);
                    oldCodReactivoOfClsDetalleListclsDetalle = em.merge(oldCodReactivoOfClsDetalleListclsDetalle);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findclsReactivo(clsReactivo.getCodReactivo()) != null) {
                throw new PreexistingEntityException("clsReactivo " + clsReactivo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(clsReactivo clsReactivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsReactivo persistentclsReactivo = em.find(clsReactivo.class, clsReactivo.getCodReactivo());
            clsAsignacion codAsignacionOld = persistentclsReactivo.getCodAsignacion();
            clsAsignacion codAsignacionNew = clsReactivo.getCodAsignacion();
            List<clsDetalle> clsDetalleListOld = persistentclsReactivo.getClsDetalleList();
            List<clsDetalle> clsDetalleListNew = clsReactivo.getClsDetalleList();
            if (codAsignacionNew != null) {
                codAsignacionNew = em.getReference(codAsignacionNew.getClass(), codAsignacionNew.getCodAsignacion());
                clsReactivo.setCodAsignacion(codAsignacionNew);
            }
            List<clsDetalle> attachedClsDetalleListNew = new ArrayList<clsDetalle>();
            for (clsDetalle clsDetalleListNewclsDetalleToAttach : clsDetalleListNew) {
                clsDetalleListNewclsDetalleToAttach = em.getReference(clsDetalleListNewclsDetalleToAttach.getClass(), clsDetalleListNewclsDetalleToAttach.getCodDetalleReactivo());
                attachedClsDetalleListNew.add(clsDetalleListNewclsDetalleToAttach);
            }
            clsDetalleListNew = attachedClsDetalleListNew;
            clsReactivo.setClsDetalleList(clsDetalleListNew);
            clsReactivo = em.merge(clsReactivo);
            if (codAsignacionOld != null && !codAsignacionOld.equals(codAsignacionNew)) {
                codAsignacionOld.getClsReactivoList().remove(clsReactivo);
                codAsignacionOld = em.merge(codAsignacionOld);
            }
            if (codAsignacionNew != null && !codAsignacionNew.equals(codAsignacionOld)) {
                codAsignacionNew.getClsReactivoList().add(clsReactivo);
                codAsignacionNew = em.merge(codAsignacionNew);
            }
            for (clsDetalle clsDetalleListOldclsDetalle : clsDetalleListOld) {
                if (!clsDetalleListNew.contains(clsDetalleListOldclsDetalle)) {
                    clsDetalleListOldclsDetalle.setCodReactivo(null);
                    clsDetalleListOldclsDetalle = em.merge(clsDetalleListOldclsDetalle);
                }
            }
            for (clsDetalle clsDetalleListNewclsDetalle : clsDetalleListNew) {
                if (!clsDetalleListOld.contains(clsDetalleListNewclsDetalle)) {
                    clsReactivo oldCodReactivoOfClsDetalleListNewclsDetalle = clsDetalleListNewclsDetalle.getCodReactivo();
                    clsDetalleListNewclsDetalle.setCodReactivo(clsReactivo);
                    clsDetalleListNewclsDetalle = em.merge(clsDetalleListNewclsDetalle);
                    if (oldCodReactivoOfClsDetalleListNewclsDetalle != null && !oldCodReactivoOfClsDetalleListNewclsDetalle.equals(clsReactivo)) {
                        oldCodReactivoOfClsDetalleListNewclsDetalle.getClsDetalleList().remove(clsDetalleListNewclsDetalle);
                        oldCodReactivoOfClsDetalleListNewclsDetalle = em.merge(oldCodReactivoOfClsDetalleListNewclsDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clsReactivo.getCodReactivo();
                if (findclsReactivo(id) == null) {
                    throw new NonexistentEntityException("The clsReactivo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsReactivo clsReactivo;
            try {
                clsReactivo = em.getReference(clsReactivo.class, id);
                clsReactivo.getCodReactivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clsReactivo with id " + id + " no longer exists.", enfe);
            }
            clsAsignacion codAsignacion = clsReactivo.getCodAsignacion();
            if (codAsignacion != null) {
                codAsignacion.getClsReactivoList().remove(clsReactivo);
                codAsignacion = em.merge(codAsignacion);
            }
            List<clsDetalle> clsDetalleList = clsReactivo.getClsDetalleList();
            for (clsDetalle clsDetalleListclsDetalle : clsDetalleList) {
                clsDetalleListclsDetalle.setCodReactivo(null);
                clsDetalleListclsDetalle = em.merge(clsDetalleListclsDetalle);
            }
            em.remove(clsReactivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<clsReactivo> findclsReactivoEntities() {
        return findclsReactivoEntities(true, -1, -1);
    }

    public List<clsReactivo> findclsReactivoEntities(int maxResults, int firstResult) {
        return findclsReactivoEntities(false, maxResults, firstResult);
    }

    private List<clsReactivo> findclsReactivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clsReactivo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public clsReactivo findclsReactivo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clsReactivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getclsReactivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<clsReactivo> rt = cq.from(clsReactivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
