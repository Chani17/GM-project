package inu.swcontest.gm.service;

import inu.swcontest.gm.entity.Zip;
import inu.swcontest.gm.model.ZipFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ZipService {
    String saveZipFile(MultipartFile zipFile);

    void returnZipFile(MultipartFile zipFile, List<Float> accuracy, String email, String projectName);

    List<Zip> getData(String email);
}
