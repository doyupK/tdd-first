package com.example.tddfirst.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Attachment extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String location;

}
