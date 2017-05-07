/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA.CONTROLLER;

import JPA.CONTROLLER.exceptions.NonexistentEntityException;
import JPA.CONTROLLER.exceptions.PreexistingEntityException;
import MODEL.clsAsignacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import MODEL.clsExamen;
import MODEL.clsMateria;
import MODEL.clsUsuario;
import MODEL.clsReactivo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lau Cerón
 */
public class clsAsignacionJpaController implements Serializable {

    public clsAsignacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(clsAsignacion clsAsignacion) throws PreexistingEntityException, Exception {
        if (clsAsignacion.getClsReactivoList() == null) {
            clsAsignacion.setClsReactivoList(new ArrayList<clsReactivo>());
        }
        if (clsAsignacion.getClsUsuarioList() == null) {
            clsAsignacion.setClsUsuarioList(new ArrayList<clsUsuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsExamen codExamen = clsAsignacion.getCodExamen();
            if (codExamen != null) {
                codExamen = em.getReference(codExamen.getClass(), codExamen.getCodExamen());
                clsAsignacion.setCodExamen(codExamen);
            }
            clsMateria codMateria = clsAsignacion.getCodMateria();
            if (codMateria != null) {
                codMateria = em.getReference(codMateria.getClass(), codMateria.getCodMateria());
                clsAsignacion.setCodMateria(codMateria);
            }
            clsUsuario codUsuario = clsAsignacion.getCodUsuario();
            if (codUsuario != null) {
                codUsuario = em.getReference(codUsuario.getClass(), codUsuario.getCodUsuario());
                clsAsignacion.setCodUsuario(codUsuario);
            }
            List<clsReactivo> attachedClsReactivoList = new ArrayList<clsReactivo>();
            for (clsReactivo clsReactivoListclsReactivoToAttach : clsAsignacion.getClsReactivoList()) {
                clsReactivoListclsReactivoToAttach = em.getReference(clsReactivoListclsReactivoToAttach.getClass(), clsReactivoListclsReactivoToAttach.getCodReactivo());
                attachedClsReactivoList.add(clsReactivoListclsReactivoToAttach);
            }
            clsAsignacion.setClsReactivoList(attachedClsReactivoList);
            List<clsUsuario> attachedClsUsuarioList = new ArrayList<clsUsuario>();
            for (clsUsuario clsUsuarioListclsUsuarioToAttach : clsAsignacion.getClsUsuarioList()) {
                clsUsuarioListclsUsuarioToAttach = em.getReference(clsUsuarioListclsUsuarioToAttach.getClass(), clsUsuarioListclsUsuarioToAttach.getCodUsuario());
                attachedClsUsuarioList.add(clsUsuarioListclsUsuarioToAttach);
            }
            clsAsignacion.setClsUsuarioList(attachedClsUsuarioList);
            em.persist(clsAsignacion);
            if (codExamen != null) {
                codExamen.getClsAsignacionList().add(clsAsignacion);
                codExamen = em.merge(codExamen);
            }
            if (codMateria != null) {
                codMateria.getClsAsignacionList().add(clsAsignacion);
                codMateria = em.merge(codMateria);
            }
            if (codUsuario != null) {
                codUsuario.getClsAsignacionList().add(clsAsignacion);
                codUsuario = em.merge(codUsuario);
            }
            for (clsReactivo clsReactivoListclsReactivo : clsAsignacion.getClsReactivoList()) {
                clsAsignacion oldCodAsignacionOfClsReactivoListclsReactivo = clsReactivoListclsReactivo.getCodAsignacion();
                clsReactivoListclsReactivo.setCodAsignacion(clsAsignacion);
                clsReactivoListclsReactivo = em.merge(clsReactivoListclsReactivo);
                if (oldCodAsignacionOfClsReactivoListclsReactivo != null) {
                    oldCodAsignacionOfClsReactivoListclsReactivo.getClsReactivoList().remove(clsReactivoListclsReactivo);
                    oldCodAsignacionOfClsReactivoListclsReactivo = em.merge(oldCodAsignacionOfClsReactivoListclsReactivo);
                }
            }
            for (clsUsuario clsUsuarioListclsUsuario : clsAsignacion.getClsUsuarioList()) {
                clsAsignacion oldCodAsignacionOfClsUsuarioListclsUsuario = clsUsuarioListclsUsuario.getCodAsignacion();
                clsUsuarioListclsUsuario.setCodAsignacion(clsAsignacion);
                clsUsuarioListclsUsuario = em.merge(clsUsuarioListclsUsuario);
                if (oldCodAsignacionOfClsUsuarioListclsUsuario != null) {
                    oldCodAsignacionOfClsUsuarioListclsUsuario.getClsUsuarioList().remove(clsUsuarioListclsUsuario);
                    oldCodAsignacionOfClsUsuarioListclsUsuario = em.merge(oldCodAsignacionOfClsUsuarioListclsUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findclsAsignacion(clsAsignacion.getCodAsignacion()) != null) {
                throw new PreexistingEntityException("clsAsignacion " + clsAsignacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(clsAsignacion clsAsignacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsAsignacion persistentclsAsignacion = em.find(clsAsignacion.class, clsAsignacion.getCodAsignacion());
            clsExamen codExamenOld = persistentclsAsignacion.getCodExamen();
            clsExamen codExamenNew = clsAsignacion.getCodExamen();
            clsMateria codMateriaOld = persistentclsAsignacion.getCodMateria();
            clsMateria codMateriaNew = clsAsignacion.getCodMateria();
            clsUsuario codUsuarioOld = persistentclsAsignacion.getCodUsuario();
            clsUsuario codUsuarioNew = clsAsignacion.getCodUsuario();
            List<clsReactivo> clsReactivoListOld = persistentclsAsignacion.getClsReactivoList();
            List<clsReactivo> clsReactivoListNew = clsAsignacion.getClsReactivoList();
            List<clsUsuario> clsUsuarioListOld = persistentclsAsignacion.getClsUsuarioList();
            List<clsUsuario> clsUsuarioListNew = clsAsignacion.getClsUsuarioList();
            if (codExamenNew != null) {
                codExamenNew = em.getReference(codExamenNew.getClass(), codExamenNew.getCodExamen());
                clsAsignacion.setCodExamen(codExamenNew);
            }
            if (codMateriaNew != null) {
                codMateriaNew = em.getReference(codMateriaNew.getClass(), codMateriaNew.getCodMateria());
                clsAsignacion.setCodMateria(codMateriaNew);
            }
            if (codUsuarioNew != null) {
                codUsuarioNew = em.getReference(codUsuarioNew.getClass(), codUsuarioNew.getCodUsuario());
                clsAsignacion.setCodUsuario(codUsuarioNew);
            }
            List<clsReactivo> attachedClsReactivoListNew = new ArrayList<clsReactivo>();
            for (clsReactivo clsReactivoListNewclsReactivoToAttach : clsReactivoListNew) {
                clsReactivoListNewclsReactivoToAttach = em.getReference(clsReactivoListNewclsReactivoToAttach.getClass(), clsReactivoListNewclsReactivoToAttach.getCodReactivo());
                attachedClsReactivoListNew.add(clsReactivoListNewclsReactivoToAttach);
            }
            clsReactivoListNew = attachedClsReactivoListNew;
            clsAsignacion.setClsReactivoList(clsReactivoListNew);
            List<clsUsuario> attachedClsUsuarioListNew = new ArrayList<clsUsuario>();
            for (clsUsuario clsUsuarioListNewclsUsuarioToAttach : clsUsuarioListNew) {
                clsUsuarioListNewclsUsuarioToAttach = em.getReference(clsUsuarioListNewclsUsuarioToAttach.getClass(), clsUsuarioListNewclsUsuarioToAttach.getCodUsuario());
                attachedClsUsuarioListNew.add(clsUsuarioListNewclsUsuarioToAttach);
            }
            clsUsuarioListNew = attachedClsUsuarioListNew;
            clsAsignacion.setClsUsuarioList(clsUsuarioListNew);
            clsAsignacion = em.merge(clsAsignacion);
            if (codExamenOld != null && !codExamenOld.equals(codExamenNew)) {
                codExamenOld.getClsAsignacionList().remove(clsAsignacion);
                codExamenOld = em.merge(codExamenOld);
            }
            if (codExamenNew != null && !codExamenNew.equals(codExamenOld)) {
                codExamenNew.getClsAsignacionList().add(clsAsignacion);
                codExamenNew = em.merge(codExamenNew);
            }
            if (codMateriaOld != null && !codMateriaOld.equals(codMateriaNew)) {
                codMateriaOld.getClsAsignacionList().remove(clsAsignacion);
                codMateriaOld = em.merge(codMateriaOld);
            }
            if (codMateriaNew != null && !codMateriaNew.equals(codMateriaOld)) {
                codMateriaNew.getClsAsignacionList().add(clsAsignacion);
                codMateriaNew = em.merge(codMateriaNew);
            }
            if (codUsuarioOld != null && !codUsuarioOld.equals(codUsuarioNew)) {
                codUsuarioOld.getClsAsignacionList().remove(clsAsignacion);
                codUsuarioOld = em.merge(codUsuarioOld);
            }
            if (codUsuarioNew != null && !codUsuarioNew.equals(codUsuarioOld)) {
                codUsuarioNew.getClsAsignacionList().add(clsAsignacion);
                codUsuarioNew = em.merge(codUsuarioNew);
            }
            for (clsReactivo clsReactivoListOldclsReactivo : clsReactivoListOld) {
                if (!clsReactivoListNew.contains(clsReactivoListOldclsReactivo)) {
                    clsReactivoListOldclsReactivo.setCodAsignacion(null);
                    clsReactivoListOldclsReactivo = em.merge(clsReactivoListOldclsReactivo);
                }
            }
            for (clsReactivo clsReactivoListNewclsReactivo : clsReactivoListNew) {
                if (!clsReactivoListOld.contains(clsReactivoListNewclsReactivo)) {
                    clsAsignacion oldCodAsignacionOfClsReactivoListNewclsReactivo = clsReactivoListNewclsReactivo.getCodAsignacion();
                    clsReactivoListNewclsReactivo.setCodAsignacion(clsAsignacion);
                    clsReactivoListNewclsReactivo = em.merge(clsReactivoListNewclsReactivo);
                    if (oldCodAsignacionOfClsReactivoListNewclsReactivo != null && !oldCodAsignacionOfClsReactivoListNewclsReactivo.equals(clsAsignacion)) {
                        oldCodAsignacionOfClsReactivoListNewclsReactivo.getClsReactivoList().remove(clsReactivoListNewclsReactivo);
                        oldCodAsignacionOfClsReactivoListNewclsReactivo = em.merge(oldCodAsignacionOfClsReactivoListNewclsReactivo);
                    }
                }
            }
            for (clsUsuario clsUsuarioListOldclsUsuario : clsUsuarioListOld) {
                if (!clsUsuarioListNew.contains(clsUsuarioListOldclsUsuario)) {
                    clsUsuarioListOldclsUsuario.setCodAsignacion(null);
                    clsUsuarioListOldclsUsuario = em.merge(clsUsuarioListOldclsUsuario);
                }
            }
            for (clsUsuario clsUsuarioListNewclsUsuario : clsUsuarioListNew) {
                if (!clsUsuarioListOld.contains(clsUsuarioListNewclsUsuario)) {
                    clsAsignacion oldCodAsignacionOfClsUsuarioListNewclsUsuario = clsUsuarioListNewclsUsuario.getCodAsignacion();
                    clsUsuarioListNewclsUsuario.setCodAsignacion(clsAsignacion);
                    clsUsuarioListNewclsUsuario = em.merge(clsUsuarioListNewclsUsuario);
                    if (oldCodAsignacionOfClsUsuarioListNewclsUsuario != null && !oldCodAsignacionOfClsUsuarioListNewclsUsuario.equals(clsAsignacion)) {
                        oldCodAsignacionOfClsUsuarioListNewclsUsuario.getClsUsuarioList().remove(clsUsuarioListNewclsUsuario);
                        oldCodAsignacionOfClsUsuarioListNewclsUsuario = em.merge(oldCodAsignacionOfClsUsuarioListNewclsUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clsAsignacion.getCodAsignacion();
                if (findclsAsignacion(id) == null) {
                    throw new NonexistentEntityException("The clsAsignacion with id " + id + " no longer exists.");
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
            clsAsignacion clsAsignacion;
            try {
                clsAsignacion = em.getReference(clsAsignacion.class, id);
                clsAsignacion.getCodAsignacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clsAsignacion with id " + id + " no longer exists.", enfe);
            }
            clsExamen codExamen = clsAsignacion.getCodExamen();
            if (codExamen != null) {
                codExamen.getClsAsignacionList().remove(clsAsignacion);
                codExamen = em.merge(codExamen);
            }
            clsMateria codMateria = clsAsignacion.getCodMateria();
            if (codMateria != null) {
                codMateria.getClsAsignacionList().remove(clsAsignacion);
                codMateria = em.merge(codMateria);
            }
            clsUsuario codUsuario = clsAsignacion.getCodUsuario();
            if (codUsuario != null) {
                codUsuario.getClsAsignacionList().remove(clsAsignacion);
                codUsuario = em.merge(codUsuario);
            }
            List<clsReactivo> clsReactivoList = clsAsignacion.getClsReactivoList();
            for (clsReactivo clsReactivoListclsReactivo : clsReactivoList) {
                clsReactivoListclsReactivo.setCodAsignacion(null);
                clsReactivoListclsReactivo = em.merge(clsReactivoListclsReactivo);
            }
            List<clsUsuario> clsUsuarioList = clsAsignacion.getClsUsuarioList();
            for (clsUsuario clsUsuarioListclsUsuario : clsUsuarioList) {
                clsUsuarioListclsUsuario.setCodAsignacion(null);
                clsUsuarioListclsUsuario = em.merge(clsUsuarioListclsUsuario);
            }
            em.remove(clsAsignacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<clsAsignacion> findclsAsignacionEntities() {
        return findclsAsignacionEntities(true, -1, -1);
    }

    public List<clsAsignacion> findclsAsignacionEntities(int maxResults, int firstResult) {
        return findclsAsignacionEntities(false, maxResults, firstResult);
    }

    private List<clsAsignacion> findclsAsignacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clsAsignacion.class));
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

    public clsAsignacion findclsAsignacion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clsAsignacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getclsAsignacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<clsAsignacion> rt = cq.from(clsAsignacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
