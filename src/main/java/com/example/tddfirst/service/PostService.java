package com.example.tddfirst.service;


import com.example.tddfirst.dto.PostDetailResponseDto;
import com.example.tddfirst.dto.ResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostService {

    @Transactional(readOnly = true)
    List<ResponseDto> get_list();

    @Transactional(readOnly = true)
    PostDetailResponseDto getDetail(Integer id);

    @Transactional
    String deletePost(Integer id);
}
