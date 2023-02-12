package controller;

import entity.Mage;
import repository.MageRepository;

import java.util.Optional;

public class MageController {
    private MageRepository repository;

    public MageController(MageRepository repository) {
        this.repository = repository;
    }
    public String find(String name) {
        Optional<Mage> result = this.repository.find(name);
        if(result.isPresent()){
            return result.get().toString();
        }
        else{
            return "not found";
        }
    }
    public String delete(String name) {
        try{
            this.repository.delete(name);
            return "done";
        } catch(IllegalArgumentException ex){
            return "not found";
        }
    }
    public String save(String name, String level) {
        Mage mage = new Mage(name, Integer.parseInt(level));
        try {
            this.repository.save(mage);
            return "done";
        } catch(IllegalArgumentException ex){
            return "bad request";
        }
    }
}