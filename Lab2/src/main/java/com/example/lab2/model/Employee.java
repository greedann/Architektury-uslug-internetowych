package com.example.lab2.model;

import java.io.Serializable;
import java.util.UUID;
import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "employees")
@Builder
@NoArgsConstructor
public class Employee implements Comparable<Employee>, Serializable {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Setter
    @Column(name = "level")
    private int level;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    public Employee(String name, int level, Company company) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.level = level;
        this.company = company;
        company.addEmployee(this);
    }

    public Employee(UUID id, String name, int level, Company company) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.company = company;
        company.addEmployee(this);
    }

    @Override
    public int compareTo(Employee other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", company=" + company +
                '}';
    }
}
