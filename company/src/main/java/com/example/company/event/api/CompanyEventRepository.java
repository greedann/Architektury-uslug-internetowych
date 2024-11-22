package com.example.company.event.api;

import java.util.UUID;

public interface CompanyEventRepository {
    void create(UUID companyId);
    void delete(UUID companyId);
}
