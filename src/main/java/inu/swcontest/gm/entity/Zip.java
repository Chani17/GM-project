package inu.swcontest.gm.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "member_email")
    private Member member;


    public static Zip createZip(String zipUrl, String projectName, Float accuracy, Float LPIPS, Float fid, Member member) {
        Zip zip = new Zip();
        zip.zipUrl = zipUrl;
        zip.projectName = projectName;
        zip.accuracy = accuracy;
        zip.LPIPS = LPIPS;
        zip.fid = fid;
        zip.member = member;
        return zip;
    }
}
