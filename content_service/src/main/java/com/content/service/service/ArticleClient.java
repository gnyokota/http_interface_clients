package com.content.service.service;

import com.content.service.model.Article;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;
import java.util.Optional;

public interface ArticleClient {

    @GetExchange("/articles")
    List<Article> findAllArticles();

    @GetExchange("/articles/{id}")
    Optional<Article> findArticleById(Integer id);

    @PostExchange("/articles")
    Article createArticle(Article article);

    @PutExchange("/articles/{id}")
    Article updateArticle(Integer id, Article article);

    @DeleteExchange("/articles/{id}")
    void deleteArticle(Integer id);
}
