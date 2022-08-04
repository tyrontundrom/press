package pl.lyszkowski.press.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
public class ArticleController {

    @GetMapping
    void allArticlesSortedDescendingByDate() {

    }

    @GetMapping
    void getOneById() {

    }

    @GetMapping
    void getArticlesIncludedKeyWord() {

    }

    @PostMapping
    void saveArticle() {

    }

    @PatchMapping
    void editArticle() {

    }

    @DeleteMapping
    void deleteById() {

    }


}
