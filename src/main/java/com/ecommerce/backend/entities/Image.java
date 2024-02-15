package com.ecommerce.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "images")
public class Image {
    @Id
    @SequenceGenerator(
            name = "image_sequence",
            sequenceName = "image_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "image_sequence"
    )
    private Long id;

    private String type;

    private Long size;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Lob
    private byte[] content;
}
