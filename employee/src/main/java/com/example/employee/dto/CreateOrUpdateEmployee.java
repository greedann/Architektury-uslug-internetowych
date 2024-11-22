package com.example.employee.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateOrUpdateEmployee {
    private String name;
    private int level;
}
