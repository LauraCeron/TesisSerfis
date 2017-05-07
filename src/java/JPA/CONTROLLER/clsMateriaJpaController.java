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
import MODEL.clsArea;
import MODEL.clsAsignacion;
import MODEL.clsMateria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lau Cerón
 */
public class clsMateriaJpaController implements Serializable {

    public clsMateriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(clsMateria clsMateria) throws PreexistingEntityException, Exception {
        if (clsMateria.getClsAsignacionList() == null) {
            clsMateria.setClsAsignacionList(new ArrayList<clsAsignacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsArea codArea = clsMateria.getCodArea();
            if (codArea != null) {
                codArea = em.getReference(codArea.getClass(), codArea.getCodArea());
                clsMateria.setCodArea(codArea);
            }
            List<clsAsignacion> attachedClsAsignacionList = new ArrayList<clsAsignacion>();
            for (clsAsignacion clsAsignacionListclsAsignacionToAttach : clsMateria.getClsAsignacionList()) {
                clsAsignacionListclsAsignacionToAttach = em.getReference(clsAsignacionListclsAsignacionToAttach.getClass(), clsAsignacionListclsAsignacionToAttach.getCodAsignacion());
                attachedClsAsignacionList.add(clsAsignacionListclsAsignacionToAttach);
            }
            clsMateria.setClsAsignacionList(attachedClsAsignacionList);
            em.persist(clsMateria);
            if (codArea != null) {
                codArea.getClsMateriaList().add(clsMateria);
                codArea = em.merge(codArea);
            }
            for (clsAsignacion clsAsignacionListclsAsignacion : clsMateria.getClsAsignacionList()) {
                clsMateria oldCodMateriaOfClsAsignacionListclsAsignacion = clsAsignacionListclsAsignacion.getCodMateria();
                clsAsignacionListclsAsignacion.setCodMateria(clsMateria);
                clsAsignacionListclsAsignacion = em.merge(clsAsignacionListclsAsignacion);
                if (oldCodMateriaOfClsAsignacionListclsAsignacion != null) {
                    oldCodMateriaOfClsAsignacionListclsAsignacion.getClsAsignacionList().remove(clsAsignacionListclsAsignacion);
                    oldCodMateriaOfClsAsignacionListclsAsignacion = em.merge(oldCodMateriaOfClsAsignacionListclsAsignacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findclsMateria(clsMateria.getCodMateria()) != null) {
                throw new PreexistingEntityException("clsMateria " + clsMateria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(clsMateria clsMateria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsMateria persistentclsMateria = em.find(clsMateria.class, clsMateria.getCodMateria());
            clsArea codAreaOld = persistentclsMateria.getCodArea();
            clsArea codAreaNew = clsMateria.getCodArea();
            List<clsAsignacion> clsAsignacionListOld = persistentclsMateria.getClsAsignacionList();
            List<clsAsignacion> clsAsignacionListNew = clsMateria.getClsAsignacionList();
            if (codAreaNew != null) {
                codAreaNew = em.getReference(codAreaNew.getClass(), codAreaNew.getCodArea());
                clsMateria.setCodArea(codAreaNew);
            }
            List<clsAsignacion> attachedClsAsignacionListNew = new ArrayList<clsAsignacion>();
            for (clsAsignacion clsAsignacionListNewclsAsignacionToAttach : clsAsignacionListNew) {
                clsAsignacionListNewclsAsignacionToAttach = em.getReference(clsAsignacionListNewclsAsignacionToAttach.getClass(), clsAsignacionListNewclsAsignacionToAttach.getCodAsignacion());
                attachedClsAsignacionListNew.add(clsAsignacionListNewclsAsignacionToAttach);
            }
            clsAsignacionListNew = attachedClsAsignacionListNew;
            clsMateria.setClsAsignacionList(clsAsignacionListNew);
            clsMateria = em.merge(clsMateria);
            if (codAreaOld != null && !codAreaOld.equals(codAreaNew)) {
                codAreaOld.getClsMateriaList().remove(clsMateria);
                codAreaOld = em.merge(codAreaOld);
            }
            if (codAreaNew != null && !codAreaNew.equals(codAreaOld)) {
                codAreaNew.getClsMateriaList().add(clsMateria);
                codAreaNew = em.merge(codAreaNew);
            }
            for (clsAsignacion clsAsignacionListOldclsAsignacion : clsAsignacionListOld) {
                if (!clsAsignacionListNew.contains(clsAsignacionListOldclsAsignacion)) {
                    clsAsignacionListOldclsAsignacion.setCodMateria(null);
                    clsAsignacionListOldclsAsignacion = em.merge(clsAsignacionListOldclsAsignacion);
                }
            }
            for (clsAsignacion clsAsignacionListNewclsAsignacion : clsAsignacionListNew) {
                if (!clsAsignacionListOld.contains(clsAsignacionListNewclsAsignacion)) {
                    clsMateria oldCodMateriaOfClsAsignacionListNewclsAsignacion = clsAsignacionListNewclsAsignacion.getCodMateria();
                    clsAsignacionListNewclsAsignacion.setCodMateria(clsMateria);
                    clsAsignacionListNewclsAsignacion = em.merge(clsAsignacionListNewclsAsignacion);
                    if (oldCodMateriaOfClsAsignacionListNewclsAsignacion != null && !oldCodMateriaOfClsAsignacionListNewclsAsignacion.equals(clsMateria)) {
                        oldCodMateriaOfClsAsignacionListNewclsAsignacion.getClsAsignacionList().remove(clsAsignacionListNewclsAsignacion);
                        oldCodMateriaOfClsAsignacionListNewclsAsignacion = em.merge(oldCodMateriaOfClsAsignacionListNewclsAsignacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clsMateria.getCodMateria();
                if (findclsMateria(id) == null) {
                    throw new NonexistentEntityException("The clsMateria with id " + id + " no longer exists.");
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
            clsMateria clsMateria;
            try {
                clsMateria = em.getReference(clsMateria.class, id);
                clsMateria.getCodMateria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clsMateria with id " + id + " no longer exists.", enfe);
            }
            clsArea codArea = clsMateria.getCodArea();
            if (codArea != null) {
                codArea.getClsMateriaList().remove(clsMateria);
                codArea = em.merge(codArea);
            }
            List<clsAsignacion> clsAsignacionList = clsMateria.getClsAsignacionList();
            for (clsAsignacion clsAsignacionListclsAsignacion : clsAsignacionList) {
                clsAsignacionListclsAsignacion.setCodMateria(null);
                clsAsignacionListclsAsignacion = em.merge(clsAsignacionListclsAsignacion);
            }
            em.remove(clsMateria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<clsMateria> findclsMateriaEntities() {
        return findclsMateriaEntities(true, -1, -1);
    }

    public List<clsMateria> findclsMateriaEntities(int maxResults, int firstResult) {
        return findclsMateriaEntities(false, maxResults, firstResult);
    }

    private List<clsMateria> findclsMateriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clsMateria.class));
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

    public clsMateria findclsMateria(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clsMateria.class, id);
        } finally {
            em.close();
        }
    }

    public int getclsMateriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<clsMateria> rt = cq.from(clsMateria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
