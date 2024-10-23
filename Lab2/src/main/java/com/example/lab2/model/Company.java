package com.example.lab2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "companies")
@Builder
@NoArgsConstructor
public class Company implements Comparable<Company>, Serializable {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "capitalization")
    private int capitalization;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employees;

    public Company(String name, int capitalization) {
        this.name = name;
        this.capitalization = capitalization;
        this.employees = new ArrayList<>();
    }

    public Company(UUID id, String name, int capitalization, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.capitalization = capitalization;
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capitalization=" + capitalization +
                '}';
    }

    @Override
    public int compareTo(Company other) {
        return Integer.compare(this.capitalization, other.capitalization);
    }
}
