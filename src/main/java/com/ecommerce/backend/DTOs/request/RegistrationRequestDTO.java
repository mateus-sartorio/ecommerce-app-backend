package com.ecommerce.backend.DTOs.request;

import lombok.Builder;

@Builder
public record RegistrationRequestDTO(
        String username,
        String email,
        String firstName,
        String lastName,
        String password
) {
}
