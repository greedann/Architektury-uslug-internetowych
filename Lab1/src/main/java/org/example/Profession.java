package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Profession implements Comparable<Profession>, Serializable {
    String name;
    int baseArmor;
    List<Character> characters;

    public Profession(String name, int baseArmor) {
        this.name = name;
        this.baseArmor = baseArmor;
        this.characters = new ArrayList<>();
    }

    public int getBaseArmor() {
        return baseArmor;
    }

    public void setBaseArmor(int baseArmor) {
        this.baseArmor = baseArmor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    @Override
    public int compareTo(Profession other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Profession{" +
                "name='" + name + '\'' +
                ", baseArmor=" + baseArmor +
                '}';
    }

    public class Builder {
        private String name;
        private int baseArmor;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBaseArmor(int baseArmor) {
            this.baseArmor = baseArmor;
            return this;
        }

        public Profession createProfession() {
            return new Profession(name, baseArmor);
        }
    }
}
