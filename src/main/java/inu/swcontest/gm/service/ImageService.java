package inu.swcontest.gm.service;

import inu.swcontest.gm.model.ZipFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.ZipFile;

public interface ImageService {
    void uploadImage(MultipartFile zipFile);

    void sendZipFile(String zipFile) throws IOException;

    ZipFileResponse returnZipFile(MultipartFile zipFile);
}
