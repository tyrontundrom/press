package pl.lyszkowski.press.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lyszkowski.press.model.Article;
import pl.lyszkowski.press.service.ArticleService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("article")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("sortDate")
    public List<Article> allArticlesSortedDescendingByDate() {
        List<Article> collect = articleService.getAll().stream().sorted(Comparator.comparing(article -> article.getPublicationDate()
                .toInstant())).collect(Collectors.toList());
        Collections.reverse(collect);
        return collect;
    }

    @GetMapping("{id}")
    public Optional<Article> getOneById(@PathVariable Long id) {
        return articleService.getById(id);
    }

    @GetMapping("search")
    public List<Article> getArticlesIncludedKeyWord(@RequestParam String keyWord) {
        return articleService.getAll().stream().filter(art -> art.getContent().contains(keyWord) ||
                art.getNameMagazine().contains(keyWord)).collect(Collectors.toList());
    }

    @PostMapping
    public Article saveArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    @PatchMapping
    void updateArticle(@RequestBody Article article) {
        articleService.update(article);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        articleService.deleteById(id);
    }


}
