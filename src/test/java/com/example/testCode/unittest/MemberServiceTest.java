package com.example.testCode.unittest;

import com.example.testCode.Member;
import com.example.testCode.MemberRepository;
import com.example.testCode.MemberService;
import com.example.testCode.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
class MemberServiceTest {

    // Test 주체
    MemberService memberService;

    // Test 협력자
    @MockBean
    MemberRepository memberRepository;

    // Test를 실행하기 전마다 MemberService에 가짜 객체 주입
    @BeforeEach
    void setUp() {
        memberService = new MemberServiceImpl(memberRepository);
    }


    @Test
    @DisplayName("멤버 생성 성공")
    void createMemberSuccess() {
        /*
         given
         */
        Member member3 = Member.builder().name("hi3").age(10).build();
        ReflectionTestUtils.setField(member3, "id", 31);

        Mockito.when(memberRepository.save(member3)).thenReturn(member3); // 가짜 객체

        /*
        when
         */
        Long hi3 = memberService.createMember("hi3", 10);

        /*
        then
         */
        assertThat(hi3).isEqualTo(3L);
    }

    @Test
    @DisplayName("멤버 생성시 membert1과 이름이 같아서 예외 발생")
    void createMemberFail() {

        /*
        given
         */
        Member member1 = Member.builder().name("hi1").age(10).build();

        Mockito.when(memberRepository.findByName("hi1")).thenReturn(Optional.of(member1));

        /*
        when then
         */
        assertThatThrownBy(() ->
                memberService.createMember("hi1", 10)).isInstanceOf(IllegalStateException.class);
    }

}