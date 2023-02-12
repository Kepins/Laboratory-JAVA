package character.repository;

import character.entity.FamilyMember;
import repository.InMemoryRepository;

import java.util.*;

public class FamilyMemberRepository extends InMemoryRepository<FamilyMember> {


    public FamilyMemberRepository() {
    }

    public FamilyMemberRepository(boolean sorted) {
        super(sorted);
    }

    public FamilyMemberRepository(Comparator<FamilyMember> comparator) {
        super(comparator);
    }

    public List<FamilyMember> findBosses(){
        List<FamilyMember> arrayWithAll = super.findAll();
        List<FamilyMember> arrayWithBosses = new ArrayList<>();

        for (FamilyMember familyMember : arrayWithAll){
            boolean hasBoss = false;
            for(FamilyMember potentialBoss : arrayWithAll){
                if(potentialBoss.getChildren().findAllCharacters().contains(familyMember)){
                    hasBoss = true;
                    break;
                }
            }
            if (!hasBoss){
                arrayWithBosses.add(familyMember);
            }
        }
        return arrayWithBosses;
    }


}
