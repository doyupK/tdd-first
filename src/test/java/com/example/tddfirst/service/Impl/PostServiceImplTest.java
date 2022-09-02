package com.example.tddfirst.service.Impl;

import com.example.tddfirst.domain.Article;
import com.example.tddfirst.domain.Attachment;
import com.example.tddfirst.domain.Board;
import com.example.tddfirst.dto.PostDetailResponseDto;
import com.example.tddfirst.dto.ResponseDto;
import com.example.tddfirst.repository.ArticleRepository;
import com.example.tddfirst.repository.AttachmentRepository;
import com.example.tddfirst.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private AttachmentRepository attachmentRepository;
    @Mock
    private BoardRepository boardRepository;



    @DisplayName("게시글 목록 조회 Service")
    @Test
    void get_list() {
        //given
        Board board = new Board(1, "test1");

        Attachment attachment_1 = new Attachment(1, "위치 1");
        Attachment attachment_2 = new Attachment(2, "위치 2");
        Attachment attachment_3 = new Attachment(3, "위치 3");

        List<Attachment> attachmentList = new ArrayList<>();
        attachmentList.add(attachment_1);
        attachmentList.add(attachment_2);
        attachmentList.add(attachment_3);

        Article article_1 = new Article(null, board, "title","content",10, attachmentList);
        article_1.setCreate_datetime(LocalDateTime.now());
        Article article_2 = new Article(null, board, "title","content",20, attachmentList);
        article_2.setCreate_datetime(LocalDateTime.now());

        List<Article> articleList = new ArrayList<>();
        articleList.add(article_1);
        articleList.add(article_2);

        //stub
        when(articleRepository.findAll()).thenReturn(articleList);

        List<ResponseDto> responseDtos = new ArrayList<>();
        for(Article article : articleList){
            responseDtos.add(ResponseDto.builder()
                    .boardName(article.getBoard().getName())
                    .title(article.getTitle())
                    .created_datetime(LocalDateTime.now().toString())
                    .location(article.getAttachments().get(0).getLocation())
                    .build()
            );
        }

        //when
        List<ResponseDto> resultEntity = postService.get_list();

        //then
        assertEquals(resultEntity.get(0).getBoardName(), responseDtos.get(0).getBoardName());
        log.info("result : "+ resultEntity.get(0).getBoardName());
        log.info("response : " + responseDtos.get(0).getBoardName());
        assertEquals(resultEntity.size(), responseDtos.size());
        log.info("result : {}", resultEntity.size());
        log.info("response : {}", responseDtos.size());

    }

    @DisplayName("게시글 상세 조회 Service")
    @Test
    void getDetail() {
        //given
        Board board = new Board(1, "test1");

        Attachment attachment_1 = new Attachment(1, "위치 1");
        Attachment attachment_2 = new Attachment(2, "위치 2");
        Attachment attachment_3 = new Attachment(3, "위치 3");

        List<Attachment> attachmentList = new ArrayList<>();
        attachmentList.add(attachment_1);
        attachmentList.add(attachment_2);
        attachmentList.add(attachment_3);

        Article article_1 = new Article(null, board, "title","content",10, attachmentList);
        article_1.setCreate_datetime(LocalDateTime.now());


        // stub
        when(articleRepository.findById(any(Integer.class))).thenReturn(Optional.of(article_1));
        PostDetailResponseDto postDetailResponseDtoStub =
                PostDetailResponseDto.builder()
                        .boardName(article_1.getBoard().getName())
                        .created_datetime(article_1.getCreate_datetime().toString())
                        .location(Collections.singletonList(article_1.getAttachments().get(0).getLocation()))
                        .title(article_1.getTitle())
                        .build();


        //when
        PostDetailResponseDto postDetailResponseDtoResult = postService.getDetail(1);

        //then
        assertEquals(postDetailResponseDtoStub.getBoardName(),postDetailResponseDtoResult.getBoardName());
        assertEquals(postDetailResponseDtoStub.getTitle(),postDetailResponseDtoResult.getTitle());
        assertEquals(postDetailResponseDtoStub.getCreated_datetime(),postDetailResponseDtoResult.getCreated_datetime());
        assertEquals(postDetailResponseDtoStub.getLocation(),postDetailResponseDtoResult.getLocation());


    }

    @Test
    void deletePost() {
    }

    @Test
    void updatePost() {
    }
}