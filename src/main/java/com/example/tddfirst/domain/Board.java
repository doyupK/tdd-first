package com.example.tddfirst.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 32)
    private String name;


}
