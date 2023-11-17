package inu.swcontest.gm.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Zip> zipList = new ArrayList<>();

    public static Member createMember(String email, String password) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.status = MemberStatus.COMPLETE;
        return member;
    }

    public void updateMemberStatus() {
        this.status = MemberStatus.GENERATING;
    }

}
