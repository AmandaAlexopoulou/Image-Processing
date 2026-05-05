package com.example.controller;

import com.example.dto.FilterRequest;
import com.example.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/process")
    public String process(
            @RequestParam("file") MultipartFile file,
            @RequestBody FilterRequest request
    ) throws Exception {

        return imageService.processWithFilters(file, request.getFilters());
    }
}