package inu.swcontest.gm.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadImageRequest {

    private MultipartFile image;
}
