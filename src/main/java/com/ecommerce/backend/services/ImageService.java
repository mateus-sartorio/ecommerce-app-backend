package com.ecommerce.backend.services;

import com.ecommerce.backend.entities.Image;
import com.ecommerce.backend.repositories.ImageRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public Image saveFile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getName());

        try {
            if (fileName.contains("..")) {
                throw new InvalidFileNameException(fileName, "invalid");
            }

            Image image = Image
                    .builder()
                    .type(file.getContentType())
                    .content(file.getBytes())
                    .build();

            return imageRepository.save(image);
        } catch (Exception e) {
            throw new Exception("Could not save file");
        }
    }

    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }
}