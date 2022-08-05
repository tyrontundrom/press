package pl.lyszkowski.press.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDate;
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
    private String content;
    private Date publicationDate;
    private String nameMagazine;
//    @Embedded
//    private Author articleAuthor;
    private Instant timestamp;
}
