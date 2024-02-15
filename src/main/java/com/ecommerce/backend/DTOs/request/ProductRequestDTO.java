package com.ecommerce.backend.DTOs.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequestDTO(String title, String description, BigDecimal price) {
}