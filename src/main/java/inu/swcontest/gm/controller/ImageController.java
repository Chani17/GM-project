package inu.swcontest.gm.controller;


import inu.swcontest.gm.service.ImageService;
import inu.swcontest.gm.model.UploadImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    // upload origin image
    @PostMapping("/upload/image")
    public ResponseEntity uploadImage(UploadImageRequest uploadImageRequest) {
        imageService.uploadImage(uploadImageRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
