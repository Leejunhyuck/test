package com.raccoon.blog.user.controller.v1;

import com.raccoon.blog.user.domain.Member;
import com.raccoon.blog.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vi/user/*")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final MemberRepository memberRepository;

    @GetMapping("admin/users")
    public Iterable<Member> allUsers() {
        return memberRepository.findAll();
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("hello", HttpStatus.ACCEPTED);
    }
}
