package com.example.tddfirst.controller;

import com.example.tddfirst.dto.PostDetailResponseDto;
import com.example.tddfirst.dto.ResponseDto;
import com.example.tddfirst.service.Impl.PostServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 컨트롤러만 테스트하는 법
 * WebMvc 어노테이션
 * (1) AutoConfigureMockMvc 내장
 * (2) Controller, ControllerAdvice, JsonComponent, Filter, WebMvcConfigurer 등을 빈으로 올린다.
 */
@Slf4j
//@WebMvcTest(PostController.class)    이거도 왜 안되지 ?
@ExtendWith(MockitoExtension.class)
class PostControllerTest {

//    왜 안될까??
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean // IoC 환경에 가짜 bean 등록 실제로 동작 X
//    private PostServiceImpl postService;


    @InjectMocks
    private PostController postController;

    @Mock
    private PostServiceImpl postService;

    private MockMvc mockMvc;
//
    @BeforeEach
    public void init() {
    mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @DisplayName("게시글 목록 조회")
    @Test
    @Nested
    public void get_test() throws Exception {
        //given
        List<ResponseDto> responseDtos = new ArrayList<>();
        responseDtos.add(new ResponseDto("board name", "title_1", "생성 시간", "위치1"));
        responseDtos.add(new ResponseDto("board name", "title_2", "생성 시간", "위치2"));

        when(postService.get_list()).thenReturn(responseDtos); // stub - 행동 정의

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/board")
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(2))) // import static org.hamcrest.Matchers.*; 임포트하면 편한게 사용가능
                .andExpect(jsonPath("$.[0].boardName").value("board name"))
                .andExpect(jsonPath("$.[0].title").value("title_1"))
                .andExpect(jsonPath("$.[0].created_datetime").value("생성 시간"))
                .andExpect(jsonPath("$.[0].location").value("위치1"))
                .andExpect(jsonPath("$.[1].boardName").value("board name"))
                .andExpect(jsonPath("$.[1].title").value("title_2"))
                .andExpect(jsonPath("$.[1].created_datetime").value("생성 시간"))
                .andExpect(jsonPath("$.[1].location").value("위치2"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("게시글 상세 조회")
    @Test
    @Nested
    public void getDetail() throws Exception {

        //given
        List<String> location = new ArrayList<>();
        location.add("위치 1");
        location.add("위치 2");
        location.add("위치 3");
        PostDetailResponseDto postDetailResponseDto =
                new PostDetailResponseDto("board name",
                        "title_1",
                        "생성 시간",
                        location);


        when(postService.getDetail(any(Integer.class))).thenReturn(postDetailResponseDto); // stub - 행동 정의

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/board/1")
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.boardName").value("board name"))
                .andExpect(jsonPath("$.title").value("title_1"))
                .andExpect(jsonPath("$.created_datetime").value("생성 시간"))
                .andExpect(jsonPath("$.location[0]").value("위치 1"))
                .andExpect(jsonPath("$.location[1]").value("위치 2"))
                .andExpect(jsonPath("$.location[2]").value("위치 3"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("게시글 삭제")
    @Test
    @Nested
    public void deletePost() throws Exception {
        //given
        Integer id = 1;

        when(postService.deletePost(id)).thenReturn("ok"); // stub - 행동 정의

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/board/1")
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        MvcResult requestResult = resultActions.andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals("ok", result);
    }

    @DisplayName("게시글 수정")
    @Test
    public void updatePost() throws Exception {
        //given
        Integer id = 1;

        when(postService.updatePost(id)).thenReturn("ok"); // stub - 행동 정의

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/board/1")
                .accept(MediaType.APPLICATION_JSON));

        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        MvcResult requestResult = resultActions.andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals("ok", result);
    }


    // BDDMockito 패턴
//    @Test
//    public void save_test() throws Exception {
//        log.info("save Test 시작 ========================");
//
//        // given (테스트 하기위한 준비)
//        Board board = new Board(0, "Board Title");
//        Article article_1 = new Article(1,board, "title_1","content_1",1);
//        String content = new ObjectMapper().writeValueAsString(article_1);
//
//        //when (테스트 실행)
//        ResultActions resultActions = mockMvc.perform(post("/api/v1/board")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//                        .accept(MediaType.APPLICATION_JSON));
//
//        //then (검증 )
//        resultActions
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.title").value("title_1"))
//                .andExpect(jsonPath("$.content").value("content_1"))
//                .andDo(MockMvcResultHandlers.print());
//
//
//    }
}