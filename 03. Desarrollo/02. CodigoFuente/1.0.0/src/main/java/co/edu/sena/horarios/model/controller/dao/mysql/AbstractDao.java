package co.edu.sena.horarios.model.controller.dao.mysql;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public abstract class AbstractDao<T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());

    protected EntityManager em;
    private Class<T> entityClass;

    public void cleanEntity() {
        em.flush();
        em.getEntityManagerFactory().getCache().evictAll();
    }

    public AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UPSemantica");
        this.em = emf.createEntityManager();
        return this.em;
    }

    public void insert(T entity) {
        try {
            this.getEntityManager();
            this.em.getTransaction().begin();
            em.persist(entity);
            this.em.getTransaction().commit();
        } catch (PersistenceException e) {
            LOGGER.severe(e.getMessage());
        }

    }

    public void update(T entity) {
        try {
            this.getEntityManager();
            this.em.getTransaction().begin();
            em.merge(entity);
            this.em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Exception:" + e.getMessage());
        }
    }

    public void remove(T entity) {
        try {
            this.getEntityManager();
            this.em.getTransaction().begin();
            em.remove(em.merge(entity));
            this.em.getTransaction().commit();
        } catch (PersistenceException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public T find(Object id) {
        try {
            this.getEntityManager();
            return this.em.find(entityClass, id);
        } catch (PersistenceException e) {
            LOGGER.severe(e.getMessage());
        }
        return null;
    }

    public Collection<T> findAll() {
        try {
            this.getEntityManager();
            javax.persistence.criteria.CriteriaQuery cq = this.em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            return this.em.createQuery(cq).getResultList();
        } catch (PersistenceException e) {
            LOGGER.severe(e.getMessage());
        }
        return null;
    }
}