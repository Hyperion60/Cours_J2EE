package com.rioc.cours_j2ee.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class AccountDto {
    private String firstName;
    private String lastName;
}
