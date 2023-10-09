package com.example.fittracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    @Autowired
    IImageService imageService;
    public String create(MultipartFile file) {
        String imageUrl= null;
//        for (MultipartFile file : files) {

        try {

            String fileName = imageService.save(file);

            imageUrl = imageService.getImageUrl(fileName);
            System.out.println(imageUrl);

            // do whatever you want with that

        } catch (Exception e) {
            e.printStackTrace();
            //  throw internal error;
        }
//        }

        return imageUrl;
    }
    public ResponseEntity create(@RequestParam(name = "file") MultipartFile[] files) {

        for (MultipartFile file : files) {

            try {

                String fileName = imageService.save(file);

                String imageUrl = imageService.getImageUrl(fileName);

                // do whatever you want with that

            } catch (Exception e) {
                //  throw internal error;
            }
        }

        return ResponseEntity.ok().build();
    }
}
