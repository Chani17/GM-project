package inu.swcontest.gm.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Zip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zip_seq")
    private Long id;

    private String projectName;

    private String zipUrl;

    @ElementCollection
    private List<Float> accuracy_generated;

    @ElementCollection
    private List<Float> accuracy_original_generated;

    // Learned Perceptual Image
    @ElementCollection
    private List<Float> LPIPS;

    // fidelity
    @ElementCollection
    private List<Float> fid;

    private String loss;

    private String generated_gif_url;

    private String generated_single_img;

    private String createdDate;

    @ManyToOne
    @JoinColumn(name = "member_email")
    private Member member;


    public static Zip createZip(String zipUrl, String projectName, List<Float> accuracy_generated,
                                List<Float> accuracy_original_generated, List<Float> LPIPS, List<Float> fid,
                                String loss, String generated_gif_url, String generated_single_img, Member member) {
        Zip zip = new Zip();
        zip.zipUrl = zipUrl;
        zip.projectName = projectName;
        zip.accuracy_generated = accuracy_generated;
        zip.accuracy_original_generated = accuracy_original_generated;
        zip.LPIPS = LPIPS;
        zip.fid = fid;
        zip.loss = loss;
        zip.generated_gif_url = generated_gif_url;
        zip.generated_single_img = generated_single_img;
        zip.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        zip.member = member;
        return zip;
    }
}
