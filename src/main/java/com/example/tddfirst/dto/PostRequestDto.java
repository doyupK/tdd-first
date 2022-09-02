package com.example.tddfirst.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;
}
