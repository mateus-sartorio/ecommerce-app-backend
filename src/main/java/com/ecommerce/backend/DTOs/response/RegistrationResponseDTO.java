package com.ecommerce.backend.DTOs.response;

import lombok.Builder;

@Builder
public record RegistrationResponseDTO(
        String username,
        String email,
        String firstName,
        String lastName,
        Boolean locked,
        Boolean enabled
) {
}