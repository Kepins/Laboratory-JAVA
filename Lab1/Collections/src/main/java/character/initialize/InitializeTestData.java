package character.initialize;

import character.entity.FamilyMember;
import character.service.FamilyMemberService;

/**
 * Create test data and save it to repositories.
 */
public class InitializeTestData {

    /**
     * Service for {@link FamilyMember}.
     */
    private final FamilyMemberService familyMemberService;

    /**
     * @param familyMemberService service for {@link FamilyMember}
     */
    public InitializeTestData(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    /**
     * Initialize test data. Should be called after dependency injection.
     */
    public void init(boolean sorted, boolean alternative) {
        FamilyMember zenek = new FamilyMember("Zenek", 30, 170.0, sorted, alternative);
        FamilyMember zenek2 = new FamilyMember("Zenek", 40, 178.0, sorted, alternative);
        FamilyMember zenek3 = new FamilyMember("Zenek", 22, 176.0, sorted, alternative);
        FamilyMember ewa = new FamilyMember("Ewa", 22, 176.2, sorted, alternative);
        FamilyMember magda = new FamilyMember("Magda", 19,  182.2, sorted, alternative);
        FamilyMember marcin = new FamilyMember("Marcin", 18, 183.1, sorted, alternative);
        FamilyMember marek = new FamilyMember("Marek", 20, 168.0, sorted, alternative);
        FamilyMember anna = new FamilyMember("Anna", 30, 168.0, sorted, alternative);
        FamilyMember anna2 = new FamilyMember("Anna", 39, 180.0, sorted, alternative);
        FamilyMember jan = new FamilyMember("Jan", 25, 162.5, sorted, alternative);
        FamilyMember szymon = new FamilyMember("Szymon", 57, 193.0, sorted, alternative);

        ewa.addChildren(zenek);
            zenek.addChildren(marek);
            zenek.addChildren(szymon);
        ewa.addChildren(marcin);
        marcin.addChildren(anna);
            anna.addChildren(jan);
        zenek3.addChildren(magda);

        familyMemberService.create(zenek);
        familyMemberService.create(zenek2);
        familyMemberService.create(ewa);
        familyMemberService.create(magda);
        familyMemberService.create(marcin);
        familyMemberService.create(marek);
        familyMemberService.create(anna);
        familyMemberService.create(anna2);
        familyMemberService.create(jan);
        familyMemberService.create(szymon);
        familyMemberService.create(zenek3);
    }
}