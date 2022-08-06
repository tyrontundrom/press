package pl.lyszkowski.press.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lyszkowski.press.model.Article;
import pl.lyszkowski.press.model.ArticleContent;
import pl.lyszkowski.press.model.Author;
import pl.lyszkowski.press.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @Mock
    private ArticleRepository repository;
    @InjectMocks
    private ArticleService service;

    @Test
    @DisplayName("return article when give ID")
    void returnArticle_when_giveId() {

        Article article = createArticle(service);

        Mockito.when(service.getById(1L)).thenReturn(Optional.of(article));

        service.getById(1L);
    }

    @Test
    @DisplayName("return all articles")
    void returnAllArticles() {
        Article article1 = createArticle(service);
        Article article2 = createArticle(service);
        Article article3 = createArticle(service);
        List<Article> articles = List.of(article1,article2,article3);


        Mockito.when(service.getAll()).thenReturn(articles);

        service.getAll();
    }

//    @Test
//    @DisplayName("return set article when give some article")
//    void returnSetArticle_when_giveSomeArticle() {
//
//        Article article = createArticle(service);
//        String newTitle = "Nowy tytuł";
//        article.setNameMagazine(newTitle);
//
//
//        Mockito.when(service.getById(1L).get().getNameMagazine()).thenReturn(newTitle);
//
//        service.update(article);
//    }

    private Article createArticle(ArticleService service) {
        String firstName = "Konrad";
        String lastName = "Walenrod";

        String cont = "Finished Spring Data repository scanning in 138 ms. Found 1 JPA repository interfaces";
        String title = "Pierwszy rok";

        String nameMagazine = "Prawda";

        Article article = new Article();

        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        ArticleContent content = new ArticleContent();
        content.setContent(cont);
        content.setTitle(title);

        article.setId(1L);
        article.setArticleAuthor(author);
        article.setContent(content);
        article.setNameMagazine(nameMagazine);
        article.setPublicationDate(new Date(898978988L));
        service.addArticle(article);
        return article;
    }
}
