/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA.CONTROLLER;

import JPA.CONTROLLER.exceptions.NonexistentEntityException;
import JPA.CONTROLLER.exceptions.PreexistingEntityException;
import MODEL.clsArea;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import MODEL.clsMateria;
import java.util.ArrayList;
import java.util.List;
import MODEL.clsUsuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lau Cerón
 */
public class clsAreaJpaController implements Serializable {

    public clsAreaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(clsArea clsArea) throws PreexistingEntityException, Exception {
        if (clsArea.getClsMateriaList() == null) {
            clsArea.setClsMateriaList(new ArrayList<clsMateria>());
        }
        if (clsArea.getClsUsuarioList() == null) {
            clsArea.setClsUsuarioList(new ArrayList<clsUsuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<clsMateria> attachedClsMateriaList = new ArrayList<clsMateria>();
            for (clsMateria clsMateriaListclsMateriaToAttach : clsArea.getClsMateriaList()) {
                clsMateriaListclsMateriaToAttach = em.getReference(clsMateriaListclsMateriaToAttach.getClass(), clsMateriaListclsMateriaToAttach.getCodMateria());
                attachedClsMateriaList.add(clsMateriaListclsMateriaToAttach);
            }
            clsArea.setClsMateriaList(attachedClsMateriaList);
            List<clsUsuario> attachedClsUsuarioList = new ArrayList<clsUsuario>();
            for (clsUsuario clsUsuarioListclsUsuarioToAttach : clsArea.getClsUsuarioList()) {
                clsUsuarioListclsUsuarioToAttach = em.getReference(clsUsuarioListclsUsuarioToAttach.getClass(), clsUsuarioListclsUsuarioToAttach.getCodUsuario());
                attachedClsUsuarioList.add(clsUsuarioListclsUsuarioToAttach);
            }
            clsArea.setClsUsuarioList(attachedClsUsuarioList);
            em.persist(clsArea);
            for (clsMateria clsMateriaListclsMateria : clsArea.getClsMateriaList()) {
                clsArea oldCodAreaOfClsMateriaListclsMateria = clsMateriaListclsMateria.getCodArea();
                clsMateriaListclsMateria.setCodArea(clsArea);
                clsMateriaListclsMateria = em.merge(clsMateriaListclsMateria);
                if (oldCodAreaOfClsMateriaListclsMateria != null) {
                    oldCodAreaOfClsMateriaListclsMateria.getClsMateriaList().remove(clsMateriaListclsMateria);
                    oldCodAreaOfClsMateriaListclsMateria = em.merge(oldCodAreaOfClsMateriaListclsMateria);
                }
            }
            for (clsUsuario clsUsuarioListclsUsuario : clsArea.getClsUsuarioList()) {
                clsArea oldCodAreaOfClsUsuarioListclsUsuario = clsUsuarioListclsUsuario.getCodArea();
                clsUsuarioListclsUsuario.setCodArea(clsArea);
                clsUsuarioListclsUsuario = em.merge(clsUsuarioListclsUsuario);
                if (oldCodAreaOfClsUsuarioListclsUsuario != null) {
                    oldCodAreaOfClsUsuarioListclsUsuario.getClsUsuarioList().remove(clsUsuarioListclsUsuario);
                    oldCodAreaOfClsUsuarioListclsUsuario = em.merge(oldCodAreaOfClsUsuarioListclsUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findclsArea(clsArea.getCodArea()) != null) {
                throw new PreexistingEntityException("clsArea " + clsArea + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(clsArea clsArea) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsArea persistentclsArea = em.find(clsArea.class, clsArea.getCodArea());
            List<clsMateria> clsMateriaListOld = persistentclsArea.getClsMateriaList();
            List<clsMateria> clsMateriaListNew = clsArea.getClsMateriaList();
            List<clsUsuario> clsUsuarioListOld = persistentclsArea.getClsUsuarioList();
            List<clsUsuario> clsUsuarioListNew = clsArea.getClsUsuarioList();
            List<clsMateria> attachedClsMateriaListNew = new ArrayList<clsMateria>();
            for (clsMateria clsMateriaListNewclsMateriaToAttach : clsMateriaListNew) {
                clsMateriaListNewclsMateriaToAttach = em.getReference(clsMateriaListNewclsMateriaToAttach.getClass(), clsMateriaListNewclsMateriaToAttach.getCodMateria());
                attachedClsMateriaListNew.add(clsMateriaListNewclsMateriaToAttach);
            }
            clsMateriaListNew = attachedClsMateriaListNew;
            clsArea.setClsMateriaList(clsMateriaListNew);
            List<clsUsuario> attachedClsUsuarioListNew = new ArrayList<clsUsuario>();
            for (clsUsuario clsUsuarioListNewclsUsuarioToAttach : clsUsuarioListNew) {
                clsUsuarioListNewclsUsuarioToAttach = em.getReference(clsUsuarioListNewclsUsuarioToAttach.getClass(), clsUsuarioListNewclsUsuarioToAttach.getCodUsuario());
                attachedClsUsuarioListNew.add(clsUsuarioListNewclsUsuarioToAttach);
            }
            clsUsuarioListNew = attachedClsUsuarioListNew;
            clsArea.setClsUsuarioList(clsUsuarioListNew);
            clsArea = em.merge(clsArea);
            for (clsMateria clsMateriaListOldclsMateria : clsMateriaListOld) {
                if (!clsMateriaListNew.contains(clsMateriaListOldclsMateria)) {
                    clsMateriaListOldclsMateria.setCodArea(null);
                    clsMateriaListOldclsMateria = em.merge(clsMateriaListOldclsMateria);
                }
            }
            for (clsMateria clsMateriaListNewclsMateria : clsMateriaListNew) {
                if (!clsMateriaListOld.contains(clsMateriaListNewclsMateria)) {
                    clsArea oldCodAreaOfClsMateriaListNewclsMateria = clsMateriaListNewclsMateria.getCodArea();
                    clsMateriaListNewclsMateria.setCodArea(clsArea);
                    clsMateriaListNewclsMateria = em.merge(clsMateriaListNewclsMateria);
                    if (oldCodAreaOfClsMateriaListNewclsMateria != null && !oldCodAreaOfClsMateriaListNewclsMateria.equals(clsArea)) {
                        oldCodAreaOfClsMateriaListNewclsMateria.getClsMateriaList().remove(clsMateriaListNewclsMateria);
                        oldCodAreaOfClsMateriaListNewclsMateria = em.merge(oldCodAreaOfClsMateriaListNewclsMateria);
                    }
                }
            }
            for (clsUsuario clsUsuarioListOldclsUsuario : clsUsuarioListOld) {
                if (!clsUsuarioListNew.contains(clsUsuarioListOldclsUsuario)) {
                    clsUsuarioListOldclsUsuario.setCodArea(null);
                    clsUsuarioListOldclsUsuario = em.merge(clsUsuarioListOldclsUsuario);
                }
            }
            for (clsUsuario clsUsuarioListNewclsUsuario : clsUsuarioListNew) {
                if (!clsUsuarioListOld.contains(clsUsuarioListNewclsUsuario)) {
                    clsArea oldCodAreaOfClsUsuarioListNewclsUsuario = clsUsuarioListNewclsUsuario.getCodArea();
                    clsUsuarioListNewclsUsuario.setCodArea(clsArea);
                    clsUsuarioListNewclsUsuario = em.merge(clsUsuarioListNewclsUsuario);
                    if (oldCodAreaOfClsUsuarioListNewclsUsuario != null && !oldCodAreaOfClsUsuarioListNewclsUsuario.equals(clsArea)) {
                        oldCodAreaOfClsUsuarioListNewclsUsuario.getClsUsuarioList().remove(clsUsuarioListNewclsUsuario);
                        oldCodAreaOfClsUsuarioListNewclsUsuario = em.merge(oldCodAreaOfClsUsuarioListNewclsUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clsArea.getCodArea();
                if (findclsArea(id) == null) {
                    throw new NonexistentEntityException("The clsArea with id " + id + " no longer exists.");
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
            clsArea clsArea;
            try {
                clsArea = em.getReference(clsArea.class, id);
                clsArea.getCodArea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clsArea with id " + id + " no longer exists.", enfe);
            }
            List<clsMateria> clsMateriaList = clsArea.getClsMateriaList();
            for (clsMateria clsMateriaListclsMateria : clsMateriaList) {
                clsMateriaListclsMateria.setCodArea(null);
                clsMateriaListclsMateria = em.merge(clsMateriaListclsMateria);
            }
            List<clsUsuario> clsUsuarioList = clsArea.getClsUsuarioList();
            for (clsUsuario clsUsuarioListclsUsuario : clsUsuarioList) {
                clsUsuarioListclsUsuario.setCodArea(null);
                clsUsuarioListclsUsuario = em.merge(clsUsuarioListclsUsuario);
            }
            em.remove(clsArea);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<clsArea> findclsAreaEntities() {
        return findclsAreaEntities(true, -1, -1);
    }

    public List<clsArea> findclsAreaEntities(int maxResults, int firstResult) {
        return findclsAreaEntities(false, maxResults, firstResult);
    }

    private List<clsArea> findclsAreaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clsArea.class));
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

    public clsArea findclsArea(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clsArea.class, id);
        } finally {
            em.close();
        }
    }

    public int getclsAreaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<clsArea> rt = cq.from(clsArea.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
