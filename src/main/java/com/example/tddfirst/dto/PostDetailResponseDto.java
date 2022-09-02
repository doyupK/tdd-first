package com.example.tddfirst.dto;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailResponseDto {
    private String boardName;
    private String title;
    private String created_datetime;
    private List<String> location;
}
