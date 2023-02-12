package character.entity;

import character.entity.Mage;

import javax.persistence.*;

import java.util.List;

@Entity
public class Tower {
    @Id
    private String name;

    private int height;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tower")
    private List<Mage> mages;

    public Tower() {
    }

    public Tower(String name, int height, List<Mage> mages) {
        this.name = name;
        this.height = height;
        this.mages = mages;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Mage> getMages() {
        return mages;
    }

    public void setMages(List<Mage> mages) {
        this.mages = mages;
    }

    public void addMage(Mage mage) { this.mages.add(mage); }

    @Override
    public String toString() {
        return "Tower{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}
