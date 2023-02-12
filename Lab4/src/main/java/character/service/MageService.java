package character.service;

import character.entity.Mage;
import character.entity.Tower;
import character.repository.MageRepository;

import java.util.List;

public class MageService {

    private final MageRepository repository;

    public MageService(MageRepository repository) {
        this.repository = repository;
    }

    public List<Mage> findAllMages() {
        return repository.findAll();
    }

    public void delete(Mage mage) {
        //mage.setTower(null);
        //repository.update(mage);
        repository.delete(mage);
    }

    public void create(Mage mage) {
        repository.add(mage);
    }

    public Mage find(String name){
        return repository.find(name);
    }

    public List<Mage> findAllMagesLevelGrater(int level){
        return repository.findAllMagesLevelGrater(level);
    }

    public List<Mage> query(Tower tower1, Tower tower2){
        return repository.query(tower1, tower2);
    }

    public void update(Mage mage){
        repository.update(mage);
    }
}
