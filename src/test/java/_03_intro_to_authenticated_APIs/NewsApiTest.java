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

    @Mock
    WebClient wc;
    @Mock
    private RequestHeadersUriSpec mockRequest;
    @Mock
    private RequestHeadersSpec mockSpec;
    @Mock
    private ResponseSpec mockResponseSpec;
    @Mock
    private Mono<ApiExampleWrapper> mockResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        newsApi = new NewsApi(wc);
    }

    @Test
    void itShouldGetNewsStoryByTopic() {
        //given
        ApiExampleWrapper mockApiExampleWrapper = new ApiExampleWrapper();
        mockApiExampleWrapper.setTotalResults(1);
        when(wc.get()).thenReturn(mockRequest);
        when(mockRequest.uri((Function<UriBuilder, URI>) any())).thenReturn(mockSpec);
        when(mockSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.bodyToMono(ApiExampleWrapper.class)).thenReturn(mockResponse);
        when(mockResponse.block()).thenReturn(mockApiExampleWrapper);
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