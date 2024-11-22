package com.example.company.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateOrUpdateCompany {
    private String name;
    private int capitalization;
}
