package inu.swcontest.gm.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private Float accuracy;

    // Learned Perceptual Image
    private Float LPIPS;

    // fidelity
    private Float fid;

    private String createdDate;

    @ManyToOne
    @JoinColumn(name = "email")
    private Member member;


    public static Zip createZip(String zipUrl, String projectName, Float accuracy, Float LPIPS, Float fid, Member member) {
        Zip zip = new Zip();
        zip.zipUrl = zipUrl;
        zip.projectName = projectName;
        zip.accuracy = accuracy;
        zip.LPIPS = LPIPS;
        zip.fid = fid;
        zip.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        zip.member = member;
        return zip;
    }
}
