package com.example.testCode;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public List<MemberResponseDto.ListDto> findAll() {
        return memberRepository.findAll().stream().map(a -> new
                MemberResponseDto.ListDto(a.getName(),a.getAge()))
                .collect(Collectors.toList());
    }

    @Override
    public Long createMember(String name, int age) {
        memberRepository.findByName(name).ifPresent(a -> {
            throw new IllegalArgumentException("existed id");
        });

        Member member = Member.builder()
                .age(age)
                .name(name).build();


        return memberRepository.save(member).getId();
    }
}
