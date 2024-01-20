package com.example.testCode.integrationtest;

import com.example.testCode.MemberRepository;
import com.example.testCode.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 만들기")
    void createMemberSuccess() {
        Long memberId = memberService.createMember("hi1", 10);
        assertThat(memberId).isEqualTo(1l);
    }

    @Test
    @DisplayName("이름 중복으로 만들기 실패")
    void createMemberFail() {
        Long memberId = memberService.createMember("hi1", 10);
        assertThatThrownBy(() ->
                memberService.createMember("hi1", 12)).isInstanceOf(IllegalStateException.class);
    }
}