package inu.swcontest.gm.Service;

import inu.swcontest.gm.model.UploadImageRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void uploadImage(UploadImageRequest request);
}
