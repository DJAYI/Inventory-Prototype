/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logic.Equipo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logic.Laboratorio;

/**
 *
 * @author danil
 */
public class LaboratorioJpaController implements Serializable {

    public LaboratorioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public LaboratorioJpaController() {
        emf = Persistence.createEntityManagerFactory("com.elyon_yireh_lab_inventory");
    }

    public void create(Laboratorio laboratorio) {
        if (laboratorio.getEquipoID() == null) {
            laboratorio.setEquipoID(new ArrayList<Equipo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Equipo> attachedEquipoID = new ArrayList<Equipo>();
            for (Equipo equipoIDEquipoToAttach : laboratorio.getEquipoID()) {
                equipoIDEquipoToAttach = em.getReference(equipoIDEquipoToAttach.getClass(),
                        equipoIDEquipoToAttach.getId());
                attachedEquipoID.add(equipoIDEquipoToAttach);
            }
            laboratorio.setEquipoID(attachedEquipoID);
            em.persist(laboratorio);
            for (Equipo equipoIDEquipo : laboratorio.getEquipoID()) {
                Laboratorio oldLaboratorioIDOfEquipoIDEquipo = equipoIDEquipo.getLaboratorioID();
                equipoIDEquipo.setLaboratorioID(laboratorio);
                equipoIDEquipo = em.merge(equipoIDEquipo);
                if (oldLaboratorioIDOfEquipoIDEquipo != null) {
                    oldLaboratorioIDOfEquipoIDEquipo.getEquipoID().remove(equipoIDEquipo);
                    oldLaboratorioIDOfEquipoIDEquipo = em.merge(oldLaboratorioIDOfEquipoIDEquipo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Laboratorio laboratorio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Laboratorio persistentLaboratorio = em.find(Laboratorio.class, laboratorio.getId());
            ArrayList<Equipo> equipoIDOld = persistentLaboratorio.getEquipoID();
            ArrayList<Equipo> equipoIDNew = laboratorio.getEquipoID();
            ArrayList<Equipo> attachedEquipoIDNew = new ArrayList<Equipo>();
            for (Equipo equipoIDNewEquipoToAttach : equipoIDNew) {
                equipoIDNewEquipoToAttach = em.getReference(equipoIDNewEquipoToAttach.getClass(),
                        equipoIDNewEquipoToAttach.getId());
                attachedEquipoIDNew.add(equipoIDNewEquipoToAttach);
            }
            equipoIDNew = attachedEquipoIDNew;
            laboratorio.setEquipoID(equipoIDNew);
            laboratorio = em.merge(laboratorio);
            for (Equipo equipoIDOldEquipo : equipoIDOld) {
                if (!equipoIDNew.contains(equipoIDOldEquipo)) {
                    equipoIDOldEquipo.setLaboratorioID(null);
                    equipoIDOldEquipo = em.merge(equipoIDOldEquipo);
                }
            }
            for (Equipo equipoIDNewEquipo : equipoIDNew) {
                if (!equipoIDOld.contains(equipoIDNewEquipo)) {
                    Laboratorio oldLaboratorioIDOfEquipoIDNewEquipo = equipoIDNewEquipo.getLaboratorioID();
                    equipoIDNewEquipo.setLaboratorioID(laboratorio);
                    equipoIDNewEquipo = em.merge(equipoIDNewEquipo);
                    if (oldLaboratorioIDOfEquipoIDNewEquipo != null
                            && !oldLaboratorioIDOfEquipoIDNewEquipo.equals(laboratorio)) {
                        oldLaboratorioIDOfEquipoIDNewEquipo.getEquipoID().remove(equipoIDNewEquipo);
                        oldLaboratorioIDOfEquipoIDNewEquipo = em.merge(oldLaboratorioIDOfEquipoIDNewEquipo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = laboratorio.getId();
                if (findLaboratorio(id) == null) {
                    throw new NonexistentEntityException("The laboratorio with id " + id + " no longer exists.");
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
            Laboratorio laboratorio;
            try {
                laboratorio = em.getReference(Laboratorio.class, id);
                laboratorio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The laboratorio with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Equipo> equipoID = laboratorio.getEquipoID();
            for (Equipo equipoIDEquipo : equipoID) {
                equipoIDEquipo.setLaboratorioID(null);
                equipoIDEquipo = em.merge(equipoIDEquipo);
            }
            em.remove(laboratorio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Laboratorio> findLaboratorioEntities() {
        return findLaboratorioEntities(true, -1, -1);
    }

    public List<Laboratorio> findLaboratorioEntities(int maxResults, int firstResult) {
        return findLaboratorioEntities(false, maxResults, firstResult);
    }

    private List<Laboratorio> findLaboratorioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Laboratorio.class));
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

    public Laboratorio findLaboratorio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Laboratorio.class, id);
        } finally {
            em.close();
        }
    }

    public int getLaboratorioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Laboratorio> rt = cq.from(Laboratorio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
