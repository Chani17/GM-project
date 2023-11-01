package inu.swcontest.gm.service;

import inu.swcontest.gm.entity.Member;
import inu.swcontest.gm.model.AddMemberRequest;
import inu.swcontest.gm.model.LoginMemberRequest;
import inu.swcontest.gm.model.ZipFileResponse;

import java.util.List;

public interface MemberService {
    void addMember(AddMemberRequest addMemberRequest);

    Member loginMember(LoginMemberRequest loginMemberRequest);

}
