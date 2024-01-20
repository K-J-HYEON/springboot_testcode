package com.example.testCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Builder
    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) { // 협력자에서 이름 중복 예외를 검증하기 위함
        Member me = (Member) obj;
        return this.name.equals(me.name) && this.age==me.age;
    }

    public void changeAge(int age) {
        this.age = age;
    }
}
