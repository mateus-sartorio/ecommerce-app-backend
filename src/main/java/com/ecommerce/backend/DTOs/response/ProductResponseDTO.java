package com.ecommerce.backend.DTOs.response;

import com.ecommerce.backend.entities.Image;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductResponseDTO(Long id, String title, String description, BigDecimal price, ImageResponseDTO cover,
                                 List<ImageResponseDTO> images) {
}
