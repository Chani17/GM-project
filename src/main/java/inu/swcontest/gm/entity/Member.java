package inu.swcontest.gm.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name = "member_email", nullable = false, unique = true)
    private String email;

    @Column(name = "member_pw", nullable = false)
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Zip> zipList = new ArrayList<>();

    public static Member createMember(String email, String password) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        return member;
    }

}
