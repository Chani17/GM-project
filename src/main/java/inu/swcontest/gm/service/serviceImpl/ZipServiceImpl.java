package inu.swcontest.gm.service.serviceImpl;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import inu.swcontest.gm.entity.Member;
import inu.swcontest.gm.entity.Zip;
import inu.swcontest.gm.repository.MemberRepository;
import inu.swcontest.gm.repository.ZipRepository;
import inu.swcontest.gm.service.ZipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ZipServiceImpl implements ZipService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    private final MemberRepository memberRepository;
    private final ZipRepository zipRepository;
    private static String zipUrl;

    @Override
    public String saveZipFile(MultipartFile zipFile) {
        // GCP storage client 초기화
        Storage storage = StorageOptions.getDefaultInstance().getService();

        // GCS에 저장될 파일 이름 UUID로 지정
        // 이미지 이름 foramt : gm-new-{originName}-{uuid}
        String originName = zipFile.getOriginalFilename();
        String name = "gm-new-" + originName + "-" + UUID.randomUUID();

        // 파일 확장자(형식) ex) PNG
        String contentType = zipFile.getContentType();

        try {
            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, name).setContentType(contentType).build();

            // Cloud에 이미지 업로드
            Blob uploadImage = storage.createFrom(blobInfo, zipFile.getInputStream());

            // 해당 image url return
            zipUrl = uploadImage.getMediaLink();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipUrl;
    }

    // return image zip file from model server
    @Override
    public void returnZipFile(MultipartFile zipFile, List<Float> accuracy, String email, String projectName) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        // upload file to GCS
        String url = saveZipFile(zipFile);

        Zip zip = Zip.createZip(url, projectName, accuracy.get(0), accuracy.get(1), accuracy.get(2), member);

        // save zipUrl to DB
        zipRepository.save(zip);

    }


    @Override
    public List<Zip> getData(String email) {
        memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        List<Zip> response = zipRepository.findByMemberEmail(email);

        return response;
    }
}
