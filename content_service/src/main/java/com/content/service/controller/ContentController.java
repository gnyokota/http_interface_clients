package com.content.service.controller;

import com.content.service.model.Article;
import com.content.service.service.ArticleClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    public final ArticleClient articleClient;

    public ContentController(ArticleClient articleClient) {
        this.articleClient = articleClient;
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> findAllArticles(){
        List<Article> articles = articleClient.findAllArticles();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> findArticleById(Integer id){
        Article article = articleClient.findArticleById(id).orElse(null);
        return ResponseEntity.ok(article);
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(Article article){
        Article createdArticle = articleClient.createArticle(article);
        return ResponseEntity.ok(createdArticle);
    }

}
