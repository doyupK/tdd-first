package com.example.tddfirst.service.Impl;

import com.example.tddfirst.domain.Article;
import com.example.tddfirst.dto.PostDetailResponseDto;
import com.example.tddfirst.dto.ResponseDto;
import com.example.tddfirst.repository.ArticleRepository;
import com.example.tddfirst.repository.AttachmentRepository;
import com.example.tddfirst.repository.BoardRepository;
import com.example.tddfirst.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final ArticleRepository articleRepository;
    private final AttachmentRepository attachmentRepository;
    private final BoardRepository boardRepository;


    @Transactional
    @Override
    public List<ResponseDto> get_list(){
        List<ResponseDto> responseDtos = new ArrayList<>();
        List<Article> articleList = articleRepository.findAll();

        for(Article article : articleList){
            responseDtos.add(ResponseDto.builder()
                    .boardName(article.getBoard().getName())
                    .title(article.getTitle())
                    .created_datetime(article.getCreate_datetime().toString())
                    .location(article.getAttachments().get(0).getLocation()).build()
            );
        }

        return responseDtos;
    }

    @Transactional(readOnly = true)
    @Override
    public PostDetailResponseDto getDetail(Integer id) {
        Optional<Article> article = articleRepository.findById(id);
        PostDetailResponseDto postDetailResponseDto;
        if (!article.isPresent()) {
            throw new NullPointerException("게시글이 없습니다.");
        } else {
            postDetailResponseDto = PostDetailResponseDto.builder()
                    .boardName(article.get().getBoard().getName())
                    .created_datetime(article.get().getCreate_datetime().toString())
                    .location(Collections.singletonList(article.get().getAttachments().get(0).getLocation()))
                    .title(article.get().getTitle())
                    .build();
        }
        return postDetailResponseDto;
    }

    @Override
    public String deletePost(Integer id) {
        return "delete_ok";
    }


    public String updatePost(Integer id) {
        return "update_ok";
    }

    @Transactional
    public ResponseDto create() {
        return new ResponseDto();
    }
}
