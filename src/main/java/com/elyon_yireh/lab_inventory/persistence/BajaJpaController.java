/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.elyon_yireh.lab_inventory.persistence;

import com.elyon_yireh.lab_inventory.persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logic.Baja;

/**
 *
 * @author danil
 */
public class BajaJpaController implements Serializable {

    public BajaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public BajaJpaController (){
        emf = Persistence.createEntityManagerFactory("com.elyon_yireh_lab_inventory");
    }

    public void create(Baja baja) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(baja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Baja baja) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            baja = em.merge(baja);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = baja.getId();
                if (findBaja(id) == null) {
                    throw new NonexistentEntityException("The baja with id " + id + " no longer exists.");
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
            Baja baja;
            try {
                baja = em.getReference(Baja.class, id);
                baja.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baja with id " + id + " no longer exists.", enfe);
            }
            em.remove(baja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Baja> findBajaEntities() {
        return findBajaEntities(true, -1, -1);
    }

    public List<Baja> findBajaEntities(int maxResults, int firstResult) {
        return findBajaEntities(false, maxResults, firstResult);
    }

    private List<Baja> findBajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Baja.class));
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

    public Baja findBaja(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Baja.class, id);
        } finally {
            em.close();
        }
    }

    public int getBajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Baja> rt = cq.from(Baja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
