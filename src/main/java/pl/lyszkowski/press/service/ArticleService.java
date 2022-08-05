package pl.lyszkowski.press.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lyszkowski.press.model.Article;
import pl.lyszkowski.press.repository.ArticleRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Optional<Article> getById(Long id) {
        return articleRepository.findById(id);
    }


    public Article addArticle(Article article) {
        article.setTimestamp(Instant.now());
        return articleRepository.save(article);
    }

    public List<Article> getAll() {
        return articleRepository.findAll();
    }


    public Article update(Article article) {
        Optional<Article> byId = articleRepository.findById(article.getId());
        System.out.println("byId from service update: " + byId);
        Article editArticle = byId.get();
        System.out.println("editArticle from service update: " + editArticle);
        editArticle.setContent(article.getContent());
        editArticle.setNameMagazine(article.getNameMagazine());
        editArticle.setTimestamp(Instant.now());
        return articleRepository.save(editArticle);
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}
