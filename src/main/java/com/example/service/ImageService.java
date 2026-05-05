package com.example.service;

import com.example.util.ImageProcessingUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class ImageService {

    public String processWithFilters(MultipartFile file, List<String> filters) throws Exception {

        File tempFile = File.createTempFile("upload", file.getOriginalFilename());
        file.transferTo(tempFile);

        int[][] image = ImageProcessingUtil.imgToTwoD(tempFile.getAbsolutePath());

        if (image == null) {
            return "Failed to read image";
        }

        for (String filter : filters) {
            switch (filter.toLowerCase()) {

                case "negative":
                    image = ImageProcessingUtil.negativeColor(image);
                    break;

                case "trim":
                    image = ImageProcessingUtil.trimBorders(image, 20);
                    break;

                case "invert":
                    image = ImageProcessingUtil.invertImage(image);
                    break;

                default:
                    return "Unknown filter: " + filter;
            }
        }

        ImageProcessingUtil.twoDToImage(image, "output/processed.jpg");

        return "Image processed successfully with filters: " + filters;
    }
}