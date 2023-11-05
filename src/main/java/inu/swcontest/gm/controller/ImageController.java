package inu.swcontest.gm.controller;


import inu.swcontest.gm.model.ZipFileResponse;
import inu.swcontest.gm.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.zip.ZipFile;


@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    // upload origin zipFile
    @PostMapping("/upload/images/{email}")
    public ResponseEntity<String> uploadImage(@PathVariable String email, @RequestParam("zip") MultipartFile zipFile) {
        imageService.uploadImage(email, zipFile);
        return ResponseEntity.ok("이미지 저장 및 전송 완료");
    }





}
