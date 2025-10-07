package com.article.service.controller;

import com.article.service.model.Article;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final List<Article> articlesList =  new ArrayList<>();

    @PostConstruct
    private void init(){
        Article article = new Article(1, "First Article", "This is the body of the first article");
        articlesList.add(article);
    }

    @GetMapping
    public ResponseEntity<List<Article>> getArticles(){
        return ResponseEntity.ok(articlesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Article>> getArticleById(@RequestBody Integer id){
        Optional<Article> article = articlesList.stream().filter(a -> a.id().equals(id)).findFirst();
        return ResponseEntity.ok(article);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article createArticle(@RequestBody Article article){
        articlesList.add(article);
        return article;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Integer id, @RequestBody Article updatedArticle){
        for (int i = 0; i < articlesList.size(); i++) {
            if(articlesList.get(i).id().equals(id)){
                articlesList.set(i, updatedArticle);
                return ResponseEntity.ok(updatedArticle);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@RequestBody Integer id){
        articlesList.removeIf(article -> article.id().equals(id));
    }
}
