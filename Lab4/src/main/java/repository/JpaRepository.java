package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;



/**
 * Simple JPA repository for storing entity classes. It uses thread safe {@link javax.persistence.EntityManagerFactory}
 * for creating {@link javax.persistence.EntityManager} objject for each request.
 *
 * @param <E> type of the entity stored in repository
 * @param <K> type of the entity primary key
 */
public abstract class JpaRepository<E, K> {

    /**
     * Thread safe factory for creating {@link EntityManager} objects.
     */
    private final EntityManagerFactory emf;

    /**
     * Class of entity object. This is required because generics are not accessible in runtime.
     */
    private final Class<E> clazz;

    /**
     * @param emf   thread safe factory which will be used for creating {@link javax.persistence.EntityManager}
     * @param clazz class of entity object, this is required because generic are not accessible in runtime
     */
    public JpaRepository(EntityManagerFactory emf, Class<E> clazz) {
        this.emf = emf;
        this.clazz = clazz;
    }

    /**
     * @return list containing all entries
     */
    public List<E> findAll() {
        EntityManager em = emf.createEntityManager();
        List<E> list = em.createQuery("select e from " + clazz.getSimpleName() + " e", clazz).getResultList();
        em.close();
        return list;
    }

    /**
     * @param id entity id
     * @return entity
     */
    public E find(K id) {
        EntityManager em = emf.createEntityManager();
        E entity = em.find(clazz, id);
        em.close();
        return entity;
    }

    /**
     * @param entity element to be removed
     */
    public void delete(E entity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(em.merge(entity));
        transaction.commit();
        em.close();
    }

    /**
     * @param entity element to be stored
     */
    public void add(E entity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(entity);
        transaction.commit();
        em.close();
    }

    /**
     * @param entity element to be updated
     */
    public void update(E entity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(entity);
        transaction.commit();
        em.close();
    }

    protected EntityManagerFactory getEmf() {
        return emf;
    }

}