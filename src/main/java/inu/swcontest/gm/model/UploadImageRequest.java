package inu.swcontest.gm.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadImageRequest {

    private String email;

    private MultipartFile image;
}
