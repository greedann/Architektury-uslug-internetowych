package com.example.aui.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateOrUpdateCompany {
    private String name;
    private int capitalization;
}
