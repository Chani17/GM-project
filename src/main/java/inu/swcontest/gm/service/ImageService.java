package inu.swcontest.gm.service;

import inu.swcontest.gm.model.UploadImageRequest;
import inu.swcontest.gm.model.ZipFileResponse;

import java.util.zip.ZipFile;

public interface ImageService {
    String uploadImage(UploadImageRequest request);

    ZipFile sendImage(String imageUrl);
}
