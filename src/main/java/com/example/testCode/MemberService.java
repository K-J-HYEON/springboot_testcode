package com.example.testCode;

import java.util.List;

public interface MemberService {

    List<MemberResponseDto.ListDto> findAll();

    Long createMember(String name, int age);
}
