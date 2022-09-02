package com.example.tddfirst.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private String boardName;
    private String title;
    private String created_datetime;
    private String location;
}
