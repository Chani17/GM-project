package inu.swcontest.gm.service.serviceImpl;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import inu.swcontest.gm.entity.Member;
import inu.swcontest.gm.entity.MemberStatus;
import inu.swcontest.gm.repository.MemberRepository;
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
import java.util.Map;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    private final MemberRepository memberRepository;

    // model server url
    private static final String URL = "http://192.168.18.173:5000/";

    @Override
    public void uploadImage(String email, String projectName, MultipartFile zipFile) {

        // member 로그인 확인하는 logic 필요함
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("가입되어 있지 않은 회원입니다."));

        // update member status -> GENERATING
        member.updateMemberStatus();

        // GCP storage client 초기화
        Storage storage = StorageOptions.getDefaultInstance().getService();

            // GCS에 저장될 파일 이름 UUID로 지정
            // 이미지 이름 foramt : gm-original-{originName}-{uuid}
            String originName = zipFile.getOriginalFilename();
            String name = "gm-original-" + originName + "-" + UUID.randomUUID();

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
                sendZipFile(email, projectName, zipUrl);

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void sendZipFile(String email, String projectName, String zipUrl) {
        RestTemplate restTemplate = new RestTemplate();

        // header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // body
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("projectName", projectName);
        body.put("zipUrl", zipUrl);

        // message
        HttpEntity<Map<String, String>> requestMessage = new HttpEntity<>(body, headers);

        // request
        restTemplate.postForObject(URL + "get/url", requestMessage, void.class);

    }




}
