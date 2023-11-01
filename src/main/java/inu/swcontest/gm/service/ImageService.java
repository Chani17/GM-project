package inu.swcontest.gm.service;

import inu.swcontest.gm.model.ZipFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipFile;

public interface ImageService {
    void uploadImage(MultipartFile zipFile);

    void sendZipFile(String zipFile) throws IOException;

    String returnZipFile(MultipartFile zipFile, List<Float> accuracy);

}
