package com.example.spring_tboard.domain.article.model;

import com.example.spring_tboard.domain.article.base.CommonUtil;

import java.util.ArrayList;
public class ArticleRepository implements Repository {

    CommonUtil commonUtil = new CommonUtil();
    ArrayList<Article> articleList = new ArrayList<>();
    int latestId = 4;
    public ArticleRepository () {
        makeTest();
    }

    public void makeTest () {
        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 0, commonUtil.getCurrentDateTime());
        Article a2 = new Article(2, "자바 질문좀 할게요~", "냉무", 0, commonUtil.getCurrentDateTime());
        Article a3 = new Article(3, "정처기 따야되나요?", "냉무", 0, commonUtil.getCurrentDateTime());
        articleList.add(a1);
        articleList.add(a2);
        articleList.add(a3);
    }

    public Article saveArticle (String title, String body) {
        Article article = new Article(latestId, title, body, 0 , commonUtil.getCurrentDateTime());
        articleList.add(article);
        latestId++;

        return article;
    }
    public void updateArticle (Article article, String title, String body) {
        article.setTitle(title);
        article.setBody(body);
    }
    public void deleteArticle (Article article) {
        articleList.remove(article);
    }
    public ArrayList<Article> findAll () {
        return articleList;
    }
    public ArrayList<Article> findArticleByKeyword (String keyword) {
        ArrayList<Article> searchedList = new ArrayList<>();

        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);

            if (article.getTitle().contains(keyword)) {
                searchedList.add(article);
            }
        }
        return searchedList;
    }
    public Article findArticleById (int articleId) {
        for (int i = 0; i < articleList.size(); i++){
            Article article = articleList.get(i);

            if (article.getId()==articleId) {
                return article;
            }
        }
        return null;
    }
}
