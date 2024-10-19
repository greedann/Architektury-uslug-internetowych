package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Company implements Comparable<Company>, Serializable {
    String name;
    int capitalization;
    List<Employee> employees;

    public Company(String name, int Capitalization) {
        this.name = name;
        this.capitalization = Capitalization;
        this.employees = new ArrayList<>();
    }

    public int getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(int capitalization) {
        this.capitalization = capitalization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getCharacters() {
        return employees;
    }

    @Override
    public int compareTo(Company other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", capitalization=" + capitalization +
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

        public Company createProfession() {
            return new Company(name, baseArmor);
        }
    }
}
