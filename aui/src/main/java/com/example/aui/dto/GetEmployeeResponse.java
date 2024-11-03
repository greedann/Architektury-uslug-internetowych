package com.example.aui.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class GetEmployeeResponse {
    private UUID id;
    private String name;
    private int level;
    private Company company;

    @Value
    @Builder
    public static class Company {
        UUID id;
        String name;
        int capitalization;
    }

}
