package inu.swcontest.gm.controller;


import inu.swcontest.gm.service.ImageService;
import inu.swcontest.gm.model.UploadImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.zip.ZipFile;


@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    // upload origin image
    @PostMapping("/upload/image")
    public ResponseEntity uploadImage(UploadImageRequest uploadImageRequest) {
        String imageUrl = imageService.uploadImage(uploadImageRequest);
        return ResponseEntity.ok(imageUrl);
    }

    // get zip file from model server
    // this part need a test
    @GetMapping("/return/zipFile")
    public ResponseEntity getZipFile(ZipFile zipFile) {
        return ResponseEntity.ok(zipFile);
    }
}
