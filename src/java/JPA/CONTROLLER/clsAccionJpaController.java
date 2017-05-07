/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA.CONTROLLER;

import JPA.CONTROLLER.exceptions.NonexistentEntityException;
import JPA.CONTROLLER.exceptions.PreexistingEntityException;
import MODEL.clsAccion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import MODEL.clsPerfil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lau Cerón
 */
public class clsAccionJpaController implements Serializable {

    public clsAccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(clsAccion clsAccion) throws PreexistingEntityException, Exception {
        if (clsAccion.getClsPerfilList() == null) {
            clsAccion.setClsPerfilList(new ArrayList<clsPerfil>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<clsPerfil> attachedClsPerfilList = new ArrayList<clsPerfil>();
            for (clsPerfil clsPerfilListclsPerfilToAttach : clsAccion.getClsPerfilList()) {
                clsPerfilListclsPerfilToAttach = em.getReference(clsPerfilListclsPerfilToAttach.getClass(), clsPerfilListclsPerfilToAttach.getCodPerfil());
                attachedClsPerfilList.add(clsPerfilListclsPerfilToAttach);
            }
            clsAccion.setClsPerfilList(attachedClsPerfilList);
            em.persist(clsAccion);
            for (clsPerfil clsPerfilListclsPerfil : clsAccion.getClsPerfilList()) {
                clsPerfilListclsPerfil.getClsAccionList().add(clsAccion);
                clsPerfilListclsPerfil = em.merge(clsPerfilListclsPerfil);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findclsAccion(clsAccion.getCodAccion()) != null) {
                throw new PreexistingEntityException("clsAccion " + clsAccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(clsAccion clsAccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsAccion persistentclsAccion = em.find(clsAccion.class, clsAccion.getCodAccion());
            List<clsPerfil> clsPerfilListOld = persistentclsAccion.getClsPerfilList();
            List<clsPerfil> clsPerfilListNew = clsAccion.getClsPerfilList();
            List<clsPerfil> attachedClsPerfilListNew = new ArrayList<clsPerfil>();
            for (clsPerfil clsPerfilListNewclsPerfilToAttach : clsPerfilListNew) {
                clsPerfilListNewclsPerfilToAttach = em.getReference(clsPerfilListNewclsPerfilToAttach.getClass(), clsPerfilListNewclsPerfilToAttach.getCodPerfil());
                attachedClsPerfilListNew.add(clsPerfilListNewclsPerfilToAttach);
            }
            clsPerfilListNew = attachedClsPerfilListNew;
            clsAccion.setClsPerfilList(clsPerfilListNew);
            clsAccion = em.merge(clsAccion);
            for (clsPerfil clsPerfilListOldclsPerfil : clsPerfilListOld) {
                if (!clsPerfilListNew.contains(clsPerfilListOldclsPerfil)) {
                    clsPerfilListOldclsPerfil.getClsAccionList().remove(clsAccion);
                    clsPerfilListOldclsPerfil = em.merge(clsPerfilListOldclsPerfil);
                }
            }
            for (clsPerfil clsPerfilListNewclsPerfil : clsPerfilListNew) {
                if (!clsPerfilListOld.contains(clsPerfilListNewclsPerfil)) {
                    clsPerfilListNewclsPerfil.getClsAccionList().add(clsAccion);
                    clsPerfilListNewclsPerfil = em.merge(clsPerfilListNewclsPerfil);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clsAccion.getCodAccion();
                if (findclsAccion(id) == null) {
                    throw new NonexistentEntityException("The clsAccion with id " + id + " no longer exists.");
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
            clsAccion clsAccion;
            try {
                clsAccion = em.getReference(clsAccion.class, id);
                clsAccion.getCodAccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clsAccion with id " + id + " no longer exists.", enfe);
            }
            List<clsPerfil> clsPerfilList = clsAccion.getClsPerfilList();
            for (clsPerfil clsPerfilListclsPerfil : clsPerfilList) {
                clsPerfilListclsPerfil.getClsAccionList().remove(clsAccion);
                clsPerfilListclsPerfil = em.merge(clsPerfilListclsPerfil);
            }
            em.remove(clsAccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<clsAccion> findclsAccionEntities() {
        return findclsAccionEntities(true, -1, -1);
    }

    public List<clsAccion> findclsAccionEntities(int maxResults, int firstResult) {
        return findclsAccionEntities(false, maxResults, firstResult);
    }

    private List<clsAccion> findclsAccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clsAccion.class));
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

    public clsAccion findclsAccion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clsAccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getclsAccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<clsAccion> rt = cq.from(clsAccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
