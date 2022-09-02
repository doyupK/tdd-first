package com.example.tddfirst.service.Impl;

import com.example.tddfirst.domain.Article;
import com.example.tddfirst.domain.Attachment;
import com.example.tddfirst.domain.Board;
import com.example.tddfirst.dto.PostRequestDto;
import com.example.tddfirst.dto.ResponseDto;
import com.example.tddfirst.repository.ArticleRepository;
import com.example.tddfirst.repository.AttachmentRepository;
import com.example.tddfirst.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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



    @Test
    void get_list() {
        //given
        Board board = new Board(1, "test1");
//        given(boardRepository.save(board)).willReturn(board);
//        boardRepository.save(board);
        Attachment attachment_1 = new Attachment(1, "위치 1");
        Attachment attachment_2 = new Attachment(2, "위치 2");
        Attachment attachment_3 = new Attachment(3, "위치 3");
//        given(attachmentRepository.save(attachment_1)).willReturn(attachment_1);
//        given(attachmentRepository.save(attachment_2)).willReturn(attachment_2);
//        given(attachmentRepository.save(attachment_3)).willReturn(attachment_3);
//        attachmentRepository.save(attachment_1);
//        attachmentRepository.save(attachment_2);
//        attachmentRepository.save(attachment_3);
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
//
//
//        List<ResponseDto> result =  new ArrayList<>();
//        result.add(new ResponseDto(article_1.getBoard().getName(),
//                        article_1.getTitle(),
//                        "456",
//                        article_1.getAttachments().get(0).getLocation()));
//        result.add(new ResponseDto(article_2.getBoard().getName(),
//                        article_2.getTitle(),
//                        "123",
//                        article_2.getAttachments().get(0).getLocation()));


        //stub
        when(articleRepository.findAll()).thenReturn(articleList);
//        doReturn(articleList).when(articleRepository).findAll();
        List<ResponseDto> responseDtos = new ArrayList<>();
        for(Article article : articleList){
            responseDtos.add(ResponseDto.builder()
                    .boardName(article.getBoard().getName())
                    .title(article.getTitle())
                    .created_datetime(LocalDateTime.now().toString())
                    .location(article.getAttachments().get(0).getLocation()).build()
            );
        }
        //when
        List<ResponseDto> resultEntity = postService.get_list();
        //then
//        assertEquals(resultEntity,responseDtos);
        assertEquals(resultEntity.get(0).getBoardName(), responseDtos.get(0).getBoardName());
        log.info("result : "+ resultEntity.get(0).getBoardName());
        log.info("response : " + responseDtos.get(0).getBoardName());
        assertEquals(resultEntity.size(), responseDtos.size());
        log.info("result : {}", resultEntity.size());
        log.info("response : {}", responseDtos.size());

    }

    @Test
    void getDetail() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void updatePost() {
    }
}