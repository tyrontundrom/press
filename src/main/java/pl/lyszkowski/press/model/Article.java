package pl.lyszkowski.press.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "title", column = @Column(name = "TITLE", length = 20)),
            @AttributeOverride(name = "content", column = @Column(name = "CONTENT", length = 5000))
    })
    private ArticleContent content;
    private Date publicationDate;
    private String nameMagazine;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "FIRST_NAME", length = 20)),
            @AttributeOverride(name = "lastName", column = @Column(name = "LAST_NAME", length = 20))
    })
    private Author articleAuthor;
    private Instant timestamp;
}
