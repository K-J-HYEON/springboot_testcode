package com.example.testCode.unittest;

import com.example.testCode.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AgeTest {

    @Test
    @DisplayName("멤버의 나이 바뀌는지 확인하는 테스트")
    void changeAgeTest(){
        /*
        given
         */
        Member member = Member.builder().age(10).name("hi").build();

        /*
        when
         */
        member.changeAge(11);

        /*
        then
         */
        Assertions.assertThat(member.getAge()).isEqualTo(11);

    }

}