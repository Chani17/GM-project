//package inu.swcontest.gm.service.serviceImpl;
//
//import inu.swcontest.gm.entity.Member;
//import inu.swcontest.gm.model.AddMemberRequest;
//import inu.swcontest.gm.repository.MemberRepository;
//import inu.swcontest.gm.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//
//@Service
//@RequiredArgsConstructor
//public class MemberServiceImpl implements MemberService {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public void addMember(AddMemberRequest addMemberRequest) {
//        if(memberRepository.existsByEmail(addMemberRequest.getEmail())) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
//
//        Member member = Member.createMember(addMemberRequest.getEmail(), addMemberRequest.getPassword());
//        memberRepository.save(member);
//    }
//}
