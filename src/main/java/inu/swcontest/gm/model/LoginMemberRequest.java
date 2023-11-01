package inu.swcontest.gm.model;


import lombok.Data;

@Data
public class LoginMemberRequest {

    private String email;

    private String password;
}
