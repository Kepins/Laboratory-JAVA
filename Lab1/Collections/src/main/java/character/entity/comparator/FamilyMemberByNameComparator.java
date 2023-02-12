package character.entity.comparator;

import character.entity.FamilyMember;

import java.util.Comparator;

public class FamilyMemberByNameComparator implements Comparator<FamilyMember> {

    @Override
    public int compare(FamilyMember m1, FamilyMember m2) {

        int ret = m1.getName().compareTo(m2.getName());
        if(ret ==0){
            ret = Double.compare(m1.getHeight(), m2.getHeight());
        }
        if(ret ==0){
            ret = m1.getAge() - m2.getAge();
        }

        return ret;
    }
}
