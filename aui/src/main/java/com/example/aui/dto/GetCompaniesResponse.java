package com.example.aui.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class GetCompaniesResponse {
    List<Company> companies;

    @Value
    @Builder
    public static class Company {
        private UUID id;
        private String name;
        private int capitalization;
    }

}
