package pl.lyszkowski.press.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lyszkowski.press.model.Article;
import pl.lyszkowski.press.model.ArticleContent;
import pl.lyszkowski.press.model.Author;
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
        Article editArticle = byId.get();
        String contentTitle = article.getContent().getTitle();
        String contentContent = article.getContent().getContent();
        String firstName = article.getArticleAuthor().getFirstName();
        String lastName = article.getArticleAuthor().getLastName();
        ArticleContent content = editArticle.getContent();
        Author author = editArticle.getArticleAuthor();
        content.setContent(contentContent);
        content.setTitle(contentTitle);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        editArticle.setContent(content);
        editArticle.setArticleAuthor(author);
        editArticle.setNameMagazine(article.getNameMagazine());
        editArticle.setTimestamp(Instant.now());
        return articleRepository.save(editArticle);
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}
