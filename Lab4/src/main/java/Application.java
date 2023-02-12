import character.entity.Mage;
import character.entity.Tower;
import character.repository.MageRepository;
import character.repository.TowerRepository;
import character.service.MageService;
import character.service.TowerService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4");

        MageService mageService = new MageService(new MageRepository(emf));
        TowerService towerService = new TowerService(new TowerRepository(emf));
        initData(mageService, towerService);

        System.out.println("\nAll towers with it's mages");
        for (Tower tower : towerService.findAllTowers()){
            System.out.println(tower);
            for (Mage mage : tower.getMages()){
                System.out.println("\t"+mage);
            }
        }

        //deleting
        mageService.delete(mageService.find("Ewa"));
        towerService.delete(towerService.find("Wieza 2"), mageService);

        //adding
        Mage pawel = new Mage("Paweł", 31, towerService.find("Wieza 1"));
        mageService.create(pawel);

        System.out.println("\nAll towers with it's mages");
        for (Tower tower : towerService.findAllTowers()){
            System.out.println(tower);
            for (Mage mage : tower.getMages()){
                System.out.println("\t"+mage);
            }
        }
        System.out.println("\nAll mages without towers");
        for(Mage mage : mageService.findAllMages()){
            if(mage.getTower() == null){
                System.out.println(mage);
            }
        }


        System.out.println("\nQuery result");
        for(Mage mage : mageService.query(towerService.find("Wieza 3"), towerService.find("Wieza 1"))){
            System.out.println(mage);
        }




        emf.close();
    }
    private static void initData(MageService mageService, TowerService towerService){

        Tower wieza1 = new Tower("Wieza 1", 150, null);
        towerService.create(wieza1);
        Mage adam = new Mage("Adam", 10, wieza1);
        Mage ewa = new Mage("Ewa", 11, wieza1);
        mageService.create(adam);
        mageService.create(ewa);

        Tower wieza2 = new Tower("Wieza 2", 182, null);
        towerService.create(wieza2);
        Mage wieslaw = new Mage("Wieslaw", 15, wieza2);
        Mage bogdan = new Mage("Bogdan", 7, wieza2);
        mageService.create(wieslaw);
        mageService.create(bogdan);


        Tower wieza3 = new Tower("Wieza 3", 185, null);
        towerService.create(wieza3);
        Mage maciej = new Mage("Maciej", 15, wieza3);
        mageService.create(maciej);
    }


}
