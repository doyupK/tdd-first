package com.example.tddfirst.repository;

import com.example.tddfirst.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
