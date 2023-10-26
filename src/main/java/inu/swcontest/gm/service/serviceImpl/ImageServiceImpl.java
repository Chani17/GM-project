package inu.swcontest.gm.service.serviceImpl;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import inu.swcontest.gm.model.ZipFileResponse;
import inu.swcontest.gm.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.zip.ZipFile;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    // model server url
    private String url = "http://127.0.0.1:8000/";

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
                uploadImage.getMediaLink();

                // send zipFile to model server
                sendZipFile(zipFile);

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void sendZipFile(MultipartFile zipFile) throws IOException {

        RestTemplate restTemplate = new RestTemplate();

        // header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        ByteArrayResource resource = new ByteArrayResource(zipFile.getBytes()){
            @Override
            public String getFilename() {
                return zipFile.getOriginalFilename();
            }
        };
        body.add("zipFile", resource);

        // message
        HttpEntity<MultiValueMap<String, Object>> requestMessage = new HttpEntity<>(body, headers);

        // request
        restTemplate.postForObject(url + "get/url", requestMessage, ZipFileResponse.class);

    }

    // return image zip file from model server
    @Override
    public ZipFileResponse returnZipFile(MultipartFile zipFile) {
        RestTemplate restTemplate = new RestTemplate();

        ZipFileResponse response = restTemplate.getForObject(url, ZipFileResponse.class, zipFile);
        System.out.println("response = " + response.toString());

        return response;
    }
}
