package com.example.tddfirst.controller;

import com.example.tddfirst.dto.ResponseDto;
import com.example.tddfirst.service.Impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private PostServiceImpl postService;

    @PostMapping("/api/v1/board")
    public ResponseDto postBoard(){

        return postService.create();
    }
    @GetMapping("/api/v1/board")
    public ResponseEntity<?> getBoard(){
        return new ResponseEntity<>(postService.get_list(), HttpStatus.OK);
    }
    @GetMapping("/api/v1/board/{id}")
    public ResponseEntity<?> getBoardDetail(@PathVariable Integer id){

        return new ResponseEntity<>(postService.getDetail(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/board/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id){

        return new ResponseEntity<>(postService.deletePost(id), HttpStatus.OK);
    }

    @PutMapping("/api/v1/board/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id){

        return new ResponseEntity<>(postService.updatePost(id), HttpStatus.OK);

    }
}
