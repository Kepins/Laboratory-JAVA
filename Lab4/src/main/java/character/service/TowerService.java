package character.service;

import character.entity.Mage;
import character.entity.Tower;
import character.repository.MageRepository;
import character.repository.TowerRepository;

import java.util.List;

public class TowerService {

    private final TowerRepository repository;

    public TowerService(TowerRepository repository) {
        this.repository = repository;
    }

    public List<Tower> findAllTowers() {
        return repository.findAll();
    }

    public void delete(Tower tower, MageService mageService) {
        List<Mage> mages = tower.getMages();
        for (Mage mage : mages){
            mageService.find(mage.getName());
            mage.setTower(null);
            mageService.update(mage);
        }
        repository.delete(tower);
    }

    public void create(Tower tower) {
        repository.add(tower);
    }

    public Tower find(String id) { return repository.find(id);}
}
