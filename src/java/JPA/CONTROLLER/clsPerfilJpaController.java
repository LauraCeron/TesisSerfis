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
import java.util.ArrayList;
import java.util.List;
import MODEL.clsAccion;
import MODEL.clsPerfil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lau Cerón
 */
public class clsPerfilJpaController implements Serializable {

    public clsPerfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(clsPerfil clsPerfil) throws PreexistingEntityException, Exception {
        if (clsPerfil.getClsUsuarioList() == null) {
            clsPerfil.setClsUsuarioList(new ArrayList<clsUsuario>());
        }
        if (clsPerfil.getClsAccionList() == null) {
            clsPerfil.setClsAccionList(new ArrayList<clsAccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<clsUsuario> attachedClsUsuarioList = new ArrayList<clsUsuario>();
            for (clsUsuario clsUsuarioListclsUsuarioToAttach : clsPerfil.getClsUsuarioList()) {
                clsUsuarioListclsUsuarioToAttach = em.getReference(clsUsuarioListclsUsuarioToAttach.getClass(), clsUsuarioListclsUsuarioToAttach.getCodUsuario());
                attachedClsUsuarioList.add(clsUsuarioListclsUsuarioToAttach);
            }
            clsPerfil.setClsUsuarioList(attachedClsUsuarioList);
            List<clsAccion> attachedClsAccionList = new ArrayList<clsAccion>();
            for (clsAccion clsAccionListclsAccionToAttach : clsPerfil.getClsAccionList()) {
                clsAccionListclsAccionToAttach = em.getReference(clsAccionListclsAccionToAttach.getClass(), clsAccionListclsAccionToAttach.getCodAccion());
                attachedClsAccionList.add(clsAccionListclsAccionToAttach);
            }
            clsPerfil.setClsAccionList(attachedClsAccionList);
            em.persist(clsPerfil);
            for (clsUsuario clsUsuarioListclsUsuario : clsPerfil.getClsUsuarioList()) {
                clsUsuarioListclsUsuario.getClsPerfilList().add(clsPerfil);
                clsUsuarioListclsUsuario = em.merge(clsUsuarioListclsUsuario);
            }
            for (clsAccion clsAccionListclsAccion : clsPerfil.getClsAccionList()) {
                clsAccionListclsAccion.getClsPerfilList().add(clsPerfil);
                clsAccionListclsAccion = em.merge(clsAccionListclsAccion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findclsPerfil(clsPerfil.getCodPerfil()) != null) {
                throw new PreexistingEntityException("clsPerfil " + clsPerfil + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(clsPerfil clsPerfil) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsPerfil persistentclsPerfil = em.find(clsPerfil.class, clsPerfil.getCodPerfil());
            List<clsUsuario> clsUsuarioListOld = persistentclsPerfil.getClsUsuarioList();
            List<clsUsuario> clsUsuarioListNew = clsPerfil.getClsUsuarioList();
            List<clsAccion> clsAccionListOld = persistentclsPerfil.getClsAccionList();
            List<clsAccion> clsAccionListNew = clsPerfil.getClsAccionList();
            List<clsUsuario> attachedClsUsuarioListNew = new ArrayList<clsUsuario>();
            for (clsUsuario clsUsuarioListNewclsUsuarioToAttach : clsUsuarioListNew) {
                clsUsuarioListNewclsUsuarioToAttach = em.getReference(clsUsuarioListNewclsUsuarioToAttach.getClass(), clsUsuarioListNewclsUsuarioToAttach.getCodUsuario());
                attachedClsUsuarioListNew.add(clsUsuarioListNewclsUsuarioToAttach);
            }
            clsUsuarioListNew = attachedClsUsuarioListNew;
            clsPerfil.setClsUsuarioList(clsUsuarioListNew);
            List<clsAccion> attachedClsAccionListNew = new ArrayList<clsAccion>();
            for (clsAccion clsAccionListNewclsAccionToAttach : clsAccionListNew) {
                clsAccionListNewclsAccionToAttach = em.getReference(clsAccionListNewclsAccionToAttach.getClass(), clsAccionListNewclsAccionToAttach.getCodAccion());
                attachedClsAccionListNew.add(clsAccionListNewclsAccionToAttach);
            }
            clsAccionListNew = attachedClsAccionListNew;
            clsPerfil.setClsAccionList(clsAccionListNew);
            clsPerfil = em.merge(clsPerfil);
            for (clsUsuario clsUsuarioListOldclsUsuario : clsUsuarioListOld) {
                if (!clsUsuarioListNew.contains(clsUsuarioListOldclsUsuario)) {
                    clsUsuarioListOldclsUsuario.getClsPerfilList().remove(clsPerfil);
                    clsUsuarioListOldclsUsuario = em.merge(clsUsuarioListOldclsUsuario);
                }
            }
            for (clsUsuario clsUsuarioListNewclsUsuario : clsUsuarioListNew) {
                if (!clsUsuarioListOld.contains(clsUsuarioListNewclsUsuario)) {
                    clsUsuarioListNewclsUsuario.getClsPerfilList().add(clsPerfil);
                    clsUsuarioListNewclsUsuario = em.merge(clsUsuarioListNewclsUsuario);
                }
            }
            for (clsAccion clsAccionListOldclsAccion : clsAccionListOld) {
                if (!clsAccionListNew.contains(clsAccionListOldclsAccion)) {
                    clsAccionListOldclsAccion.getClsPerfilList().remove(clsPerfil);
                    clsAccionListOldclsAccion = em.merge(clsAccionListOldclsAccion);
                }
            }
            for (clsAccion clsAccionListNewclsAccion : clsAccionListNew) {
                if (!clsAccionListOld.contains(clsAccionListNewclsAccion)) {
                    clsAccionListNewclsAccion.getClsPerfilList().add(clsPerfil);
                    clsAccionListNewclsAccion = em.merge(clsAccionListNewclsAccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clsPerfil.getCodPerfil();
                if (findclsPerfil(id) == null) {
                    throw new NonexistentEntityException("The clsPerfil with id " + id + " no longer exists.");
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
            clsPerfil clsPerfil;
            try {
                clsPerfil = em.getReference(clsPerfil.class, id);
                clsPerfil.getCodPerfil();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clsPerfil with id " + id + " no longer exists.", enfe);
            }
            List<clsUsuario> clsUsuarioList = clsPerfil.getClsUsuarioList();
            for (clsUsuario clsUsuarioListclsUsuario : clsUsuarioList) {
                clsUsuarioListclsUsuario.getClsPerfilList().remove(clsPerfil);
                clsUsuarioListclsUsuario = em.merge(clsUsuarioListclsUsuario);
            }
            List<clsAccion> clsAccionList = clsPerfil.getClsAccionList();
            for (clsAccion clsAccionListclsAccion : clsAccionList) {
                clsAccionListclsAccion.getClsPerfilList().remove(clsPerfil);
                clsAccionListclsAccion = em.merge(clsAccionListclsAccion);
            }
            em.remove(clsPerfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<clsPerfil> findclsPerfilEntities() {
        return findclsPerfilEntities(true, -1, -1);
    }

    public List<clsPerfil> findclsPerfilEntities(int maxResults, int firstResult) {
        return findclsPerfilEntities(false, maxResults, firstResult);
    }

    private List<clsPerfil> findclsPerfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clsPerfil.class));
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

    public clsPerfil findclsPerfil(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clsPerfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getclsPerfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<clsPerfil> rt = cq.from(clsPerfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
