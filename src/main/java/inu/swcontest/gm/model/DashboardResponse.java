package inu.swcontest.gm.model;

import lombok.Data;

@Data
public class DashboardResponse {

    private String email;

    private String projectName;

    private String zipUrl;

    private Float accuracy;

    private Float LPIPS;

    private Float fid;

    private String createdDate;


    public static DashboardResponse dashboardResponse(String email, String projectName, String zipUrl, Float accuracy, Float LPIPS, Float fid, String createdDate) {
        DashboardResponse response = new DashboardResponse();
        response.email = email;
        response.projectName = projectName;
        response.zipUrl = zipUrl;
        response.accuracy = accuracy;
        response.LPIPS = LPIPS;
        response.fid = fid;
        response.createdDate = createdDate;
        return response;
    }
}
