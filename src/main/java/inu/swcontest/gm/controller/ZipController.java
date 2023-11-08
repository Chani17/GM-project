package inu.swcontest.gm.controller;

import inu.swcontest.gm.entity.Zip;
import inu.swcontest.gm.model.DashboardResponse;
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
@CrossOrigin("http://localhost:3000")
public class ZipController {

    private final ZipService zipService;

    // get zip file from model server
    @PostMapping("/zips")
    public ResponseEntity getZipFile(@RequestParam("zipFile") MultipartFile zipFile,
                                     @RequestParam("accuracy_generated") List<Float> accuracy_generated,
                                     @RequestParam("accuracy_original_generated") List<Float> accuracy_original_generated,
                                     @RequestParam("LPIPS") List<Float> LPIPS,
                                     @RequestParam("fid") List<Float> fid,
                                     @RequestParam("loss") MultipartFile loss,
                                     @RequestParam("generated_gif") MultipartFile generated_gif,
                                     @RequestParam("generated_single_img") MultipartFile generated_single_img,
                                     String email, String projectName) {
        zipService.returnZipFile(zipFile, accuracy_generated, accuracy_original_generated, LPIPS, fid, loss, generated_gif, generated_single_img, email, projectName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // get zip file, accuracy list for rendering
    @GetMapping("/zips/{email}")
    public List<DashboardResponse> getData(@PathVariable String email) {
        List<DashboardResponse> response = zipService.getData(email);
        return response;
    }
}
