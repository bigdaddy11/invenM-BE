package com.main.invenmbe.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.main.invenmbe.dto.LoginRequest;
import com.main.invenmbe.dto.LoginResponse;
import com.main.invenmbe.entity.Member;
import com.main.invenmbe.repository.MemberRepository;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    private KeyPair keyPair;

    public MemberController() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        this.keyPair = keyPairGenerator.generateKeyPair();
    }

    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Optional<Member> member = memberRepository.findByUsername(loginRequest.getUsername());
        if (member.isPresent() && member.get().getPassword().equals(loginRequest.getPassword())) {
            // 세션에 사용자 정보 저장
            session.setAttribute("user", member.get());
            return ResponseEntity.ok().body(new LoginResponse("로그인 성공", "some-token", member.get().getId())); // 임시 토큰
        }

        return ResponseEntity.status(401).body("아이디 또는 비밀번호가 잘못되었습니다.");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return ResponseEntity.ok().body("로그아웃 성공");
    }
}
