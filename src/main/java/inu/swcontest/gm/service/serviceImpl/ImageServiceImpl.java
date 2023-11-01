package inu.swcontest.gm.service.serviceImpl;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import inu.swcontest.gm.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;


    // model server url
    private String url = "http://127.0.0.1:5000/";
    @Override
    public void uploadImage(MultipartFile zipFile) {
        // GCP storage client 초기화
        Storage storage = StorageOptions.getDefaultInstance().getService();

            // GCS에 저장될 파일 이름 UUID로 지정
            // 이미지 이름 foramt : gm-{originName}-{uuid}
            String originName = zipFile.getOriginalFilename();
            String name = "gm-" + originName + "-" + UUID.randomUUID();

            // 파일 확장자(형식) ex) PNG
            String contentType = zipFile.getContentType();

            // 이미지 정보 설정
            try {
                BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, name).setContentType(contentType).build();

                // Cloud에 이미지 업로드
                Blob uploadImage = storage.createFrom(blobInfo, zipFile.getInputStream());

                // 해당 image url return
                String zipUrl = uploadImage.getMediaLink();

                // send zipFile to model server
                sendZipFile(zipUrl);

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void sendZipFile(String zipUrl) {
        RestTemplate restTemplate = new RestTemplate();

        // header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // body
        Map<String, String> body = new HashMap<>();
        body.put("zip", zipUrl);

        // message
        HttpEntity<Map<String, String>> requestMessage = new HttpEntity<>(body, headers);

        // request
        restTemplate.postForObject(url + "get/url", requestMessage, void.class);

    }

    // return image zip file from model server
    @Override
    public String returnZipFile(MultipartFile zipFile, List<Float> accuracy) {

        System.out.println("returnZipFile service 들어옴");

        System.out.println("response.getZipFile().getOriginalFilename() = " + zipFile.getOriginalFilename());

        for(Float a : accuracy) {
            System.out.println("accuracy = " + a.toString());
        }

        return zipFile.getOriginalFilename();
    }


}
