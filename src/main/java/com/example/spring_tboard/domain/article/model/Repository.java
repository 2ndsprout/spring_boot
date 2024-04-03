package com.example.spring_tboard.domain.article.model;

import java.util.ArrayList;

public interface Repository {
    void makeTest ();
    Article saveArticle (String title, String body);
    void updateArticle (Article article, String title, String body);
    void deleteArticle (Article article);
    ArrayList<Article> findAll ();
    ArrayList<Article> findArticleByKeyword (String keyword);

    Article findArticleById (int articleId);
}
