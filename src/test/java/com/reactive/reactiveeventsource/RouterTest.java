package com.reactive.reactiveeventsource;

import com.reactive.reactiveeventsource.dao.Dao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;


@WebFluxTest
@Import({Handler.class, Dao.class, Router.class})
public class RouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private Dao dao;

    @Test
    public void testGetAllID() {
        Mockito.when(dao.getAllUniverseID()).thenReturn(Flux.just(1,2,3,4));
        webTestClient.get().uri("/getAllID").exchange()
                .expectBodyList(Integer.class).contains(1,2,3,4);
    }

    @Test
    public void testDynamicContentType(){
        Mockito.when(dao.getAllUniverseID()).thenReturn(Flux.just(1,2,3,4));
        webTestClient.get().uri("/getAllID").accept(MediaType.APPLICATION_JSON).exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void testDynaicContentType_FailBack(){
        Mockito.when(dao.getAllUniverseID()).thenReturn(Flux.just(1,2,3,4));
        webTestClient.get().uri("/getAllID").exchange()
                .expectHeader().contentType(MediaType.APPLICATION_STREAM_JSON);
    }
}