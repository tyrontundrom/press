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
public class Author {
    private String firstName;
    private String lastName;


}
