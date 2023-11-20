package inu.swcontest.gm.controller;


import inu.swcontest.gm.entity.Member;
import inu.swcontest.gm.model.AddMemberRequest;
import inu.swcontest.gm.model.CheckMemberStatusRequest;
import inu.swcontest.gm.model.LoginMemberRequest;
import inu.swcontest.gm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/members")
@CrossOrigin("http://localhost:3000")
public class MemberController {

    private final MemberService memberService;

    // join
    @PostMapping()
    public ResponseEntity addMember(@RequestBody AddMemberRequest addMemberRequest) {
        memberService.addMember(addMemberRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // login
    @PostMapping("/login")
    public Member loginMember(@RequestBody LoginMemberRequest loginMemberRequest) {
        Member member = memberService.loginMember(loginMemberRequest);
        return member;
    }

    // check member status
    @GetMapping("/status/{email}")
    public String checkMemberStatus(@PathVariable String email) {
        String memberStatus = memberService.checkMemberStatus(email);
        return memberStatus;
    }

    // return progress status value when member status is generating
    @GetMapping("/progress/status/{email}")
    public String progressStatus(@PathVariable String email) {
        String message = memberService.progressStatus(email);
        return message;
    }

}
