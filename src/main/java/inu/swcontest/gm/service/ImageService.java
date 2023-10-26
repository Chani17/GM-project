package inu.swcontest.gm.service;

import inu.swcontest.gm.model.UploadImageRequest;
import inu.swcontest.gm.model.ZipFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.zip.ZipFile;

public interface ImageService {
    void uploadImage(MultipartFile zipFile);

    void sendImage(MultipartFile zipFile);

    ZipFileResponse returnZipFile(ZipFile zipFile);
}
