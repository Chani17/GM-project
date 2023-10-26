package inu.swcontest.gm.controller;


import inu.swcontest.gm.service.ImageService;
import inu.swcontest.gm.model.UploadImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;


@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    // upload origin image
    @PostMapping("/upload/image")
    public List<String> uploadImage(@RequestParam("image") List<MultipartFile> image) {
        System.out.println("uploadImageRequest.getImage().getName() = " + image.stream().collect(Collectors.toList()));
        List<String> imageUrl = imageService.uploadImage(image);
        return imageUrl;
    }


    // get zip file from model server
    // this part need a test
    @GetMapping("/return/zipFile")
    public ResponseEntity getZipFile(ZipFile zipFile) {
        return ResponseEntity.ok(zipFile);
    }
}
