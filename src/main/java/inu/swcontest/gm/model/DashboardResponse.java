package inu.swcontest.gm.model;

import inu.swcontest.gm.entity.Member;
import inu.swcontest.gm.entity.Zip;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class DashboardResponse {

    private String email;

    private String projectName;

    private String zipUrl;

    private List<Float> accuracy_generated;

    private List<Float> accuracy_original_generated;

    // Learned Perceptual Image
    private List<Float> LPIPS;

    // fidelity
    private List<Float> fid;

    private String loss;

    private String generated_gif_url;

    private String generated_single_img_url;

    private String createdDate;

    public static DashboardResponse dashboardResponse(String email, String projectName, String zipUrl,
                                                      List<Float> accuracy_generated, List<Float> accuracy_original_generated,
                                                      List<Float> LPIPS, List<Float> fid, String loss,
                                                      String generated_gif_url, String generated_single_img_url, String createdDate) {
        DashboardResponse response = new DashboardResponse();
        response.email = email;
        response.projectName = projectName;
        response.zipUrl = zipUrl;
        response.accuracy_generated = accuracy_generated;
        response.accuracy_original_generated = accuracy_original_generated;
        response.LPIPS = LPIPS;
        response.fid = fid;
        response.loss = loss;
        response.generated_gif_url = generated_gif_url;
        response.generated_single_img_url = generated_single_img_url;
        response.createdDate = createdDate;
        return response;
    }
}
