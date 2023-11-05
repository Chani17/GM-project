package inu.swcontest.gm.controller;

import inu.swcontest.gm.entity.Zip;
import inu.swcontest.gm.model.ZipFileResponse;
import inu.swcontest.gm.service.ZipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ZipController {

    private final ZipService zipService;

    // get zip file from model server
    @PostMapping("/zips")
    public ResponseEntity getZipFile(@RequestParam("zipFile") MultipartFile zipFile, @RequestParam("accuracy") List<Float> accuracy, String email, String projectName) {
        zipService.returnZipFile(zipFile, accuracy, email, projectName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // get zip file, accuracy list for rendering
    @GetMapping("/zips/{email}")
    public List<Zip> getData(@PathVariable String email) {
        List<Zip> response = zipService.getData(email);
        return response;
    }
}
