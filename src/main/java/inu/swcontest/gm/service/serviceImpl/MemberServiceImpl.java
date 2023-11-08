package inu.swcontest.gm.service.serviceImpl;

import inu.swcontest.gm.entity.Member;
import inu.swcontest.gm.model.AddMemberRequest;
import inu.swcontest.gm.model.LoginMemberRequest;
import inu.swcontest.gm.model.ZipFileResponse;
import inu.swcontest.gm.repository.MemberRepository;
import inu.swcontest.gm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void addMember(AddMemberRequest addMemberRequest) {
        if(memberRepository.existsByEmail(addMemberRequest.getEmail())) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        Member member = Member.createMember(addMemberRequest.getEmail(), addMemberRequest.getPassword());
        memberRepository.save(member);
    }

    @Override
    public Member loginMember(LoginMemberRequest loginMemberRequest) {
        Member member = memberRepository.findByEmail(loginMemberRequest.getEmail())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 이메일입니다."));

        if(!member.getPassword().equals(loginMemberRequest.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        return member;
    }

}
