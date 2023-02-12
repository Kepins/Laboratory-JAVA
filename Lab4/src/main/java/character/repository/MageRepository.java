package character.repository;

import character.entity.Mage;
import character.entity.Tower;
import character.service.TowerService;
import repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MageRepository extends JpaRepository<Mage, String> {
    /**
     * @param emf   thread safe factory which will be used for creating {@link EntityManager}
     */
    public MageRepository(EntityManagerFactory emf) {
        super(emf, Mage.class);
    }

    public List<Mage> findAllMagesLevelGrater(int level){
        EntityManager em = getEmf().createEntityManager();
        List<Mage> mages = em.createQuery("select m from Mage m where m.level > :level", Mage.class)
                .setParameter("level", level).getResultList();
        em.close();
        return mages;
    }

    public List<Mage> query(Tower tower1, Tower tower2){
        EntityManager em = getEmf().createEntityManager();
        List<Mage> mages = em.createQuery("SELECT m1 FROM Mage m1 WHERE m1.level > (SELECT min(m2.level) FROM Mage m2 WHERE m2.tower = :tower1) AND m1.tower = :tower2", Mage.class)
                .setParameter("tower1", tower1).setParameter("tower2", tower2).getResultList();
        em.close();
        return mages;
    }

}
