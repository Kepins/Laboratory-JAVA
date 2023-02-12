import character.entity.comparator.FamilyMemberByNameComparator;
import character.initialize.InitializeTestData;
import character.repository.FamilyMemberRepository;
import character.service.FamilyMemberService;
import character.view.DataList;
import view.View;

public class FamilyHierarchyApplication {

    /**
     * Main method run in program
     * @param args arguments passed refer to the mode you want to run
     *             "no-sort" - 0,
     *             "normal-sort" - 1,
     *             "alternative-sort" - 1 1
     */
    public static void main(String[] args){
        boolean sorted = args.length >= 1 && Boolean.parseBoolean(args[0]);
        boolean alternativeCriteria = args.length == 2 && Boolean.parseBoolean(args[1]);

        FamilyHierarchyApplication application = new FamilyHierarchyApplication();

        FamilyMemberService familyMemberService = application.createCharacterService(sorted, alternativeCriteria);
        InitializeTestData initializeTestData = new InitializeTestData(familyMemberService);
        initializeTestData.init(sorted, alternativeCriteria);

        View entryView = new DataList(familyMemberService);
        entryView.display();
    }

    private FamilyMemberService createCharacterService(boolean sorted, boolean alternative){
        FamilyMemberRepository repository;
        repository = sorted ?
                (alternative ? new FamilyMemberRepository(new FamilyMemberByNameComparator()) : new FamilyMemberRepository(true))
                : new FamilyMemberRepository();
        return new FamilyMemberService(repository);
    }

}
