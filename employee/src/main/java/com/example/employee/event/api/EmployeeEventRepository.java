package com.example.employee.event.api;

import java.util.UUID;

public interface EmployeeEventRepository {
    void create(UUID employeeId);
    void delete(UUID employeeId);
}
