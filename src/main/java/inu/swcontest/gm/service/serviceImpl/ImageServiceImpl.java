package inu.swcontest.gm.service.serviceImpl;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import inu.swcontest.gm.model.ZipFileResponse;
import inu.swcontest.gm.service.ImageService;
import inu.swcontest.gm.model.UploadImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.UUID;
import java.util.zip.ZipFile;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Override
    public String uploadImage(UploadImageRequest request) {
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
            Blob uploadImage = storage.createFrom(blobInfo, request.getImage().getInputStream());

            // 해당 image url return
            String imageUrl = uploadImage.getMediaLink();

            return imageUrl;

        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public ZipFile sendImage(String imageUrl) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8080/";

        // header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("imageUrl", imageUrl);

        // message
        HttpEntity<MultiValueMap<String, String>> requestMessage = new HttpEntity<>(body, headers);

        // request
        ZipFileResponse zipFileResponse = restTemplate.postForObject(url, requestMessage, ZipFileResponse.class);

        return zipFileResponse.getZipFile();

    }
}
