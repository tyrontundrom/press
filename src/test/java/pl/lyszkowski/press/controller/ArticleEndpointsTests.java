package pl.lyszkowski.press.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.lyszkowski.press.model.Article;
import pl.lyszkowski.press.repository.ArticleRepository;
import pl.lyszkowski.press.service.ArticleService;

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
        mockMvc.perform(MockMvcRequestBuilders.get("/article/sortDat")
                .contentType("aplication/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("http://localhost/article/sortDate -> 200")
    void articleEndpoint_getAllArticles_shouldReturn200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/article/sortDate")
                .contentType("aplication/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("http://localhost/article/{id} -> 404")
    void articleEndpoint_getOneById_shouldReturn400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/article/1")
                        .contentType("aplication/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(404));
    }
}
