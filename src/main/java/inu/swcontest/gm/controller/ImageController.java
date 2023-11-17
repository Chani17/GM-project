package inu.swcontest.gm.controller;


import inu.swcontest.gm.model.ZipFileResponse;
import inu.swcontest.gm.service.ImageService;
import inu.swcontest.gm.service.MemberService;
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

    private final MemberService memberService;


    // upload origin zipFile
    @PostMapping("/upload/images/{email}")
    public void uploadImage(@PathVariable String email, String projectName, @RequestParam("zip") MultipartFile zipFile) {
        if(memberService.updateMemberStatus(email)) {
            imageService.uploadImage(email, projectName, zipFile);
        } else {
            throw new IllegalStateException("다시 로그인해주세요.");
        }
    }





}
