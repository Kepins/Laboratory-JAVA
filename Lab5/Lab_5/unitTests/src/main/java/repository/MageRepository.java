package repository;

import entity.Mage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class MageRepository {
    private Collection<Mage> collection;

    public MageRepository() {
        this.collection = new HashSet<>();
    }

    public Optional<Mage> find(String name) {
        for (Mage mage : this.collection){
            if(mage.getName().equals(name)){
                return Optional.of(mage);
            }
        }
        return Optional.empty();
    }
    public void delete(String name) {
        if(!this.collection.removeIf(mage -> mage.getName().equals(name))){
            throw new IllegalArgumentException();
        }
    }
    public void save(Mage mage) {
        if(this.find(mage.getName()).isPresent()){
            throw new IllegalArgumentException();
        }
        else{
            this.collection.add(mage);
        }
    }
}
