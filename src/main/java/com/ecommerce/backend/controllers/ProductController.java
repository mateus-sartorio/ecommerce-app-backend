package com.ecommerce.backend.controllers;

import com.ecommerce.backend.DTOs.response.ImageResponseDTO;
import com.ecommerce.backend.DTOs.response.ProductResponseDTO;
import com.ecommerce.backend.entities.Image;
import com.ecommerce.backend.entities.Product;
import com.ecommerce.backend.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin("*")
@Transactional
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();

            String baseURL = ServletUriComponentsBuilder.
                    fromCurrentContextPath()
                    .path("/api/v1/image/download/")
                    .toUriString();

            List<ProductResponseDTO> responseBody = new ArrayList<>();

            for (Product product : products) {
                ImageResponseDTO coverResponse = ImageResponseDTO
                        .builder()
                        .url(baseURL + product.getCover().getId())
                        .type(product.getCover().getType())
                        .size(product.getCover().getSize())
                        .build();

                List<ImageResponseDTO> imagesResponse = product.getImages()
                        .stream()
                        .map(img -> ImageResponseDTO
                                .builder()
                                .url(baseURL + img.getId())
                                .type(img.getType())
                                .size(img.getSize())
                                .build())
                        .toList();

                ProductResponseDTO responseProduct = ProductResponseDTO
                        .builder()
                        .id(product.getId())
                        .title(product.getTitle())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .cover(coverResponse)
                        .images(imagesResponse)
                        .build();

                responseBody.add(responseProduct);
            }

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseBody);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Product not found");
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId).orElseThrow();

            String baseURL = ServletUriComponentsBuilder.
                    fromCurrentContextPath()
                    .path("/api/v1/image/download/")
                    .toUriString();

            ImageResponseDTO coverResponse = ImageResponseDTO
                    .builder()
                    .url(baseURL + product.getCover().getId())
                    .type(product.getCover().getType())
                    .size(product.getCover().getSize())
                    .build();

            List<ImageResponseDTO> imagesResponse = product.getImages()
                    .stream()
                    .map(img -> ImageResponseDTO
                            .builder()
                            .url(baseURL + img.getId())
                            .type(img.getType())
                            .size(img.getSize())
                            .build())
                    .toList();

            ProductResponseDTO responseBody = ProductResponseDTO
                    .builder()
                    .id(product.getId())
                    .title(product.getTitle())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .cover(coverResponse)
                    .images(imagesResponse)
                    .build();

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseBody);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Product not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("price") BigDecimal price, @RequestParam("cover") MultipartFile cover, @RequestParam("images") MultipartFile[] images) {
        try {
            Product product = Product
                    .builder()
                    .title(title)
                    .description(description)
                    .price(price)
                    .build();

            Image processedCover = Image
                    .builder()
                    .type(cover.getContentType())
                    .size(cover.getSize())
                    .product(product)
                    .content(cover.getBytes())
                    .build();

            List<Image> imagesObjects = Arrays.stream(images).map(img -> {
                        try {
                            return Image
                                    .builder()
                                    .type(img.getContentType())
                                    .size(cover.getSize())
                                    .product(product)
                                    .content(img.getBytes())
                                    .build();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            ).toList();

            product.setCover(processedCover);
            product.setImages(imagesObjects);

            Product savedProduct = productService.saveProduct(product);

            String baseURL = ServletUriComponentsBuilder.
                    fromCurrentContextPath()
                    .path("/api/v1/image/download/")
                    .toUriString();

            ImageResponseDTO coverResponse = ImageResponseDTO
                    .builder()
                    .url(baseURL + savedProduct.getCover().getId())
                    .type(savedProduct.getCover().getType())
                    .size(savedProduct.getCover().getSize())
                    .build();

            List<ImageResponseDTO> imagesResponse = savedProduct.getImages()
                    .stream()
                    .map(img -> ImageResponseDTO
                            .builder()
                            .url(baseURL + img.getId())
                            .type(img.getType())
                            .size(img.getSize())
                            .build())
                    .toList();

            ProductResponseDTO responseBody = ProductResponseDTO
                    .builder()
                    .id(savedProduct.getId())
                    .title(savedProduct.getTitle())
                    .description(savedProduct.getDescription())
                    .price(savedProduct.getPrice())
                    .cover(coverResponse)
                    .images(imagesResponse)
                    .build();

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseBody);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the request: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/{productId}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestParam(value = "title", required = false) String title, @RequestParam(value = "description", required = false) String description, @RequestParam(value = "price", required = false) BigDecimal price, @RequestParam(value = "cover", required = false) MultipartFile cover, @RequestParam(value = "images", required = false) MultipartFile[] images) {
        try {
            Product product = productService.getProductById(productId).orElseThrow();

            if (title != null) {
                product.setTitle(title);
            }

            if (description != null) {
                product.setDescription(description);
            }

            if (price != null) {
                product.setPrice(price);
            }

            if (cover != null) {
                Image processedCover = Image
                        .builder()
                        .type(cover.getContentType())
                        .size(cover.getSize())
                        .product(product)
                        .content(cover.getBytes())
                        .build();

                product.setCover(processedCover);
            }

            if (images != null && images.length > 0) {
                List<Image> processedImages = Arrays.stream(images).map(img -> {
                            try {
                                return Image
                                        .builder()
                                        .type(img.getContentType())
                                        .size(img.getSize())
                                        .product(product)
                                        .content(img.getBytes())
                                        .build();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).toList();

                product.setImages(processedImages);
            }

            Product savedProduct = productService.saveProduct(product);

            String baseURL = ServletUriComponentsBuilder.
                    fromCurrentContextPath()
                    .path("/api/v1/image/download/")
                    .toUriString();

            ImageResponseDTO coverResponse = ImageResponseDTO
                    .builder()
                    .url(baseURL + savedProduct.getCover().getId())
                    .type(savedProduct.getCover().getType())
                    .size(savedProduct.getCover().getSize())
                    .build();

            List<ImageResponseDTO> imagesResponse = savedProduct.getImages()
                    .stream()
                    .map(img -> ImageResponseDTO
                            .builder()
                            .url(baseURL + img.getId())
                            .type(img.getType())
                            .size(img.getSize())
                            .build())
                    .toList();

            ProductResponseDTO responseBody = ProductResponseDTO
                    .builder()
                    .id(savedProduct.getId())
                    .title(savedProduct.getTitle())
                    .description(savedProduct.getDescription())
                    .price(savedProduct.getPrice())
                    .cover(coverResponse)
                    .images(imagesResponse)
                    .build();

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseBody);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the request: " + e.getMessage());
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Product deleted successfully.");
    }
}