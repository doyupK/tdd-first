package com.example.tddfirst.service.Impl;

import com.example.tddfirst.domain.Article;
import com.example.tddfirst.domain.Attachment;
import com.example.tddfirst.domain.Board;
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
import java.util.List;

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
//        for(Article article : articleList){
//            responseDtos.add(new ResponseDto(
//                    article.getBoard().getName(),
//                    article.getTitle(),
//                    article.getCreate_datetime().toString(),
//                    article.getAttachments().get(0).getLocation())
//            );
//        }

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
    public PostDetailResponseDto getDetail(Integer id){

        return new PostDetailResponseDto();
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
