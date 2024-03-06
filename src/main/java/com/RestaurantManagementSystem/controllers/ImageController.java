package com.RestaurantManagementSystem.controllers;


import com.RestaurantManagementSystem.models.ImageModel;
import com.RestaurantManagementSystem.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;

    @GetMapping("/image/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        ImageModel image = imageRepository.findById(id).orElseThrow();
        return ResponseEntity.ok()
                .header("fileId", String.valueOf(image.getId()))
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getImgBinary().length)
                .body(new InputStreamResource(new ByteArrayInputStream(image.getImgBinary())));
    }
}
