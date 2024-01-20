package com.example.testCode.unittest;


import com.example.testCode.MemberController;
import com.example.testCode.MemberResponseDto;
import com.example.testCode.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MemberServiceImpl memberService;

    @Test
    @DisplayName("리스트 반환하기")
    void getList() throws Exception {
        // given
        List<MemberResponseDto.ListDto> list = List.of(new MemberResponseDto.ListDto("asd", 10),
                new MemberResponseDto.ListDto("fsd", 12));
        Mockito.when(memberService.findAll()).thenReturn(list);

        // when
        mvc.perform(MockMvcRequestBuilders.get("/members").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("fsd"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("asd"));

        // then
    }

}