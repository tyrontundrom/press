package pl.lyszkowski.press.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lyszkowski.press.model.Article;
import pl.lyszkowski.press.service.ArticleService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("article")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("sortDate")
    @ResponseStatus(HttpStatus.OK)
    public List<Article> allArticlesSortedDescendingByDate() {
        List<Article> collect = articleService.getAll().stream().sorted(Comparator.comparing(article -> article.getPublicationDate()
                .toInstant())).collect(Collectors.toList());
        Collections.reverse(collect);
        return collect;
    }

    @GetMapping("{id}")
    public ResponseEntity<Article> getOneById(@PathVariable Long id) {
        return articleService
                .getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("search")
    @ResponseStatus(HttpStatus.OK)
    public List<Article> getArticlesIncludedKeyWord(@RequestParam String keyWord) {
        return articleService.getAll().stream().filter(art -> art.getContent().getTitle().contains(keyWord) ||
                art.getContent().getContent().contains(keyWord)).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article saveArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateArticle(@RequestBody Article article) {
        articleService.update(article);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        articleService.deleteById(id);
    }
}
