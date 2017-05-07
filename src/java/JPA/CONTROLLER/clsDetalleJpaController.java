/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA.CONTROLLER;

import JPA.CONTROLLER.exceptions.NonexistentEntityException;
import JPA.CONTROLLER.exceptions.PreexistingEntityException;
import MODEL.clsDetalle;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import MODEL.clsReactivo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lau Cerón
 */
public class clsDetalleJpaController implements Serializable {

    public clsDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(clsDetalle clsDetalle) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsReactivo codReactivo = clsDetalle.getCodReactivo();
            if (codReactivo != null) {
                codReactivo = em.getReference(codReactivo.getClass(), codReactivo.getCodReactivo());
                clsDetalle.setCodReactivo(codReactivo);
            }
            em.persist(clsDetalle);
            if (codReactivo != null) {
                codReactivo.getClsDetalleList().add(clsDetalle);
                codReactivo = em.merge(codReactivo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findclsDetalle(clsDetalle.getCodDetalleReactivo()) != null) {
                throw new PreexistingEntityException("clsDetalle " + clsDetalle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(clsDetalle clsDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsDetalle persistentclsDetalle = em.find(clsDetalle.class, clsDetalle.getCodDetalleReactivo());
            clsReactivo codReactivoOld = persistentclsDetalle.getCodReactivo();
            clsReactivo codReactivoNew = clsDetalle.getCodReactivo();
            if (codReactivoNew != null) {
                codReactivoNew = em.getReference(codReactivoNew.getClass(), codReactivoNew.getCodReactivo());
                clsDetalle.setCodReactivo(codReactivoNew);
            }
            clsDetalle = em.merge(clsDetalle);
            if (codReactivoOld != null && !codReactivoOld.equals(codReactivoNew)) {
                codReactivoOld.getClsDetalleList().remove(clsDetalle);
                codReactivoOld = em.merge(codReactivoOld);
            }
            if (codReactivoNew != null && !codReactivoNew.equals(codReactivoOld)) {
                codReactivoNew.getClsDetalleList().add(clsDetalle);
                codReactivoNew = em.merge(codReactivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clsDetalle.getCodDetalleReactivo();
                if (findclsDetalle(id) == null) {
                    throw new NonexistentEntityException("The clsDetalle with id " + id + " no longer exists.");
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
            clsDetalle clsDetalle;
            try {
                clsDetalle = em.getReference(clsDetalle.class, id);
                clsDetalle.getCodDetalleReactivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clsDetalle with id " + id + " no longer exists.", enfe);
            }
            clsReactivo codReactivo = clsDetalle.getCodReactivo();
            if (codReactivo != null) {
                codReactivo.getClsDetalleList().remove(clsDetalle);
                codReactivo = em.merge(codReactivo);
            }
            em.remove(clsDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<clsDetalle> findclsDetalleEntities() {
        return findclsDetalleEntities(true, -1, -1);
    }

    public List<clsDetalle> findclsDetalleEntities(int maxResults, int firstResult) {
        return findclsDetalleEntities(false, maxResults, firstResult);
    }

    private List<clsDetalle> findclsDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clsDetalle.class));
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

    public clsDetalle findclsDetalle(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clsDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getclsDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<clsDetalle> rt = cq.from(clsDetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
