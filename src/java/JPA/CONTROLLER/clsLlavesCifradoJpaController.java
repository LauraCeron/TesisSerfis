/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA.CONTROLLER;

import JPA.CONTROLLER.exceptions.NonexistentEntityException;
import JPA.CONTROLLER.exceptions.PreexistingEntityException;
import MODEL.clsLlavesCifrado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import MODEL.clsUsuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lau Cerón
 */
public class clsLlavesCifradoJpaController implements Serializable {

    public clsLlavesCifradoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(clsLlavesCifrado clsLlavesCifrado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsUsuario codUsuario = clsLlavesCifrado.getCodUsuario();
            if (codUsuario != null) {
                codUsuario = em.getReference(codUsuario.getClass(), codUsuario.getCodUsuario());
                clsLlavesCifrado.setCodUsuario(codUsuario);
            }
            em.persist(clsLlavesCifrado);
            if (codUsuario != null) {
                codUsuario.getClsLlavesCifradoList().add(clsLlavesCifrado);
                codUsuario = em.merge(codUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findclsLlavesCifrado(clsLlavesCifrado.getCodLlavesCifrado()) != null) {
                throw new PreexistingEntityException("clsLlavesCifrado " + clsLlavesCifrado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(clsLlavesCifrado clsLlavesCifrado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsLlavesCifrado persistentclsLlavesCifrado = em.find(clsLlavesCifrado.class, clsLlavesCifrado.getCodLlavesCifrado());
            clsUsuario codUsuarioOld = persistentclsLlavesCifrado.getCodUsuario();
            clsUsuario codUsuarioNew = clsLlavesCifrado.getCodUsuario();
            if (codUsuarioNew != null) {
                codUsuarioNew = em.getReference(codUsuarioNew.getClass(), codUsuarioNew.getCodUsuario());
                clsLlavesCifrado.setCodUsuario(codUsuarioNew);
            }
            clsLlavesCifrado = em.merge(clsLlavesCifrado);
            if (codUsuarioOld != null && !codUsuarioOld.equals(codUsuarioNew)) {
                codUsuarioOld.getClsLlavesCifradoList().remove(clsLlavesCifrado);
                codUsuarioOld = em.merge(codUsuarioOld);
            }
            if (codUsuarioNew != null && !codUsuarioNew.equals(codUsuarioOld)) {
                codUsuarioNew.getClsLlavesCifradoList().add(clsLlavesCifrado);
                codUsuarioNew = em.merge(codUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clsLlavesCifrado.getCodLlavesCifrado();
                if (findclsLlavesCifrado(id) == null) {
                    throw new NonexistentEntityException("The clsLlavesCifrado with id " + id + " no longer exists.");
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
            clsLlavesCifrado clsLlavesCifrado;
            try {
                clsLlavesCifrado = em.getReference(clsLlavesCifrado.class, id);
                clsLlavesCifrado.getCodLlavesCifrado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clsLlavesCifrado with id " + id + " no longer exists.", enfe);
            }
            clsUsuario codUsuario = clsLlavesCifrado.getCodUsuario();
            if (codUsuario != null) {
                codUsuario.getClsLlavesCifradoList().remove(clsLlavesCifrado);
                codUsuario = em.merge(codUsuario);
            }
            em.remove(clsLlavesCifrado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<clsLlavesCifrado> findclsLlavesCifradoEntities() {
        return findclsLlavesCifradoEntities(true, -1, -1);
    }

    public List<clsLlavesCifrado> findclsLlavesCifradoEntities(int maxResults, int firstResult) {
        return findclsLlavesCifradoEntities(false, maxResults, firstResult);
    }

    private List<clsLlavesCifrado> findclsLlavesCifradoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clsLlavesCifrado.class));
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

    public clsLlavesCifrado findclsLlavesCifrado(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clsLlavesCifrado.class, id);
        } finally {
            em.close();
        }
    }

    public int getclsLlavesCifradoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<clsLlavesCifrado> rt = cq.from(clsLlavesCifrado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
