package org.example;

import java.io.Serializable;

public class Character implements Comparable<Character>, Serializable {
    String name;
    int level;
    Profession profession;

    public Character(String name, int level, Profession profession) {
        this.name = name;
        this.level = level;
        this.profession = profession;
        profession.characters.add(this);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int compareTo(Character other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", profession=" + profession +
                '}';
    }

    public class Builder {
        private String name;
        private int level;
        private Profession profession;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setLevel(int level) {
            this.level = level;
            return this;
        }

        public Builder setProfession(Profession profession) {
            this.profession = profession;
            return this;
        }

        public Character createCharacter() {
            return new Character(name, level, profession);
        }
    }
}
