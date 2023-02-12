package character.entity;

import character.entity.comparator.FamilyMemberByNameComparator;
import character.repository.FamilyMemberRepository;
import character.service.FamilyMemberService;

import java.util.Objects;

public class FamilyMember implements Comparable<FamilyMember> {
    private String name;
    private int age;
    private double height;
    private FamilyMemberService children;

    public FamilyMember(String name, int level, double height, boolean sorted, boolean alternative) {
        this.name = name;
        this.age = level;
        this.height = height;

        FamilyMemberRepository repository;
        repository = sorted ?
                (alternative ? new FamilyMemberRepository(new FamilyMemberByNameComparator()) : new FamilyMemberRepository(true))
                : new FamilyMemberRepository();
        this.children = new FamilyMemberService(repository);

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public FamilyMemberService getChildren() {
        return children;
    }

    public int getNumberOfApprentices(){
        int ret = 0;
        for(FamilyMember familyMember : children.findAllCharacters()){
            ret+= familyMember.getNumberOfApprentices() + 1;
        }
        return ret;
    }

    public void addChildren(FamilyMember child){
        children.create(child);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyMember familyMember = (FamilyMember) o;
        return age == familyMember.age && Double.compare(familyMember.height, height) == 0 && Objects.equals(name, familyMember.name) && Objects.equals(children, familyMember.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, height, children);
    }

    @Override
    public int compareTo(FamilyMember other) {
        int ret = age - other.age;
        if (ret == 0) {
            ret = name.compareTo(other.name);
        }
        if (ret == 0) {
            ret = Double.compare(height, other.height);
        }
        return ret;
    }


    @Override
    public String toString() {
        return "FamilyMember{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
