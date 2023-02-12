package character.view;

import character.entity.FamilyMember;
import character.service.FamilyMemberService;
import view.View;


public class DataList implements View {

    private final FamilyMemberService familyMemberService;

    public DataList(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @Override
    public void display(){
        System.out.println("FamilyMembers:");
        for(FamilyMember familyMember : familyMemberService.findAllBosses()){
            displayRecursive(familyMember);
        }
        System.out.println();
        System.out.println(familyMemberService.createApprenticesStatistics());

    }

    private static String indentation = "";
    private void displayRecursive(FamilyMember mainFamilyMember){
        indentation = new String(indentation + '-');
        System.out.println(indentation + mainFamilyMember);
        for(FamilyMember familyMember : mainFamilyMember.getChildren().findAllCharacters()){
            displayRecursive(familyMember);
        }
        indentation = new String(indentation.substring(0, indentation.length() - 1));
    }

}
