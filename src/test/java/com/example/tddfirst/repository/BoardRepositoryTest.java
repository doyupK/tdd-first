package com.example.tddfirst.repository;

import com.example.tddfirst.domain.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Slf4j
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)   // 실제DB테스트 Replace.NONE, 내장DB테스트 Replace.ANY
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @DisplayName("게시판 조회")
    @Test
    public void findBoardAll(){
        // given
        boardRepository.saveAll(
                Arrays.asList(
                        new Board(null, "board_1"),
                        new Board(null, "board_2")
                )
        );

        // when
        List<Board> boardEntity = boardRepository.findAll();

        // then
        log.info("boardEntity : "+ boardEntity.size() );
        assertNotEquals(0, boardEntity.size());
        assertEquals(2, boardEntity.size());
    }




}
