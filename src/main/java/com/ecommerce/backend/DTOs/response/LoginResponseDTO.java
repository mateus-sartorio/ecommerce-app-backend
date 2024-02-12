package com.ecommerce.backend.DTOs.response;

import lombok.Builder;

@Builder
public record LoginResponseDTO(
        UserResponseDTO user,
        String jwt
) {
}
