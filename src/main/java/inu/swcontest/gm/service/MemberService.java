package inu.swcontest.gm.service;

import inu.swcontest.gm.entity.Member;
import inu.swcontest.gm.model.*;
import org.hibernate.annotations.Check;

import java.util.List;

public interface MemberService {
    void addMember(AddMemberRequest addMemberRequest);

    Member loginMember(LoginMemberRequest loginMemberRequest);

    String checkMemberStatus(String email);

    String progressStatus(String email);

    boolean updateMemberStatus(String email);
}
