package com.example.tddfirst.repository;

import com.example.tddfirst.domain.Article;
import com.example.tddfirst.domain.Attachment;
import com.example.tddfirst.domain.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)   // 실제DB테스트 Replace.NONE, 내장DB테스트 Replace.ANY
@DataJpaTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private BoardRepository boardRepository;


    @DisplayName("게시글 조회")
    @Test
    public void findPostAll(){

        // given
//        Board board = new Board(1, "Board Name");
//        boardRepository.save(board);
//
//        when(boardRepository.save(any())).thenReturn(board);
//        Attachment attachment_1 = new Attachment(null, "위치 1");
//        Attachment attachment_2 = new Attachment(null, "위치 2");
//        Attachment attachment_3 = new Attachment(null, "위치 3");
//        List<Attachment> attachmentList = new ArrayList<>();
//        attachmentList.add(attachment_1);
//        attachmentList.add(attachment_2);
//        attachmentList.add(attachment_3);
        articleRepository.saveAll(
                Arrays.asList(
                        new Article(null, null, "title_1","contents_2",10, null),
                        new Article(null, null, "title_2","contents_2",20, null)
                )
        );

        // when
        List<Article> result = articleRepository.findAll();

        // then
        log.info("article Entity size : "+result.size() );
        assertNotEquals(0, result.size());
        assertEquals(2, result.size());
    }

//    @DisplayName("게시글 수정")
//    @Test
//    public void updatePost(){
//
//        // given
//        articleRepository.saveAll(
//                Arrays.asList(
//                        new Article(1, null, "title_1","contents_1",10, null),
//                        new Article(2, null, "title_2","contents_2",20, null)
//                )
//        );
//        Article article = new Article(1,null,"update","content Update",10,null);
//        articleRepository.update(article);
//
//        // when
//        List<Article> result = articleRepository.findAll();
//
//        // then
//        log.info("article Entity size : "+result.size() );
//        assertNotEquals(0, result.size());
//        assertEquals(2, result.size());
//    }
}
