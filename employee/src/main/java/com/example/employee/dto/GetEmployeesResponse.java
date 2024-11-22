package com.example.employee.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetEmployeesResponse {
    List<Employee> employees;

    @Value
    @Builder
    public static class Employee {
        private UUID id;
        private String name;
        private int level;
        private String companyName;
    }
}
