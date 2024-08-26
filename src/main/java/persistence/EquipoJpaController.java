/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import logic.Equipo;
import logic.Laboratorio;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author danil
 */
public class EquipoJpaController implements Serializable {

    public EquipoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EquipoJpaController() {
        emf = Persistence.createEntityManagerFactory("com.elyon_yireh_lab_inventory");
    }

    public void create(Equipo equipo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Laboratorio laboratorioID = equipo.getLaboratorioID();
            if (laboratorioID != null) {
                laboratorioID = em.getReference(laboratorioID.getClass(), laboratorioID.getId());
                equipo.setLaboratorioID(laboratorioID);
            }
            em.persist(equipo);
            if (laboratorioID != null) {
                laboratorioID.getEquipoID().add(equipo);
                laboratorioID = em.merge(laboratorioID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Equipo equipo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipo persistentEquipo = em.find(Equipo.class, equipo.getId());
            Laboratorio laboratorioIDOld = persistentEquipo.getLaboratorioID();
            Laboratorio laboratorioIDNew = equipo.getLaboratorioID();
            if (laboratorioIDNew != null) {
                laboratorioIDNew = em.getReference(laboratorioIDNew.getClass(), laboratorioIDNew.getId());
                equipo.setLaboratorioID(laboratorioIDNew);
            }
            equipo = em.merge(equipo);
            if (laboratorioIDOld != null && !laboratorioIDOld.equals(laboratorioIDNew)) {
                laboratorioIDOld.getEquipoID().remove(equipo);
                laboratorioIDOld = em.merge(laboratorioIDOld);
            }
            if (laboratorioIDNew != null && !laboratorioIDNew.equals(laboratorioIDOld)) {
                laboratorioIDNew.getEquipoID().add(equipo);
                laboratorioIDNew = em.merge(laboratorioIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = equipo.getId();
                if (findEquipo(id) == null) {
                    throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipo equipo;
            try {
                equipo = em.getReference(Equipo.class, id);
                equipo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.", enfe);
            }
            Laboratorio laboratorioID = equipo.getLaboratorioID();
            if (laboratorioID != null) {
                laboratorioID.getEquipoID().remove(equipo);
                laboratorioID = em.merge(laboratorioID);
            }
            em.remove(equipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Equipo> findEquipoEntities() {
        return findEquipoEntities(true, -1, -1);
    }

    public List<Equipo> findEquipoEntities(int maxResults, int firstResult) {
        return findEquipoEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings("unchecked")
    private List<Equipo> findEquipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            @SuppressWarnings("rawtypes")
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Equipo.class));
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

    public Equipo findEquipo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Equipo.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unchecked")
    public int getEquipoCount() {
        EntityManager em = getEntityManager();
        try {
            @SuppressWarnings("rawtypes")
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Equipo> rt = cq.from(Equipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
