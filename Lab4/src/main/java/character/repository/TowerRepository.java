package character.repository;

import character.entity.Mage;
import character.entity.Tower;
import repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class TowerRepository extends JpaRepository<Tower, String> {
    /**
     * @param emf   thread safe factory which will be used for creating {@link EntityManager}
     */
    public TowerRepository(EntityManagerFactory emf) {
        super(emf, Tower.class);
    }


}