package com.example.aui.function;

import java.util.UUID;
import java.util.function.BiFunction;

import org.springframework.stereotype.Component;

import com.example.aui.dto.CreateOrUpdateEmployee;
import com.example.aui.model.Company;
import com.example.aui.model.Employee;

@Component
public class RequestToEmployeeFunction implements BiFunction<UUID, CreateOrUpdateEmployee, Employee> {

    @Override
    public Employee apply(UUID companyUUID, CreateOrUpdateEmployee createOrUpdateEmployee) {
        return Employee.builder()
                .name(createOrUpdateEmployee.getName())
                .company(Company.builder().id(companyUUID).build())
                .id(UUID.randomUUID())
                .level(createOrUpdateEmployee.getLevel())
                .build();
    }

}
