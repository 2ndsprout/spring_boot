package com.example.spring_tboard.domain.article.controller;

import com.example.spring_tboard.domain.article.model.Article;
import com.example.spring_tboard.domain.article.model.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class ArticleController {

    ArticleRepository articleRepository = new ArticleRepository();

    @GetMapping("/add")
    public String addForm () {
        return "addForm";
    }

    @PostMapping("/add")
    public String add (@RequestParam("title") String title,
                       @RequestParam("body") String body) {

        articleRepository.saveArticle(title, body);

        return "redirect:/list";
    }
    @RequestMapping("/list")
    public String list (Model model) {
        ArrayList<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList", articleList);

        return "list";
    }
    @RequestMapping("/detail/{articleId}")
    public String detail(@PathVariable("articleId") int articleId, Model model) {
        Article article = articleRepository.findArticleById(articleId);
        if (article == null) {
            return "없는 게시물입니다.";
        }
        article.increaseHit();
        model.addAttribute("article", article);
        return "detail";
    }
    @RequestMapping("/delete/{articleId}")
    public String delete(@PathVariable("articleId") int articleId) {
        Article article = articleRepository.findArticleById(articleId);
        if (article == null) {
            return "없는 게시물입니다.";
        }
        articleRepository.deleteArticle(article);
        return "redirect:/list";
    }
    @GetMapping("/update/{articleId}")
    public String updateForm(@PathVariable("articleId") int articleId, Model model) {

        Article article = articleRepository.findArticleById(articleId);

        if (article == null) {
            throw new RuntimeException("없는 게시물입니다.");
        }

        model.addAttribute("article", article);
        return "updateForm";
    }
    @PostMapping("/update/{articleId}")
    public String update(@PathVariable("articleId") int articleId,
                         @RequestParam("title") String title,
                         @RequestParam("body") String body) {
        Article article = articleRepository.findArticleById(articleId);

        if (article == null) {
            throw new RuntimeException("없는 게시물입니다.");
        }

        articleRepository.updateArticle(article, title, body);

        return "redirect:/detail/%d".formatted(articleId);
    }

}
