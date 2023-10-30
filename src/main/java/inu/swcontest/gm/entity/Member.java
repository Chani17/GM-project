package inu.swcontest.gm.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_seq")
    private Long id;

    private String email;

    private String password;

    public static Member createMember(String email, String password) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        return member;
    }
}
