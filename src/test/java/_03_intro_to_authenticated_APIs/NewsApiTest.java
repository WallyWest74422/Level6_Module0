package _03_intro_to_authenticated_APIs;

import _03_intro_to_authenticated_APIs.data_transfer_objects.ApiExampleWrapper;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class NewsApiTest {

    NewsApi newsApi;

    private static final String baseUrl = "http://newsapi.org/v2/everything";
    private static final String apiKey = "59ac01326c584ac0a069a29798794bec";

    WebClient wc;

    @BeforeEach
    void setUp() {
        newsApi = new NewsApi();
    }

    @Test
    void itShouldGetNewsStoryByTopic() {
        //given
        //when
ApiExampleWrapper actual = newsApi.getNewsStoryByTopic("election");
        //then
        assertTrue(actual.getTotalResults()>=1);
    }

    @Test
    void itShouldFindStory(){
        //given
        //when
String actual = newsApi.findStory("politics");
        //then
        assertTrue(!actual.isEmpty());
    }


}