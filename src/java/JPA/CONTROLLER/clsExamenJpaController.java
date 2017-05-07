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
import MODEL.clsUsuario;
import MODEL.clsAsignacion;
import MODEL.clsExamen;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lau Cerón
 */
public class clsExamenJpaController implements Serializable {

    public clsExamenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(clsExamen clsExamen) throws PreexistingEntityException, Exception {
        if (clsExamen.getClsAsignacionList() == null) {
            clsExamen.setClsAsignacionList(new ArrayList<clsAsignacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsUsuario codUsuario = clsExamen.getCodUsuario();
            if (codUsuario != null) {
                codUsuario = em.getReference(codUsuario.getClass(), codUsuario.getCodUsuario());
                clsExamen.setCodUsuario(codUsuario);
            }
            List<clsAsignacion> attachedClsAsignacionList = new ArrayList<clsAsignacion>();
            for (clsAsignacion clsAsignacionListclsAsignacionToAttach : clsExamen.getClsAsignacionList()) {
                clsAsignacionListclsAsignacionToAttach = em.getReference(clsAsignacionListclsAsignacionToAttach.getClass(), clsAsignacionListclsAsignacionToAttach.getCodAsignacion());
                attachedClsAsignacionList.add(clsAsignacionListclsAsignacionToAttach);
            }
            clsExamen.setClsAsignacionList(attachedClsAsignacionList);
            em.persist(clsExamen);
            if (codUsuario != null) {
                codUsuario.getClsExamenList().add(clsExamen);
                codUsuario = em.merge(codUsuario);
            }
            for (clsAsignacion clsAsignacionListclsAsignacion : clsExamen.getClsAsignacionList()) {
                clsExamen oldCodExamenOfClsAsignacionListclsAsignacion = clsAsignacionListclsAsignacion.getCodExamen();
                clsAsignacionListclsAsignacion.setCodExamen(clsExamen);
                clsAsignacionListclsAsignacion = em.merge(clsAsignacionListclsAsignacion);
                if (oldCodExamenOfClsAsignacionListclsAsignacion != null) {
                    oldCodExamenOfClsAsignacionListclsAsignacion.getClsAsignacionList().remove(clsAsignacionListclsAsignacion);
                    oldCodExamenOfClsAsignacionListclsAsignacion = em.merge(oldCodExamenOfClsAsignacionListclsAsignacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findclsExamen(clsExamen.getCodExamen()) != null) {
                throw new PreexistingEntityException("clsExamen " + clsExamen + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(clsExamen clsExamen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsExamen persistentclsExamen = em.find(clsExamen.class, clsExamen.getCodExamen());
            clsUsuario codUsuarioOld = persistentclsExamen.getCodUsuario();
            clsUsuario codUsuarioNew = clsExamen.getCodUsuario();
            List<clsAsignacion> clsAsignacionListOld = persistentclsExamen.getClsAsignacionList();
            List<clsAsignacion> clsAsignacionListNew = clsExamen.getClsAsignacionList();
            if (codUsuarioNew != null) {
                codUsuarioNew = em.getReference(codUsuarioNew.getClass(), codUsuarioNew.getCodUsuario());
                clsExamen.setCodUsuario(codUsuarioNew);
            }
            List<clsAsignacion> attachedClsAsignacionListNew = new ArrayList<clsAsignacion>();
            for (clsAsignacion clsAsignacionListNewclsAsignacionToAttach : clsAsignacionListNew) {
                clsAsignacionListNewclsAsignacionToAttach = em.getReference(clsAsignacionListNewclsAsignacionToAttach.getClass(), clsAsignacionListNewclsAsignacionToAttach.getCodAsignacion());
                attachedClsAsignacionListNew.add(clsAsignacionListNewclsAsignacionToAttach);
            }
            clsAsignacionListNew = attachedClsAsignacionListNew;
            clsExamen.setClsAsignacionList(clsAsignacionListNew);
            clsExamen = em.merge(clsExamen);
            if (codUsuarioOld != null && !codUsuarioOld.equals(codUsuarioNew)) {
                codUsuarioOld.getClsExamenList().remove(clsExamen);
                codUsuarioOld = em.merge(codUsuarioOld);
            }
            if (codUsuarioNew != null && !codUsuarioNew.equals(codUsuarioOld)) {
                codUsuarioNew.getClsExamenList().add(clsExamen);
                codUsuarioNew = em.merge(codUsuarioNew);
            }
            for (clsAsignacion clsAsignacionListOldclsAsignacion : clsAsignacionListOld) {
                if (!clsAsignacionListNew.contains(clsAsignacionListOldclsAsignacion)) {
                    clsAsignacionListOldclsAsignacion.setCodExamen(null);
                    clsAsignacionListOldclsAsignacion = em.merge(clsAsignacionListOldclsAsignacion);
                }
            }
            for (clsAsignacion clsAsignacionListNewclsAsignacion : clsAsignacionListNew) {
                if (!clsAsignacionListOld.contains(clsAsignacionListNewclsAsignacion)) {
                    clsExamen oldCodExamenOfClsAsignacionListNewclsAsignacion = clsAsignacionListNewclsAsignacion.getCodExamen();
                    clsAsignacionListNewclsAsignacion.setCodExamen(clsExamen);
                    clsAsignacionListNewclsAsignacion = em.merge(clsAsignacionListNewclsAsignacion);
                    if (oldCodExamenOfClsAsignacionListNewclsAsignacion != null && !oldCodExamenOfClsAsignacionListNewclsAsignacion.equals(clsExamen)) {
                        oldCodExamenOfClsAsignacionListNewclsAsignacion.getClsAsignacionList().remove(clsAsignacionListNewclsAsignacion);
                        oldCodExamenOfClsAsignacionListNewclsAsignacion = em.merge(oldCodExamenOfClsAsignacionListNewclsAsignacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clsExamen.getCodExamen();
                if (findclsExamen(id) == null) {
                    throw new NonexistentEntityException("The clsExamen with id " + id + " no longer exists.");
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
            clsExamen clsExamen;
            try {
                clsExamen = em.getReference(clsExamen.class, id);
                clsExamen.getCodExamen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clsExamen with id " + id + " no longer exists.", enfe);
            }
            clsUsuario codUsuario = clsExamen.getCodUsuario();
            if (codUsuario != null) {
                codUsuario.getClsExamenList().remove(clsExamen);
                codUsuario = em.merge(codUsuario);
            }
            List<clsAsignacion> clsAsignacionList = clsExamen.getClsAsignacionList();
            for (clsAsignacion clsAsignacionListclsAsignacion : clsAsignacionList) {
                clsAsignacionListclsAsignacion.setCodExamen(null);
                clsAsignacionListclsAsignacion = em.merge(clsAsignacionListclsAsignacion);
            }
            em.remove(clsExamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<clsExamen> findclsExamenEntities() {
        return findclsExamenEntities(true, -1, -1);
    }

    public List<clsExamen> findclsExamenEntities(int maxResults, int firstResult) {
        return findclsExamenEntities(false, maxResults, firstResult);
    }

    private List<clsExamen> findclsExamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clsExamen.class));
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

    public clsExamen findclsExamen(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clsExamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getclsExamenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<clsExamen> rt = cq.from(clsExamen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
