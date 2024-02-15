package com.ecommerce.backend.DTOs.response;

import lombok.Builder;

@Builder
public record ImageResponseDTO(String url, String type, Long size) {
}