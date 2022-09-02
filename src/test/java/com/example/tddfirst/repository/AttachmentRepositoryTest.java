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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Slf4j
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)   // 실제DB테스트 Replace.NONE, 내장DB테스트 Replace.ANY
@DataJpaTest
public class AttachmentRepositoryTest {

    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("첨부 파일 조회")
    public void attachTest(){
        // given
        Board board = new Board(null, "Board Name");
        boardRepository.save(board);
        Attachment attachment_1 = new Attachment(null, "위치 1");
        Attachment attachment_2 = new Attachment(null, "위치 2");
        Attachment attachment_3 = new Attachment(null, "위치 3");
        attachmentRepository.save(attachment_1);
        attachmentRepository.save(attachment_2);
        attachmentRepository.save(attachment_3);
        List<Attachment> attachmentList = new ArrayList<>();
        attachmentList.add(attachment_1);
        attachmentList.add(attachment_2);
        attachmentList.add(attachment_3);

        Article article_1 = new Article(null,board, "title_1","contents_1",10,attachmentList);
        Article article_2 = new Article(null,board, "title_2","contents_2",20,attachmentList);
        articleRepository.saveAll(Arrays.asList(article_1,article_2));





        // when
        List<Attachment> result = attachmentRepository.findAll();

        // then
        log.info("attachment Entity size : " + result.size() );
        assertNotEquals(0, result.size());
        assertEquals(3, result.size());
        assertEquals("location_1",result.get(0).getLocation());
        assertEquals("location_2",result.get(1).getLocation());
        assertEquals("location_3",result.get(2).getLocation());
    }
}

