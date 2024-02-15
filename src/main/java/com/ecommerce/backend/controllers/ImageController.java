package com.ecommerce.backend.controllers;

import com.ecommerce.backend.entities.Image;
import com.ecommerce.backend.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

//    @PostMapping("/upload")
//    public Optional<ImageResponseDTO> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            Image image = imageService.saveFile(file);
//
//            String url = ServletUriComponentsBuilder.
//                    fromCurrentContextPath()
//                    .path("/api/v1/download/")
//                    .path(image.getId().toString())
//                    .toUriString();
//
//            return Optional.of(ImageResponseDTO
//                    .builder()
//                    .type(image.getType())
//                    .size(file.getSize())
//                    .url(url)
//                    .build()
//            );
//
//        } catch (Exception e) {
//            return Optional.empty();
//        }
//    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        Image image = imageService.findById(fileId).orElseThrow();

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .body(new ByteArrayResource(image.getContent()))
                ;
    }
}