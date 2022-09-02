package com.example.tddfirst.domain;

import jdk.jfr.Timestamp;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Article extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Board.class)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(length = 128)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private Integer view_count;

    @OneToMany
    @JoinColumn(name = "article_id")
    private List<Attachment> attachments;


}
