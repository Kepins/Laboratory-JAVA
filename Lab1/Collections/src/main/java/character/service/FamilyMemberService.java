package character.service;


import character.entity.FamilyMember;
import character.repository.FamilyMemberRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Service for managing {@link FamilyMember} entities.
 */
public class FamilyMemberService {

    /**
     * Repository for {@link FamilyMember}.
     */
    private final FamilyMemberRepository repository;

    /**
     * @param repository repository for {@link FamilyMember}
     */
    public FamilyMemberService(FamilyMemberRepository repository) {
        this.repository = repository;
    }

    /**
     * @return all family members
     */
    public List<FamilyMember> findAllCharacters() {
        return repository.findAll();
    }

    /**
     * @return all family members that have no boss in this service
     */
    public List<FamilyMember> findAllBosses() { return repository.findBosses(); }

    /**
     * @param familyMember family member to be removed
     */
    public void delete(FamilyMember familyMember) {
        repository.delete(familyMember);
    }

    /**
     * @param familyMember new family member to be saved
     */
    public void create(FamilyMember familyMember) {
        repository.add(familyMember);
    }


    public Map<FamilyMember, Integer> createApprenticesStatistics(){
        Map<FamilyMember, Integer> map;
        if(repository.isSorted() && !repository.isAlternative()){
            //sorted in natural order
            map = new TreeMap<>();
        }
        else if (repository.isSorted()){
            //sorted in alternative order
            map = new TreeMap<>(repository.getComparator());
        }
        else{
            //not sorted
            map = new HashMap<>();
        }
        for (FamilyMember familyMember : repository.findAll()) {
            map.put(familyMember, familyMember.getNumberOfApprentices());
        }
        return map;
    }
}