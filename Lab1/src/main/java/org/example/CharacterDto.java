package org.example;

public class CharacterDto implements Comparable<CharacterDto> {
    String name;
    int level;
    String profession;

    public CharacterDto(String name, int level, String profession) {
        this.name = name;
        this.level = level;
        this.profession = profession;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "CharacterDto{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", profession='" + profession + '\'' +
                '}';
    }

    @Override
    public int compareTo(CharacterDto other) {
        return this.name.compareTo(other.name);
    }

    public class Builder {
        private String name;
        private int level;
        private String profession;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setLevel(int level) {
            this.level = level;
            return this;
        }

        public Builder setProfession(String profession) {
            this.profession = profession;
            return this;
        }

        public CharacterDto createCharacterDto() {
            return new CharacterDto(name, level, profession);
        }
    }
}
