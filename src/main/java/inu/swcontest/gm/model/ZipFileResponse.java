package inu.swcontest.gm.model;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
public class ZipFileResponse {

    private MultipartFile zipFile;

    private List<Float> accuracy;
}
