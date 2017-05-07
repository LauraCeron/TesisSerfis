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
import MODEL.clsPerfil;
import java.util.ArrayList;
import java.util.List;
import MODEL.clsExamen;
import MODEL.clsLlavesCifrado;
import MODEL.clsUsuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lau Cerón
 */
public class clsUsuarioJpaController implements Serializable {

    public clsUsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(clsUsuario clsUsuario) throws PreexistingEntityException, Exception {
        if (clsUsuario.getClsPerfilList() == null) {
            clsUsuario.setClsPerfilList(new ArrayList<clsPerfil>());
        }
        if (clsUsuario.getClsAsignacionList() == null) {
            clsUsuario.setClsAsignacionList(new ArrayList<clsAsignacion>());
        }
        if (clsUsuario.getClsExamenList() == null) {
            clsUsuario.setClsExamenList(new ArrayList<clsExamen>());
        }
        if (clsUsuario.getClsLlavesCifradoList() == null) {
            clsUsuario.setClsLlavesCifradoList(new ArrayList<clsLlavesCifrado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsArea codArea = clsUsuario.getCodArea();
            if (codArea != null) {
                codArea = em.getReference(codArea.getClass(), codArea.getCodArea());
                clsUsuario.setCodArea(codArea);
            }
            clsAsignacion codAsignacion = clsUsuario.getCodAsignacion();
            if (codAsignacion != null) {
                codAsignacion = em.getReference(codAsignacion.getClass(), codAsignacion.getCodAsignacion());
                clsUsuario.setCodAsignacion(codAsignacion);
            }
            List<clsPerfil> attachedClsPerfilList = new ArrayList<clsPerfil>();
            for (clsPerfil clsPerfilListclsPerfilToAttach : clsUsuario.getClsPerfilList()) {
                clsPerfilListclsPerfilToAttach = em.getReference(clsPerfilListclsPerfilToAttach.getClass(), clsPerfilListclsPerfilToAttach.getCodPerfil());
                attachedClsPerfilList.add(clsPerfilListclsPerfilToAttach);
            }
            clsUsuario.setClsPerfilList(attachedClsPerfilList);
            List<clsAsignacion> attachedClsAsignacionList = new ArrayList<clsAsignacion>();
            for (clsAsignacion clsAsignacionListclsAsignacionToAttach : clsUsuario.getClsAsignacionList()) {
                clsAsignacionListclsAsignacionToAttach = em.getReference(clsAsignacionListclsAsignacionToAttach.getClass(), clsAsignacionListclsAsignacionToAttach.getCodAsignacion());
                attachedClsAsignacionList.add(clsAsignacionListclsAsignacionToAttach);
            }
            clsUsuario.setClsAsignacionList(attachedClsAsignacionList);
            List<clsExamen> attachedClsExamenList = new ArrayList<clsExamen>();
            for (clsExamen clsExamenListclsExamenToAttach : clsUsuario.getClsExamenList()) {
                clsExamenListclsExamenToAttach = em.getReference(clsExamenListclsExamenToAttach.getClass(), clsExamenListclsExamenToAttach.getCodExamen());
                attachedClsExamenList.add(clsExamenListclsExamenToAttach);
            }
            clsUsuario.setClsExamenList(attachedClsExamenList);
            List<clsLlavesCifrado> attachedClsLlavesCifradoList = new ArrayList<clsLlavesCifrado>();
            for (clsLlavesCifrado clsLlavesCifradoListclsLlavesCifradoToAttach : clsUsuario.getClsLlavesCifradoList()) {
                clsLlavesCifradoListclsLlavesCifradoToAttach = em.getReference(clsLlavesCifradoListclsLlavesCifradoToAttach.getClass(), clsLlavesCifradoListclsLlavesCifradoToAttach.getCodLlavesCifrado());
                attachedClsLlavesCifradoList.add(clsLlavesCifradoListclsLlavesCifradoToAttach);
            }
            clsUsuario.setClsLlavesCifradoList(attachedClsLlavesCifradoList);
            em.persist(clsUsuario);
            if (codArea != null) {
                codArea.getClsUsuarioList().add(clsUsuario);
                codArea = em.merge(codArea);
            }
            if (codAsignacion != null) {
                clsUsuario oldCodUsuarioOfCodAsignacion = codAsignacion.getCodUsuario();
                if (oldCodUsuarioOfCodAsignacion != null) {
                    oldCodUsuarioOfCodAsignacion.setCodAsignacion(null);
                    oldCodUsuarioOfCodAsignacion = em.merge(oldCodUsuarioOfCodAsignacion);
                }
                codAsignacion.setCodUsuario(clsUsuario);
                codAsignacion = em.merge(codAsignacion);
            }
            for (clsPerfil clsPerfilListclsPerfil : clsUsuario.getClsPerfilList()) {
                clsPerfilListclsPerfil.getClsUsuarioList().add(clsUsuario);
                clsPerfilListclsPerfil = em.merge(clsPerfilListclsPerfil);
            }
            for (clsAsignacion clsAsignacionListclsAsignacion : clsUsuario.getClsAsignacionList()) {
                clsUsuario oldCodUsuarioOfClsAsignacionListclsAsignacion = clsAsignacionListclsAsignacion.getCodUsuario();
                clsAsignacionListclsAsignacion.setCodUsuario(clsUsuario);
                clsAsignacionListclsAsignacion = em.merge(clsAsignacionListclsAsignacion);
                if (oldCodUsuarioOfClsAsignacionListclsAsignacion != null) {
                    oldCodUsuarioOfClsAsignacionListclsAsignacion.getClsAsignacionList().remove(clsAsignacionListclsAsignacion);
                    oldCodUsuarioOfClsAsignacionListclsAsignacion = em.merge(oldCodUsuarioOfClsAsignacionListclsAsignacion);
                }
            }
            for (clsExamen clsExamenListclsExamen : clsUsuario.getClsExamenList()) {
                clsUsuario oldCodUsuarioOfClsExamenListclsExamen = clsExamenListclsExamen.getCodUsuario();
                clsExamenListclsExamen.setCodUsuario(clsUsuario);
                clsExamenListclsExamen = em.merge(clsExamenListclsExamen);
                if (oldCodUsuarioOfClsExamenListclsExamen != null) {
                    oldCodUsuarioOfClsExamenListclsExamen.getClsExamenList().remove(clsExamenListclsExamen);
                    oldCodUsuarioOfClsExamenListclsExamen = em.merge(oldCodUsuarioOfClsExamenListclsExamen);
                }
            }
            for (clsLlavesCifrado clsLlavesCifradoListclsLlavesCifrado : clsUsuario.getClsLlavesCifradoList()) {
                clsUsuario oldCodUsuarioOfClsLlavesCifradoListclsLlavesCifrado = clsLlavesCifradoListclsLlavesCifrado.getCodUsuario();
                clsLlavesCifradoListclsLlavesCifrado.setCodUsuario(clsUsuario);
                clsLlavesCifradoListclsLlavesCifrado = em.merge(clsLlavesCifradoListclsLlavesCifrado);
                if (oldCodUsuarioOfClsLlavesCifradoListclsLlavesCifrado != null) {
                    oldCodUsuarioOfClsLlavesCifradoListclsLlavesCifrado.getClsLlavesCifradoList().remove(clsLlavesCifradoListclsLlavesCifrado);
                    oldCodUsuarioOfClsLlavesCifradoListclsLlavesCifrado = em.merge(oldCodUsuarioOfClsLlavesCifradoListclsLlavesCifrado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findclsUsuario(clsUsuario.getCodUsuario()) != null) {
                throw new PreexistingEntityException("clsUsuario " + clsUsuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(clsUsuario clsUsuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clsUsuario persistentclsUsuario = em.find(clsUsuario.class, clsUsuario.getCodUsuario());
            clsArea codAreaOld = persistentclsUsuario.getCodArea();
            clsArea codAreaNew = clsUsuario.getCodArea();
            clsAsignacion codAsignacionOld = persistentclsUsuario.getCodAsignacion();
            clsAsignacion codAsignacionNew = clsUsuario.getCodAsignacion();
            List<clsPerfil> clsPerfilListOld = persistentclsUsuario.getClsPerfilList();
            List<clsPerfil> clsPerfilListNew = clsUsuario.getClsPerfilList();
            List<clsAsignacion> clsAsignacionListOld = persistentclsUsuario.getClsAsignacionList();
            List<clsAsignacion> clsAsignacionListNew = clsUsuario.getClsAsignacionList();
            List<clsExamen> clsExamenListOld = persistentclsUsuario.getClsExamenList();
            List<clsExamen> clsExamenListNew = clsUsuario.getClsExamenList();
            List<clsLlavesCifrado> clsLlavesCifradoListOld = persistentclsUsuario.getClsLlavesCifradoList();
            List<clsLlavesCifrado> clsLlavesCifradoListNew = clsUsuario.getClsLlavesCifradoList();
            if (codAreaNew != null) {
                codAreaNew = em.getReference(codAreaNew.getClass(), codAreaNew.getCodArea());
                clsUsuario.setCodArea(codAreaNew);
            }
            if (codAsignacionNew != null) {
                codAsignacionNew = em.getReference(codAsignacionNew.getClass(), codAsignacionNew.getCodAsignacion());
                clsUsuario.setCodAsignacion(codAsignacionNew);
            }
            List<clsPerfil> attachedClsPerfilListNew = new ArrayList<clsPerfil>();
            for (clsPerfil clsPerfilListNewclsPerfilToAttach : clsPerfilListNew) {
                clsPerfilListNewclsPerfilToAttach = em.getReference(clsPerfilListNewclsPerfilToAttach.getClass(), clsPerfilListNewclsPerfilToAttach.getCodPerfil());
                attachedClsPerfilListNew.add(clsPerfilListNewclsPerfilToAttach);
            }
            clsPerfilListNew = attachedClsPerfilListNew;
            clsUsuario.setClsPerfilList(clsPerfilListNew);
            List<clsAsignacion> attachedClsAsignacionListNew = new ArrayList<clsAsignacion>();
            for (clsAsignacion clsAsignacionListNewclsAsignacionToAttach : clsAsignacionListNew) {
                clsAsignacionListNewclsAsignacionToAttach = em.getReference(clsAsignacionListNewclsAsignacionToAttach.getClass(), clsAsignacionListNewclsAsignacionToAttach.getCodAsignacion());
                attachedClsAsignacionListNew.add(clsAsignacionListNewclsAsignacionToAttach);
            }
            clsAsignacionListNew = attachedClsAsignacionListNew;
            clsUsuario.setClsAsignacionList(clsAsignacionListNew);
            List<clsExamen> attachedClsExamenListNew = new ArrayList<clsExamen>();
            for (clsExamen clsExamenListNewclsExamenToAttach : clsExamenListNew) {
                clsExamenListNewclsExamenToAttach = em.getReference(clsExamenListNewclsExamenToAttach.getClass(), clsExamenListNewclsExamenToAttach.getCodExamen());
                attachedClsExamenListNew.add(clsExamenListNewclsExamenToAttach);
            }
            clsExamenListNew = attachedClsExamenListNew;
            clsUsuario.setClsExamenList(clsExamenListNew);
            List<clsLlavesCifrado> attachedClsLlavesCifradoListNew = new ArrayList<clsLlavesCifrado>();
            for (clsLlavesCifrado clsLlavesCifradoListNewclsLlavesCifradoToAttach : clsLlavesCifradoListNew) {
                clsLlavesCifradoListNewclsLlavesCifradoToAttach = em.getReference(clsLlavesCifradoListNewclsLlavesCifradoToAttach.getClass(), clsLlavesCifradoListNewclsLlavesCifradoToAttach.getCodLlavesCifrado());
                attachedClsLlavesCifradoListNew.add(clsLlavesCifradoListNewclsLlavesCifradoToAttach);
            }
            clsLlavesCifradoListNew = attachedClsLlavesCifradoListNew;
            clsUsuario.setClsLlavesCifradoList(clsLlavesCifradoListNew);
            clsUsuario = em.merge(clsUsuario);
            if (codAreaOld != null && !codAreaOld.equals(codAreaNew)) {
                codAreaOld.getClsUsuarioList().remove(clsUsuario);
                codAreaOld = em.merge(codAreaOld);
            }
            if (codAreaNew != null && !codAreaNew.equals(codAreaOld)) {
                codAreaNew.getClsUsuarioList().add(clsUsuario);
                codAreaNew = em.merge(codAreaNew);
            }
            if (codAsignacionOld != null && !codAsignacionOld.equals(codAsignacionNew)) {
                codAsignacionOld.setCodUsuario(null);
                codAsignacionOld = em.merge(codAsignacionOld);
            }
            if (codAsignacionNew != null && !codAsignacionNew.equals(codAsignacionOld)) {
                clsUsuario oldCodUsuarioOfCodAsignacion = codAsignacionNew.getCodUsuario();
                if (oldCodUsuarioOfCodAsignacion != null) {
                    oldCodUsuarioOfCodAsignacion.setCodAsignacion(null);
                    oldCodUsuarioOfCodAsignacion = em.merge(oldCodUsuarioOfCodAsignacion);
                }
                codAsignacionNew.setCodUsuario(clsUsuario);
                codAsignacionNew = em.merge(codAsignacionNew);
            }
            for (clsPerfil clsPerfilListOldclsPerfil : clsPerfilListOld) {
                if (!clsPerfilListNew.contains(clsPerfilListOldclsPerfil)) {
                    clsPerfilListOldclsPerfil.getClsUsuarioList().remove(clsUsuario);
                    clsPerfilListOldclsPerfil = em.merge(clsPerfilListOldclsPerfil);
                }
            }
            for (clsPerfil clsPerfilListNewclsPerfil : clsPerfilListNew) {
                if (!clsPerfilListOld.contains(clsPerfilListNewclsPerfil)) {
                    clsPerfilListNewclsPerfil.getClsUsuarioList().add(clsUsuario);
                    clsPerfilListNewclsPerfil = em.merge(clsPerfilListNewclsPerfil);
                }
            }
            for (clsAsignacion clsAsignacionListOldclsAsignacion : clsAsignacionListOld) {
                if (!clsAsignacionListNew.contains(clsAsignacionListOldclsAsignacion)) {
                    clsAsignacionListOldclsAsignacion.setCodUsuario(null);
                    clsAsignacionListOldclsAsignacion = em.merge(clsAsignacionListOldclsAsignacion);
                }
            }
            for (clsAsignacion clsAsignacionListNewclsAsignacion : clsAsignacionListNew) {
                if (!clsAsignacionListOld.contains(clsAsignacionListNewclsAsignacion)) {
                    clsUsuario oldCodUsuarioOfClsAsignacionListNewclsAsignacion = clsAsignacionListNewclsAsignacion.getCodUsuario();
                    clsAsignacionListNewclsAsignacion.setCodUsuario(clsUsuario);
                    clsAsignacionListNewclsAsignacion = em.merge(clsAsignacionListNewclsAsignacion);
                    if (oldCodUsuarioOfClsAsignacionListNewclsAsignacion != null && !oldCodUsuarioOfClsAsignacionListNewclsAsignacion.equals(clsUsuario)) {
                        oldCodUsuarioOfClsAsignacionListNewclsAsignacion.getClsAsignacionList().remove(clsAsignacionListNewclsAsignacion);
                        oldCodUsuarioOfClsAsignacionListNewclsAsignacion = em.merge(oldCodUsuarioOfClsAsignacionListNewclsAsignacion);
                    }
                }
            }
            for (clsExamen clsExamenListOldclsExamen : clsExamenListOld) {
                if (!clsExamenListNew.contains(clsExamenListOldclsExamen)) {
                    clsExamenListOldclsExamen.setCodUsuario(null);
                    clsExamenListOldclsExamen = em.merge(clsExamenListOldclsExamen);
                }
            }
            for (clsExamen clsExamenListNewclsExamen : clsExamenListNew) {
                if (!clsExamenListOld.contains(clsExamenListNewclsExamen)) {
                    clsUsuario oldCodUsuarioOfClsExamenListNewclsExamen = clsExamenListNewclsExamen.getCodUsuario();
                    clsExamenListNewclsExamen.setCodUsuario(clsUsuario);
                    clsExamenListNewclsExamen = em.merge(clsExamenListNewclsExamen);
                    if (oldCodUsuarioOfClsExamenListNewclsExamen != null && !oldCodUsuarioOfClsExamenListNewclsExamen.equals(clsUsuario)) {
                        oldCodUsuarioOfClsExamenListNewclsExamen.getClsExamenList().remove(clsExamenListNewclsExamen);
                        oldCodUsuarioOfClsExamenListNewclsExamen = em.merge(oldCodUsuarioOfClsExamenListNewclsExamen);
                    }
                }
            }
            for (clsLlavesCifrado clsLlavesCifradoListOldclsLlavesCifrado : clsLlavesCifradoListOld) {
                if (!clsLlavesCifradoListNew.contains(clsLlavesCifradoListOldclsLlavesCifrado)) {
                    clsLlavesCifradoListOldclsLlavesCifrado.setCodUsuario(null);
                    clsLlavesCifradoListOldclsLlavesCifrado = em.merge(clsLlavesCifradoListOldclsLlavesCifrado);
                }
            }
            for (clsLlavesCifrado clsLlavesCifradoListNewclsLlavesCifrado : clsLlavesCifradoListNew) {
                if (!clsLlavesCifradoListOld.contains(clsLlavesCifradoListNewclsLlavesCifrado)) {
                    clsUsuario oldCodUsuarioOfClsLlavesCifradoListNewclsLlavesCifrado = clsLlavesCifradoListNewclsLlavesCifrado.getCodUsuario();
                    clsLlavesCifradoListNewclsLlavesCifrado.setCodUsuario(clsUsuario);
                    clsLlavesCifradoListNewclsLlavesCifrado = em.merge(clsLlavesCifradoListNewclsLlavesCifrado);
                    if (oldCodUsuarioOfClsLlavesCifradoListNewclsLlavesCifrado != null && !oldCodUsuarioOfClsLlavesCifradoListNewclsLlavesCifrado.equals(clsUsuario)) {
                        oldCodUsuarioOfClsLlavesCifradoListNewclsLlavesCifrado.getClsLlavesCifradoList().remove(clsLlavesCifradoListNewclsLlavesCifrado);
                        oldCodUsuarioOfClsLlavesCifradoListNewclsLlavesCifrado = em.merge(oldCodUsuarioOfClsLlavesCifradoListNewclsLlavesCifrado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clsUsuario.getCodUsuario();
                if (findclsUsuario(id) == null) {
                    throw new NonexistentEntityException("The clsUsuario with id " + id + " no longer exists.");
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
            clsUsuario clsUsuario;
            try {
                clsUsuario = em.getReference(clsUsuario.class, id);
                clsUsuario.getCodUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clsUsuario with id " + id + " no longer exists.", enfe);
            }
            clsArea codArea = clsUsuario.getCodArea();
            if (codArea != null) {
                codArea.getClsUsuarioList().remove(clsUsuario);
                codArea = em.merge(codArea);
            }
            clsAsignacion codAsignacion = clsUsuario.getCodAsignacion();
            if (codAsignacion != null) {
                codAsignacion.setCodUsuario(null);
                codAsignacion = em.merge(codAsignacion);
            }
            List<clsPerfil> clsPerfilList = clsUsuario.getClsPerfilList();
            for (clsPerfil clsPerfilListclsPerfil : clsPerfilList) {
                clsPerfilListclsPerfil.getClsUsuarioList().remove(clsUsuario);
                clsPerfilListclsPerfil = em.merge(clsPerfilListclsPerfil);
            }
            List<clsAsignacion> clsAsignacionList = clsUsuario.getClsAsignacionList();
            for (clsAsignacion clsAsignacionListclsAsignacion : clsAsignacionList) {
                clsAsignacionListclsAsignacion.setCodUsuario(null);
                clsAsignacionListclsAsignacion = em.merge(clsAsignacionListclsAsignacion);
            }
            List<clsExamen> clsExamenList = clsUsuario.getClsExamenList();
            for (clsExamen clsExamenListclsExamen : clsExamenList) {
                clsExamenListclsExamen.setCodUsuario(null);
                clsExamenListclsExamen = em.merge(clsExamenListclsExamen);
            }
            List<clsLlavesCifrado> clsLlavesCifradoList = clsUsuario.getClsLlavesCifradoList();
            for (clsLlavesCifrado clsLlavesCifradoListclsLlavesCifrado : clsLlavesCifradoList) {
                clsLlavesCifradoListclsLlavesCifrado.setCodUsuario(null);
                clsLlavesCifradoListclsLlavesCifrado = em.merge(clsLlavesCifradoListclsLlavesCifrado);
            }
            em.remove(clsUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<clsUsuario> findclsUsuarioEntities() {
        return findclsUsuarioEntities(true, -1, -1);
    }

    public List<clsUsuario> findclsUsuarioEntities(int maxResults, int firstResult) {
        return findclsUsuarioEntities(false, maxResults, firstResult);
    }

    private List<clsUsuario> findclsUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clsUsuario.class));
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

    public clsUsuario findclsUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clsUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getclsUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<clsUsuario> rt = cq.from(clsUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
