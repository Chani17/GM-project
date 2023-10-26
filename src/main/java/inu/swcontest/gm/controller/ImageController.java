package inu.swcontest.gm.controller;


import inu.swcontest.gm.model.ZipFileResponse;
import inu.swcontest.gm.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.zip.ZipFile;


@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    // upload origin image
    @PostMapping("/upload/image")
    public ResponseEntity<String> uploadImage(@RequestParam("zipFile") MultipartFile zipFile) {
        System.out.println("uploadImageRequest.getImage().getName() = " + zipFile.getName());
        imageService.uploadImage(zipFile);
        return ResponseEntity.ok("이미지 저장 및 전송 완료");
    }


    // get zip file from model server
    // this part need a test
    @GetMapping("/return/zipFile")
    public ZipFileResponse getZipFile(ZipFile zipFile) {
        ZipFileResponse zipFileResponse = imageService.returnZipFile(zipFile);
        return zipFileResponse;
    }
}
