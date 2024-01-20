package com.example.testCode.unittest;

import com.example.testCode.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    @DisplayName("멤버가 생성되는지 확인하는 테스트")
    void createMember(){
        /*
        given
         */
        Member member = Member.builder().age(10).age(10).name("hi").build();

        /*
        when, then
        */
        Assertions.assertThat(member.getAge()).isEqualTo(10);
        Assertions.assertThat(member.getName()).isEqualTo("hi");
    }

}