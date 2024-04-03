package com.example.spring_tboard.domain.article.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleSQLRepository extends JpaRepository<Article, Integer> {
    List<Article> findByTitleContaining(String keyword);
}
