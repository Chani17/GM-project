package inu.swcontest.gm.service;

import inu.swcontest.gm.model.UploadImageRequest;
import inu.swcontest.gm.model.ZipFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.zip.ZipFile;

public interface ImageService {
    String uploadImage(List<MultipartFile> image);

    ZipFile sendImage(String imageUrl);
}
