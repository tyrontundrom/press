package pl.lyszkowski.press.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lyszkowski.press.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
