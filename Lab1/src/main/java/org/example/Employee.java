package org.example;

import java.io.Serializable;

public class Employee implements Comparable<Employee>, Serializable {
    String name;
    int level;
    Company company;

    public Employee(String name, int level, Company company) {
        this.name = name;
        this.level = level;
        this.company = company;
        company.employees.add(this);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int compareTo(Employee other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", company=" + company +
                '}';
    }

    public class Builder {
        private String name;
        private int level;
        private Company company;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setLevel(int level) {
            this.level = level;
            return this;
        }

        public Builder setProfession(Company company) {
            this.company = company;
            return this;
        }

        public Employee createCharacter() {
            return new Employee(name, level, company);
        }
    }
}
