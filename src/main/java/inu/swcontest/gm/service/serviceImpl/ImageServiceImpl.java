package inu.swcontest.gm.service.serviceImpl;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import inu.swcontest.gm.service.ImageService;
import inu.swcontest.gm.model.UploadImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;
    @Override
    public void uploadImage(UploadImageRequest request) {
        // GCP storage client 초기화
        Storage storage = StorageOptions.getDefaultInstance().getService();

        // GCS에 저장될 파일 이름 UUID로 지정
        // 이미지 이름 foramt : gm-{originName}-{uuid}
        String originName = request.getImage().getOriginalFilename();
        String name = "gm-" + originName + "-" + UUID.randomUUID();

        // 파일 확장자(형식) ex) PNG
        String contentType = request.getImage().getContentType();

        // 이미지 정보 설정
        try {
            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, name).setContentType(contentType).build();

            // Cloud에 이미지 업로드
            storage.createFrom(blobInfo, request.getImage().getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
