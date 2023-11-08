package inu.swcontest.gm.service;

import inu.swcontest.gm.model.DashboardResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ZipService {
    String saveFile(MultipartFile zipFile);

    void returnZipFile(MultipartFile zipFile, List<Float> accuracy_generated, List<Float> accuracy_original_generaged,
                       List<Float> LPIPS, List<Float> fid, MultipartFile loss, MultipartFile generated_gif,
                       MultipartFile generated_single_img, String email, String projectName);

    List<DashboardResponse> getData(String email);
}
