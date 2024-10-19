package org.example;

public class EmployeeDto implements Comparable<EmployeeDto> {
    String name;
    int level;
    String company;

    public EmployeeDto(String name, int level, String company) {
        this.name = name;
        this.level = level;
        this.company = company;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", company='" + company + '\'' +
                '}';
    }

    @Override
    public int compareTo(EmployeeDto other) {
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

        public EmployeeDto createCharacterDto() {
            return new EmployeeDto(name, level, profession);
        }
    }
}
