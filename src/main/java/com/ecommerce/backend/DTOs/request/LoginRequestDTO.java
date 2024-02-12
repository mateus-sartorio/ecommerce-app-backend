package com.ecommerce.backend.DTOs.request;

import lombok.Builder;

@Builder
public record LoginRequestDTO(String username, String password) {
}
