package pl.lyszkowski.press.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.lyszkowski.press.model.Article;
import pl.lyszkowski.press.model.ArticleContent;
import pl.lyszkowski.press.model.Author;
import pl.lyszkowski.press.repository.ArticleRepository;
import pl.lyszkowski.press.service.ArticleService;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleEndpointsTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private ArticleService service;

    @Test
    @DisplayName("http://localhost/article/sortDate -> 400")
    void articleEndpoint_wrongEndpointName_shouldReturn400() throws Exception {
        mockMvc.perform(get("/article/sortDat")
                .contentType("aplication/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("http://localhost/article/sortDate -> 200")
    void articleEndpoint_getAllArticles_shouldReturn200() throws Exception {
        mockMvc.perform(get("/article/sortDate")
                .contentType("aplication/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("http://localhost/article/{id} -> 404")
    void articleEndpoint_getOneById_shouldReturn400() throws Exception {
        mockMvc.perform(get("/article/1")
                        .contentType("aplication/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(404));
    }

    @Test
    @DisplayName("http://localhost/article -> 201")
    public void articleEndpoint_addArticle_shouldReturn201() throws Exception {
        // given
        var endpointURL = "/article";
        String name_magazine = "name magazine";
        String contentFromConent = "treść artykułu";
        String authorLastName = "Kowalski";
        String authorFirstName = "Marcin";
        String contentTitle = "Tytul";
        Author author = new Author();
        ArticleContent content = new ArticleContent();
        Article article = new Article();
        content.setTitle(contentTitle);
        content.setContent(contentFromConent);
        author.setFirstName(authorFirstName);
        author.setLastName(authorLastName);
        article.setArticleAuthor(author);
        article.setContent(content);
        article.setNameMagazine(name_magazine);
        service.addArticle(article);
        var newResponseAsJSON = objectMapper.writeValueAsString(article);
        var initialListSize = repository.findAll().size();
        // when
        mockMvc
                .perform(
                        post(endpointURL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newResponseAsJSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(201));
        // then
        assertAll("should add one article",
                () -> assertEquals(initialListSize, repository.findAll().size()),
                () -> assertEquals(contentFromConent, repository.findAll().get(0).getContent().getContent()),
                () -> assertEquals(contentTitle, repository.findAll().get(0).getContent().getTitle()),
                () -> assertEquals(authorFirstName, repository.findAll().get(0).getArticleAuthor().getFirstName()),
                () -> assertEquals(authorLastName, repository.findAll().get(0).getArticleAuthor().getLastName()),
                () -> assertEquals(name_magazine, repository.findAll().get(0).getNameMagazine())
                );
    }

    @Test
    @DisplayName("http://localhost/article/search?keyWord=abc -> 200")
    public void articleEndpoint_getArticleListWithParam_shouldReturn200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/article/search")
                        .contentType("application/json")
                        .param("keyWord", "abc"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
