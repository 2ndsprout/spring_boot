package com.example.spring_tboard.domain.article.controller;

import com.example.spring_tboard.domain.article.base.CommonUtil;
import com.example.spring_tboard.domain.article.model.Article;
import com.example.spring_tboard.domain.article.model.ArticleSQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ArticleController {

    //Repository articleRepository = new ArticleRepository();
    @Autowired
    ArticleSQLRepository articleSQLRepository;
    CommonUtil commonUtil = new CommonUtil();

    @GetMapping("/add")
    public String addForm () {
        return "add";
    }
    @PostMapping("/add")
    public String add (@RequestParam("title") String title,
                       @RequestParam("body") String body) {
        Article article = new Article();
        article.setTitle(title);
        article.setBody(body);
        article.setHit(0);
        article.setRegDate(commonUtil.getCurrentDateTime());
        articleSQLRepository.save(article);

        return "redirect:/list";
    }
    @RequestMapping("/list")
    public String list (Model model) {
        List<Article> articleList = articleSQLRepository.findAll();
        model.addAttribute("articleList", articleList);

        return "list";
    }
    @GetMapping("/update/{articleId}")
    public String updateForm (@PathVariable("articleId") int articleId, Model model) {
        Optional<Article> optionalArticle = articleSQLRepository.findById(articleId);

        Article article = optionalArticle.get();

        model.addAttribute("article",article);
        return "update";
    }
    @PostMapping("/update/{articleId}")
    public String update (@PathVariable("articleId") int articleId,
                          @RequestParam("title") String title,
                          @RequestParam("body") String body) {

        Optional<Article> optionalArticle = articleSQLRepository.findById(articleId);

        Article article = optionalArticle.get();

        // Article 객체의 속성 변경
        article.setTitle(title);
        article.setBody(body);

        // 변경된 Article 객체 저장
        articleSQLRepository.save(article);


        return "redirect:/detail/%d".formatted(articleId);
    }
    @RequestMapping("/delete/{articleId}")
    public String delete (@PathVariable("articleId") int articleId) {

        articleSQLRepository.deleteById(articleId);

        return "redirect:/list";

    }
    @RequestMapping("/detail/{articleId}")
    public String detail (@PathVariable("articleId") int articleId, Model model) {
        Optional<Article> optionalArticle = articleSQLRepository.findById(articleId);

        if (optionalArticle.isEmpty()) {
            throw new RuntimeException("게시물을 찾을 수 없습니다.");
        }

        Article article = optionalArticle.get();
        article.increaseHit();
        articleSQLRepository.save(article);

        model.addAttribute("article", article);
        return "detail";

    }
    @RequestMapping("/search")
    public String search (@RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {
        List<Article> searchedList = articleSQLRepository.findByTitleContaining(keyword);

        model.addAttribute("articleList", searchedList);
        return "list";
    }


}
