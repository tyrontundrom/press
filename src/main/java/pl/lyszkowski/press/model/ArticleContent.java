package pl.lyszkowski.press.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@ToString
@Getter
@Setter
public class ArticleContent {
    private String title;
    private String content;
}
