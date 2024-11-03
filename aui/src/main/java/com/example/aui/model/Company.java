package com.example.aui.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "companies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Comparable<Company>, Serializable {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "capitalization")
    private int capitalization;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    @Override
    public int compareTo(Company company) {
        return this.name.compareTo(company.getName());
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capitalization=" + capitalization +
                '}';
    }

}
