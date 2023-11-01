//package inu.swcontest.gm.controller;
//
//
//import inu.swcontest.gm.model.AddMemberRequest;
//import inu.swcontest.gm.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(value = "/members")
//public class MemberController {
//
//    private final MemberService memberService;
//
//    @PostMapping()
//    public ResponseEntity addMember(@RequestBody AddMemberRequest addMemberRequest) {
//        memberService.addMember(addMemberRequest);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//}
